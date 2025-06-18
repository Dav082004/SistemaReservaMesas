package com.example.demo.Repository;

import com.example.demo.Entities.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Busca un usuario por su nombre de usuario.
     * Spring Data JPA creará la implementación automáticamente.
     * 
     * @param usuario El nombre de usuario a buscar.
     * @return Un Optional que contiene el usuario si se encuentra.
     */
    Optional<Usuario> findByUsuario(String usuario);

    /**
     * Busca un usuario por su correo electrónico.
     * Útil para verificar si un correo ya está registrado.
     * 
     * @param correo El correo a buscar.
     * @return Un Optional que contiene el usuario si se encuentra.
     */
    Optional<Usuario> findByCorreo(String correo);

    /**
     * Busca usuarios por su rol.
     * 
     * @param rol El rol a buscar.
     * @return Una lista de usuarios con el rol especificado.
     */
    List<Usuario> findByRol(String rol);

    /**
     * Verifica si existe un usuario con el nombre de usuario dado.
     * 
     * @param usuario El nombre de usuario a verificar.
     * @return true si existe, false en caso contrario.
     */
    boolean existsByUsuario(String usuario);

    /**
     * Verifica si existe un usuario con el correo dado.
     * 
     * @param correo El correo a verificar.
     * @return true si existe, false en caso contrario.
     */
    boolean existsByCorreo(String correo);
}