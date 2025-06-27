package com.example.demo.Services;

import com.example.demo.dto.PerfilDatosDTO;
import com.example.demo.Entities.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Valida las credenciales de un usuario.
     * @return Un Optional con el objeto Usuario si el login es exitoso, o un Optional vacío si falla.
     */
    public Optional<Usuario> validarLogin(String username, String password) {
        Optional<Usuario> optUsuario = usuarioRepository.findByUsuario(username);
        
        // ADVERTENCIA DE SEGURIDAD: Comparación de contraseña en texto plano solo para desarrollo.
        if (optUsuario.isPresent() && optUsuario.get().getContrasena().equals(password)) {
            return optUsuario;
        }
        
        return Optional.empty();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param usuario El objeto Usuario a registrar, ya mapeado desde el DTO.
     * @throws Exception si el nombre de usuario o correo ya existen.
     */
    public void registrarUsuario(Usuario usuario) throws Exception {
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            throw new Exception("El nombre de usuario '" + usuario.getUsuario() + "' ya está en uso.");
        }
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new Exception("El correo electrónico '" + usuario.getCorreo() + "' ya está registrado.");
        }

        // --- Asignación del rol por defecto ---
        // Esta es la regla de negocio: todo usuario nuevo es un "USUARIO".
        usuario.setRol("USUARIO");
        
        // ADVERTENCIA DE SEGURIDAD: Aquí se debería hashear la contraseña antes de guardarla.
        
        usuarioRepository.save(usuario);
    }

    // --- NUEVOS MÉTODOS PARA PERFIL ---

    public Usuario actualizarDatosPerfil(int idUsuario, PerfilDatosDTO datosDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        usuario.setNombreCompleto(datosDTO.getNombreCompleto());
        usuario.setTelefono(datosDTO.getTelefono());
        
        usuarioRepository.update(usuario);
        return usuario;
    }

    public void cambiarContrasena(int idUsuario, String contrasenaActual, String nuevaContrasena) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // ADVERTENCIA DE SEGURIDAD: Comparación en texto plano.
        if (!usuario.getContrasena().equals(contrasenaActual)) {
            throw new Exception("La contraseña actual es incorrecta.");
        }
        
        usuario.setContrasena(nuevaContrasena);
        // ADVERTENCIA DE SEGURIDAD: Aquí se debería hashear la nueva contraseña.
        
        usuarioRepository.update(usuario);
    }

}