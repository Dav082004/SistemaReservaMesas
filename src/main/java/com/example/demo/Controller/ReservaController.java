package com.example.demo.Controller;

import com.example.demo.facade.ReservaFacade;
import com.example.demo.dto.ReservaFormDTO;
import com.example.demo.Entities.Usuario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controlador para las reservas
 * Refactorizado para usar el patrón Facade y requerir autenticación
 */
@Controller
public class ReservaController {

    private final ReservaFacade reservaFacade;

    public ReservaController(ReservaFacade reservaFacade) {
        this.reservaFacade = reservaFacade;
    }

    /**
     * Muestra el formulario de reserva
     */
    @GetMapping("/reservaciones")
    public String mostrarFormularioReserva(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Obtener usuario de la sesión (puede ser null)
        Usuario usuario = obtenerUsuarioSesion(session);

        // Si el usuario está logueado, preparar el formulario
        if (usuario != null) {
            // Verificar que es un usuario normal (no admin)
            if ("ADMIN".equals(usuario.getRol())) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Los administradores no pueden hacer reservas desde aquí");
                return "redirect:/admin";
            }

            // Crear el formulario con datos del usuario
            ReservaFormDTO reservaForm = new ReservaFormDTO();
            reservaForm.setNombreCliente(usuario.getNombreCompleto());
            reservaForm.setCorreoCliente(usuario.getCorreo());
            reservaForm.setTelefonoCliente(usuario.getTelefono());

            model.addAttribute("reservaForm", reservaForm);
            model.addAttribute("tiposDeMesa", reservaFacade.obtenerTiposMesa());
            model.addAttribute("franjasHorarias", reservaFacade.obtenerFranjasHorarias());
            model.addAttribute("fechaMin", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            model.addAttribute("fechaMax", LocalDate.now().plusMonths(2).format(DateTimeFormatter.ISO_LOCAL_DATE));
            model.addAttribute("usuario", usuario);
        }

        // Siempre ir a la página de reservaciones (el JSP decide qué mostrar)
        return "reservaciones";
    }

    /**
     * API para verificar disponibilidad (AJAX)
     */
    @GetMapping("/api/verificar-disponibilidad")
    @ResponseBody
    public boolean verificarDisponibilidad(@RequestParam String fecha,
            @RequestParam Integer idFranja,
            @RequestParam Integer numeroPersonas,
            @RequestParam Integer idTipoMesa) {
        try {
            LocalDate fechaSeleccionada = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
            return reservaFacade.verificarDisponibilidad(fechaSeleccionada, idFranja, numeroPersonas, idTipoMesa);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * API para calcular mesas necesarias (AJAX)
     */
    @GetMapping("/api/calcular-mesas")
    @ResponseBody
    public int calcularMesasNecesarias(@RequestParam Integer numeroPersonas) {
        return reservaFacade.calcularMesasNecesarias(numeroPersonas);
    }

    /**
     * Procesa la creación de una reserva
     */
    @PostMapping("/reservaciones/crear")
    public String procesarReserva(@ModelAttribute("reservaForm") ReservaFormDTO formDTO,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        // Verificar que el usuario esté logueado
        Usuario usuario = obtenerUsuarioSesion(session);
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Debe iniciar sesión para hacer una reserva");
            return "redirect:/login";
        }

        try {
            // Crear la reserva usando el facade
            reservaFacade.crearReserva(formDTO, usuario);
            redirectAttributes.addFlashAttribute("successMessage",
                    "¡Tu reserva ha sido creada exitosamente! Estado: Por confirmar");
            return "redirect:/usuario/perfil";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error al crear la reserva: " + e.getMessage());
            redirectAttributes.addFlashAttribute("reservaForm", formDTO);
            return "redirect:/reservaciones";
        }
    }

    /**
     * Método helper para obtener el usuario de la sesión
     */
    private Usuario obtenerUsuarioSesion(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioLogueado");
    }
}