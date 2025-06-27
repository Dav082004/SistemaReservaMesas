package com.example.demo.Controller;

import com.example.demo.dto.UsuarioRegistroDTO; // Importar el DTO
import com.example.demo.Entities.Usuario;
import com.example.demo.Services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // Usar ModelAttribute para el DTO
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Muestra la página de login
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }

    // Procesa el formulario de login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String usuario, @RequestParam String contrasena, HttpServletRequest request) {
        Optional<Usuario> optUsuario = usuarioService.validarLogin(usuario, contrasena);

        if (optUsuario.isPresent()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", optUsuario.get());
            return "redirect:/"; 
        } else {
            return "redirect:/login?error";
        }
    }

    // Muestra la página de registro
    @GetMapping("/register")
    public String mostrarFormularioRegistro() {
        return "register";
    }

    // Procesa el formulario de registro usando el DTO
    @PostMapping("/register")
    public String procesarRegistro(@ModelAttribute UsuarioRegistroDTO usuarioDTO, Model model, RedirectAttributes redirectAttributes) {
        
        // 1. Mapear el DTO al Modelo
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreCompleto(usuarioDTO.getNombreCompleto());
        nuevoUsuario.setCorreo(usuarioDTO.getCorreo());
        nuevoUsuario.setTelefono(usuarioDTO.getTelefono());
        nuevoUsuario.setUsuario(usuarioDTO.getUsuario());
        nuevoUsuario.setContrasena(usuarioDTO.getContrasena());
        // No se mapea el rol, eso se define en la capa de servicio.

        try {
            // 2. Llamar al servicio con el objeto del Modelo
            usuarioService.registrarUsuario(nuevoUsuario);
            
            redirectAttributes.addFlashAttribute("successMessage", "¡Registro exitoso! Por favor, inicia sesión.");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }
    
    // Procesa el cierre de sesión
    @PostMapping("/logout")
    public String procesarLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); 
        }
        return "redirect:/login?logout";
    }
}