package com.example.demo.Controller;

import com.example.demo.facade.UsuarioFacade;
import com.example.demo.facade.ReservaFacade;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.Entities.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * Controlador para las funcionalidades del usuario
 * Maneja perfil y operaciones específicas de usuarios
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioFacade usuarioFacade;
    private final ReservaFacade reservaFacade;

    public UsuarioController(UsuarioFacade usuarioFacade, ReservaFacade reservaFacade) {
        this.usuarioFacade = usuarioFacade;
        this.reservaFacade = reservaFacade;
    }

    /**
     * Muestra el perfil del usuario
     */
    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Usuario usuario = obtenerUsuarioSesion(session);
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Debe iniciar sesión para acceder a esta página");
            return "redirect:/login";
        }

        // Verificar que es un usuario normal
        if (!"USER".equals(usuario.getRol())) {
            return "redirect:/admin";
        }

        // Obtener reservas futuras del usuario
        List<ReservaDTO> reservasFuturas = reservaFacade.obtenerReservasFuturasUsuario(usuario);
        List<ReservaDTO> todasLasReservas = reservaFacade.obtenerReservasUsuario(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("reservasFuturas", reservasFuturas);
        model.addAttribute("todasLasReservas", todasLasReservas);

        return "perfilu"; // perfilu.jsp
    }

    /**
     * Cancela una reserva del usuario
     */
    @PostMapping("/cancelar-reserva/{idReserva}")
    public String cancelarReserva(@PathVariable Integer idReserva,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        Usuario usuario = obtenerUsuarioSesion(session);
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Debe iniciar sesión para realizar esta acción");
            return "redirect:/login";
        }

        try {
            boolean cancelada = reservaFacade.cancelarReserva(idReserva, usuario);
            if (cancelada) {
                redirectAttributes.addFlashAttribute("successMessage", "Reserva cancelada exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "No se pudo cancelar la reserva");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/usuario/perfil";
    }    /**
     * Actualiza la información del perfil del usuario
     */
    @PostMapping("/actualizar-perfil")
    public String actualizarPerfil(@ModelAttribute Usuario usuarioActualizado,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        Usuario usuario = obtenerUsuarioSesion(session);
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Debe iniciar sesión para realizar esta acción");
            return "redirect:/login";
        }

        try {
            // Mantener datos importantes del usuario original
            usuarioActualizado.setIdUsuario(usuario.getIdUsuario());
            usuarioActualizado.setRol(usuario.getRol());
            usuarioActualizado.setUsuario(usuario.getUsuario()); // No permitir cambio de username

            // Si no se proporciona nueva contraseña, mantener la actual
            if (usuarioActualizado.getContrasena() == null || usuarioActualizado.getContrasena().trim().isEmpty()) {
                usuarioActualizado.setContrasena(usuario.getContrasena());
            }

            Usuario usuarioGuardado = usuarioFacade.actualizarUsuario(usuarioActualizado);

            // Actualizar la sesión
            session.setAttribute("usuarioLogueado", usuarioGuardado);
            session.setAttribute("nombreUsuario", usuarioGuardado.getNombreCompleto());

            redirectAttributes.addFlashAttribute("successMessage", "Perfil actualizado exitosamente");
            return "redirect:/usuario/perfil";        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/usuario/perfil";
        }
    }

    /**
     * Método helper para obtener el usuario de la sesión
     */
    private Usuario obtenerUsuarioSesion(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioLogueado");
    }
}
