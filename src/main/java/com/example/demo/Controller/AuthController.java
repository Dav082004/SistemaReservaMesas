package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

import com.example.demo.facade.UsuarioFacade;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UsuarioRegistroDto;
import com.example.demo.Entities.Usuario;

/**
 * Controlador para la autenticación y gestión de usuarios
 * Refactorizado para usar el patrón Facade
 */
@Controller
public class AuthController {

    private final UsuarioFacade usuarioFacade;

    public AuthController(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    /**
     * Muestra el formulario de login.
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login"; // login.jsp
    }

    /**
     * Procesa el login
     */
    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute LoginDTO loginDTO,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioFacade.autenticarUsuario(loginDTO);

            // Guardar usuario en sesión
            session.setAttribute("usuarioLogueado", usuario);
            session.setAttribute("usuario", usuario); // Para compatibilidad con JSPs
            session.setAttribute("nombreUsuario", usuario.getNombreCompleto());
            session.setAttribute("rolUsuario", usuario.getRol());
            session.setAttribute("usuarioId", usuario.getIdUsuario());

            // Redirigir según el rol
            if ("ADMIN".equals(usuario.getRol())) {
                return "redirect:/admin";
            } else {
                return "redirect:/reservaciones";
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/login";
        }
    }

    /**
     * Muestra el formulario de registro.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new UsuarioRegistroDto());
        return "register"; // register.jsp
    }

    /**
     * Procesa el formulario de registro.
     */
    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("usuario") UsuarioRegistroDto registroDTO,
            RedirectAttributes redirectAttributes) {
        try {
            usuarioFacade.registrarUsuario(registroDTO);
            redirectAttributes.addFlashAttribute("successMessage",
                    "¡Registro exitoso! Ahora puedes iniciar sesión.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("usuario", registroDTO);
            return "redirect:/register";
        }
    }

    /**
     * Cierra la sesión
     */
    @PostMapping("/logout")
    public String logoutPost(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "Sesión cerrada exitosamente");
        return "redirect:/";
    }

    /**
     * Cierra la sesión (GET)
     */
    @GetMapping("/logout")
    public String logoutGet(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "Sesión cerrada exitosamente");
        return "redirect:/";
    }

    /**
     * Verifica si un usuario está disponible (AJAX)
     */
    @GetMapping("/verificar-usuario")
    @ResponseBody
    public boolean verificarUsuario(@RequestParam String usuario) {
        return !usuarioFacade.existeUsuario(usuario);
    }

    /**
     * Verifica si un correo está disponible (AJAX)
     */
    @GetMapping("/verificar-correo")
    @ResponseBody
    public boolean verificarCorreo(@RequestParam String correo) {
        return !usuarioFacade.existeCorreo(correo);
    }
}