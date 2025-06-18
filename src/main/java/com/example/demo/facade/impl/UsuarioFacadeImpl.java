package com.example.demo.facade.impl;

import com.example.demo.facade.UsuarioFacade;
import com.example.demo.dao.UsuarioDAO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UsuarioRegistroDto;
import com.example.demo.Entities.Usuario;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Implementación del Facade para usuarios
 * Contiene la lógica de negocio relacionada con usuarios
 */
@Service
public class UsuarioFacadeImpl implements UsuarioFacade {

    private final UsuarioDAO usuarioDAO;

    public UsuarioFacadeImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public Usuario registrarUsuario(UsuarioRegistroDto usuarioRegistroDto) {
        // Validaciones de negocio
        validarDatosRegistro(usuarioRegistroDto);

        // Verificar que el usuario no exista
        if (usuarioDAO.existsByUsuario(usuarioRegistroDto.getUsuario())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        if (usuarioDAO.existsByCorreo(usuarioRegistroDto.getCorreo())) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }

        // Crear nueva entidad Usuario
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(usuarioRegistroDto.getNombreCompleto());
        usuario.setCorreo(usuarioRegistroDto.getCorreo());
        usuario.setTelefono(usuarioRegistroDto.getTelefono());
        usuario.setUsuario(usuarioRegistroDto.getUsuario());
        usuario.setContrasena(usuarioRegistroDto.getContrasena()); // En producción debería hashearse
        usuario.setRol("USER"); // Por defecto, todos los registros nuevos son USER

        return usuarioDAO.save(usuario);
    }

    @Override
    public Usuario autenticarUsuario(LoginDTO loginDTO) {
        Optional<Usuario> usuarioOpt = usuarioDAO.findByUsuario(loginDTO.getUsuario());

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        // Verificar contraseña (en producción debería usar hash)
        if (!usuario.getContrasena().equals(loginDTO.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public boolean existeUsuario(String usuario) {
        return usuarioDAO.existsByUsuario(usuario);
    }

    @Override
    public boolean existeCorreo(String correo) {
        return usuarioDAO.existsByCorreo(correo);
    }

    @Override
    public boolean validarCredenciales(String usuario, String contrasena) {
        try {
            LoginDTO loginDTO = new LoginDTO(usuario, contrasena);
            autenticarUsuario(loginDTO);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioDAO.update(usuario);
    }

    /**
     * Valida los datos de registro
     */
    private void validarDatosRegistro(UsuarioRegistroDto dto) {
        if (dto.getNombreCompleto() == null || dto.getNombreCompleto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }

        if (dto.getCorreo() == null || dto.getCorreo().trim().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio");
        }

        if (dto.getUsuario() == null || dto.getUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario es obligatorio");
        }

        if (dto.getContrasena() == null || dto.getContrasena().trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        // Validar formato de correo
        if (!dto.getCorreo().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido");
        }

        // Validar longitud de contraseña
        if (dto.getContrasena().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }
    }
}
