package com.example.demo.dao.impl;

import com.example.demo.dao.UsuarioDAO;
import com.example.demo.Entities.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del DAO para Usuario usando Spring Data JPA
 */
@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDAOImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> findByUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario);
    }

    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findByRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }

    @Override
    public boolean existsByUsuario(String usuario) {
        return usuarioRepository.existsByUsuario(usuario);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    @Override
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
