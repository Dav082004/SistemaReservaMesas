package com.example.demo.Controller;

import com.example.demo.dto.PasswordChangeDTO;
import com.example.demo.dto.PerfilDatosDTO;
import com.example.demo.Entities.Usuario;
import com.example.demo.Services.UsuarioService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/perfil") // Todas las rutas de este controlador empezarán con /perfil
public class PerfilController {

    private final UsuarioService usuarioService;

    @Autowired
    public PerfilController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Muestra la página de perfil
    @GetMapping
    public String mostrarPerfil(HttpSession session, Model model) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado == null) {
            return "redirect:/login"; // Si no hay sesión, va a login
        }
        model.addAttribute("usuario", usuarioLogueado);
        // Aquí iría la lógica para cargar las reservas, por ahora se omite.
        return "perfil";
    }

    // Procesa la actualización de datos personales
    @PostMapping("/datos")
    public String actualizarDatos(@ModelAttribute PerfilDatosDTO datosDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado == null) {
            return "redirect:/login";
        }

        try {
            Usuario usuarioActualizado = usuarioService.actualizarDatosPerfil(usuarioLogueado.getIdUsuario(), datosDTO);
            session.setAttribute("usuario", usuarioActualizado); // Actualiza la sesión con los nuevos datos
            redirectAttributes.addFlashAttribute("successMessage", "¡Datos actualizados con éxito!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar los datos.");
        }

        return "redirect:/perfil";
    }

    // Procesa el cambio de contraseña
    @PostMapping("/password")
    public String cambiarContrasena(@ModelAttribute PasswordChangeDTO passwordDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        if (usuarioLogueado == null) {
            return "redirect:/login";
        }

        // Validación básica
        if (!passwordDTO.getNuevaContrasena().equals(passwordDTO.getConfirmarContrasena())) {
            redirectAttributes.addFlashAttribute("passwordError", "Las nuevas contraseñas no coinciden.");
            return "redirect:/perfil";
        }

        try {
            usuarioService.cambiarContrasena(
                usuarioLogueado.getIdUsuario(),
                passwordDTO.getContrasenaActual(),
                passwordDTO.getNuevaContrasena()
            );
            redirectAttributes.addFlashAttribute("passwordSuccess", "¡Contraseña actualizada con éxito!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("passwordError", e.getMessage());
        }

        return "redirect:/perfil";
    }
}
