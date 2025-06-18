package com.example.demo.Controller;

import com.example.demo.Entities.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.Services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminProfileController {

     private final UsuarioRepository usuarioRepository;
     private final UsuarioService usuarioService;

     public AdminProfileController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
          this.usuarioRepository = usuarioRepository;
          this.usuarioService = usuarioService;
     }

     /**
      * Muestra la página de perfil administrativo.
      * 
      * @param model El modelo para pasar datos a la vista.
      * @return La plantilla de la vista de perfil admin.
      */
     @GetMapping("/perfiladmin")
     public String showAdminProfilePage(Model model) {
          // Buscar el primer usuario admin para mostrar el perfil
          Usuario usuario = usuarioRepository.findByRol("ADMIN").stream().findFirst()
                    .orElse(new Usuario());

          // Pasamos el objeto usuario completo a la vista JSP
          model.addAttribute("usuario", usuario);

          // Devolvemos el nombre del archivo JSP
          return "admin/perfiladmin";
     }

     /**
      * Actualiza los datos del perfil administrativo.
      */
     @PostMapping("/perfiladmin/actualizar")
     public String handleAdminProfileUpdate(@RequestParam String nombreCompleto,
               @RequestParam String telefono,
               @RequestParam(required = false) String username,
               RedirectAttributes redirectAttributes) {
          try {
               // Si no se proporciona username, usamos el primer admin
               if (username == null || username.isEmpty()) {
                    Usuario adminUser = usuarioRepository.findByRol("ADMIN").stream().findFirst()
                              .orElseThrow(() -> new RuntimeException("No se encontró usuario admin"));
                    username = adminUser.getUsuario();
               }
               usuarioService.actualizarPerfil(username, nombreCompleto, telefono);
               redirectAttributes.addFlashAttribute("successMessage",
                         "¡Perfil administrativo actualizado correctamente!");
          } catch (Exception e) {
               redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar el perfil: " + e.getMessage());
          }
          return "redirect:/admin/perfiladmin";
     }

     /**
      * Cambia la contraseña del administrador.
      */
     @PostMapping("/perfiladmin/cambiar-contrasena")
     public String handleAdminChangePassword(@RequestParam String contrasenaActual,
               @RequestParam String nuevaContrasena,
               @RequestParam String confirmarContrasena,
               @RequestParam(required = false) String username,
               RedirectAttributes redirectAttributes) {
          if (!nuevaContrasena.equals(confirmarContrasena)) {
               redirectAttributes.addFlashAttribute("passwordError", "Las nuevas contraseñas no coinciden.");
               return "redirect:/admin/perfiladmin";
          }

          try {
               // Si no se proporciona username, usamos el primer admin
               if (username == null || username.isEmpty()) {
                    Usuario adminUser = usuarioRepository.findByRol("ADMIN").stream().findFirst()
                              .orElseThrow(() -> new RuntimeException("No se encontró usuario admin"));
                    username = adminUser.getUsuario();
               }
               usuarioService.cambiarContrasena(username, contrasenaActual, nuevaContrasena);
               redirectAttributes.addFlashAttribute("passwordSuccess",
                         "¡Contraseña administrativa actualizada correctamente!");
          } catch (Exception e) {
               redirectAttributes.addFlashAttribute("passwordError",
                         "Error al cambiar la contraseña: " + e.getMessage());
          }
          return "redirect:/admin/perfiladmin";
     }
}
