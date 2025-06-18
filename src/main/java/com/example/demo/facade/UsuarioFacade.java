package com.example.demo.facade;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UsuarioRegistroDto;
import com.example.demo.Entities.Usuario;

/**
 * Facade para las operaciones de autenticación y gestión de usuarios
 * Encapsula la lógica de negocio relacionada con usuarios
 */
public interface UsuarioFacade {

    /**
     * Registra un nuevo usuario
     */
    Usuario registrarUsuario(UsuarioRegistroDto usuarioRegistroDto);

    /**
     * Autentica un usuario
     */
    Usuario autenticarUsuario(LoginDTO loginDTO);

    /**
     * Obtiene un usuario por su ID
     */
    Usuario obtenerUsuarioPorId(Integer id);

    /**
     * Verifica si un nombre de usuario ya existe
     */
    boolean existeUsuario(String usuario);

    /**
     * Verifica si un correo ya existe
     */
    boolean existeCorreo(String correo);

    /**
     * Valida las credenciales de un usuario
     */
    boolean validarCredenciales(String usuario, String contrasena);

    /**
     * Actualiza la información de un usuario
     */
    Usuario actualizarUsuario(Usuario usuario);
}
