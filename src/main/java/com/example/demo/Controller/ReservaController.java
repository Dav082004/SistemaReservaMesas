package com.example.demo.Controller;

import com.example.demo.facade.ReservaFacade;
import com.example.demo.dto.ReservaFormDTO;
import com.example.demo.Entities.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controlador para la gestión de reservas
 * 
 * Responsabilidades:
 * - Mostrar formulario de reserva
 * - Procesar creación de reservas
 * - Validar disponibilidad
 * - Gestionar autenticación de usuarios
 * 
 * Patrón MVC: Este controlador actúa como la capa de presentación,
 * delegando la lógica de negocio al ReservaFacade
 */
@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaFacade reservaFacade;

    /**
     * Muestra el formulario de reserva con datos precargados
     * 
     * @param fecha Fecha seleccionada desde la consulta de disponibilidad
     * @param idFranja ID de la franja horaria seleccionada
     * @param numeroPersonas Número de personas para la reserva
     * @param session Sesión HTTP para obtener datos del usuario logueado
     * @param model Modelo para pasar datos a la vista
     * @return Vista del formulario de reserva
     */
    @GetMapping("/nueva")
    public String mostrarFormularioReserva(
            @RequestParam(required = false) String fecha,
            @RequestParam(required = false) Integer idFranja,
            @RequestParam(required = false) Integer numeroPersonas,
            HttpSession session,
            Model model) {
        
        // Verificar que el usuario esté autenticado
        Usuario usuario = obtenerUsuarioSesion(session);
        if (usuario == null) {
            model.addAttribute("error", "Debe iniciar sesión para hacer una reserva");
            return "login";
        }

        // Verificar que no sea un administrador
        if ("ADMIN".equals(usuario.getRol())) {
            model.addAttribute("error", "Los administradores no pueden hacer reservas desde aquí");
            return "redirect:/admin";
        }

        // Crear formulario con datos del usuario y parámetros recibidos
        ReservaFormDTO reservaForm = new ReservaFormDTO();
        reservaForm.setNombreCliente(usuario.getNombreCompleto());
        reservaForm.setCorreoCliente(usuario.getCorreo());
        reservaForm.setTelefonoCliente(usuario.getTelefono());
        
        // Precargar datos si vienen de la consulta de disponibilidad
        if (fecha != null) reservaForm.setFecha(fecha);
        if (idFranja != null) reservaForm.setIdFranja(idFranja);
        if (numeroPersonas != null) reservaForm.setNumeroPersonas(numeroPersonas);

        // Preparar datos para los selectores del formulario
        model.addAttribute("reservaForm", reservaForm);
        model.addAttribute("tiposDeMesa", reservaFacade.obtenerTiposMesa());
        model.addAttribute("franjasHorarias", reservaFacade.obtenerFranjasHorarias());
        model.addAttribute("fechaMin", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        model.addAttribute("fechaMax", LocalDate.now().plusMonths(2).format(DateTimeFormatter.ISO_LOCAL_DATE));
        model.addAttribute("usuario", usuario);

        return "nueva-reserva"; // Vista nueva-reserva.jsp
    }

    /**
     * Procesa la creación de una nueva reserva
     * 
     * @param reservaForm Datos del formulario de reserva
     * @param session Sesión HTTP para obtener el usuario autenticado
     * @param redirectAttributes Atributos para mensajes flash
     * @return Redirección según el resultado de la operación
     */
    @PostMapping("/crear")
    public String crearReserva(
            @ModelAttribute("reservaForm") ReservaFormDTO reservaForm,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        // Verificar autenticación del usuario
        Usuario usuario = obtenerUsuarioSesion(session);
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", 
                "Debe iniciar sesión para hacer una reserva");
            return "redirect:/login";
        }

        try {
            // Delegar la creación de la reserva al facade
            reservaFacade.crearReserva(reservaForm, usuario);
            
            // Mensaje de éxito
            redirectAttributes.addFlashAttribute("mensaje", 
                "¡Reserva creada exitosamente! Estado: Por confirmar");
            
            return "redirect:/reservas/exito";

        } catch (IllegalArgumentException e) {
            // Errores de validación (datos incorrectos)
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("reservaForm", reservaForm);
            return "redirect:/reservas/nueva";
            
        } catch (RuntimeException e) {
            // Errores de negocio (no disponibilidad, etc.)
            redirectAttributes.addFlashAttribute("error", 
                "Error al crear la reserva: " + e.getMessage());
            redirectAttributes.addFlashAttribute("reservaForm", reservaForm);
            return "redirect:/reservas/nueva";
            
        } catch (Exception e) {
            // Errores inesperados
            redirectAttributes.addFlashAttribute("error", 
                "Error inesperado. Por favor, intente nuevamente.");
            return "redirect:/reservas/nueva";
        }
    }

    /**
     * Muestra la página de confirmación después de crear una reserva exitosamente
     * 
     * @param model Modelo para pasar datos a la vista
     * @return Vista de confirmación
     */
    @GetMapping("/exito")
    public String mostrarConfirmacion(Model model) {
        return "confirmacion-reserva"; // Vista confirmacion-reserva.jsp
    }

    /**
     * Verifica la disponibilidad para una fecha y franja específica
     * Se utiliza para validación antes de mostrar el formulario de reserva
     * 
     * @param fecha Fecha en formato yyyy-MM-dd
     * @param idFranja ID de la franja horaria
     * @param numeroPersonas Número de personas
     * @param idTipoMesa ID del tipo de mesa requerido
     * @param model Modelo para pasar datos a la vista
     * @return Vista con el resultado de la verificación
     */
    @GetMapping("/verificar-disponibilidad")
    public String verificarDisponibilidad(
            @RequestParam String fecha,
            @RequestParam Integer idFranja,
            @RequestParam Integer numeroPersonas,
            @RequestParam Integer idTipoMesa,
            Model model) {
        
        try {
            LocalDate fechaSeleccionada = LocalDate.parse(fecha);
            boolean disponible = reservaFacade.verificarDisponibilidad(
                fechaSeleccionada, idFranja, numeroPersonas, idTipoMesa);
            
            model.addAttribute("disponible", disponible);
            model.addAttribute("fecha", fecha);
            model.addAttribute("idFranja", idFranja);
            model.addAttribute("numeroPersonas", numeroPersonas);
            model.addAttribute("idTipoMesa", idTipoMesa);
            
            if (disponible) {
                return "redirect:/reservas/nueva?fecha=" + fecha + 
                       "&idFranja=" + idFranja + "&numeroPersonas=" + numeroPersonas;
            } else {
                model.addAttribute("error", "No hay disponibilidad para la fecha y horario seleccionados");
                return "disponibilidad";
            }
            
        } catch (Exception e) {
            model.addAttribute("error", "Error al verificar disponibilidad: " + e.getMessage());
            return "disponibilidad";
        }
    }

    /**
     * Método auxiliar para obtener el usuario de la sesión HTTP
     * 
     * @param session Sesión HTTP actual
     * @return Usuario logueado o null si no hay sesión activa
     */
    private Usuario obtenerUsuarioSesion(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioLogueado");
    }
}