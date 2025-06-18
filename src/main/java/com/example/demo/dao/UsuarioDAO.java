package com.example.demo.dao;

import com.example.demo.Entities.Usuario;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz DAO para la entidad Usuario
 * Define las operaciones de acceso a datos para usuarios
 */
public interface UsuarioDAO {

    /**
     * Guarda un usuario en la base de datos
     */
    Usuario save(Usuario usuario);

    /**
     * Busca un usuario por su ID
     */
    Optional<Usuario> findById(Integer id);

    /**
     * Busca un usuario por su nombre de usuario
     */
    Optional<Usuario> findByUsuario(String usuario);

    /**
     * Busca un usuario por su correo electrónico
     */
    Optional<Usuario> findByCorreo(String correo);

    /**
     * Obtiene todos los usuarios
     */
    List<Usuario> findAll();

    /**
     * Obtiene usuarios por rol
     */
    List<Usuario> findByRol(String rol);

    /**
     * Verifica si existe un usuario con el nombre de usuario dado
     */
    boolean existsByUsuario(String usuario);

    /**
     * Verifica si existe un usuario con el correo dado
     */
    boolean existsByCorreo(String correo);

    /**
     * Elimina un usuario por su ID
     */
    void deleteById(Integer id);

    /**
     * Actualiza un usuario
     */
    Usuario update(Usuario usuario);
}
