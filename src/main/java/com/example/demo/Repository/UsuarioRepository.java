package com.example.demo.Repository;

import com.example.demo.Entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UsuarioRowMapper rowMapper = new UsuarioRowMapper();

    @Autowired
    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class UsuarioRowMapper implements RowMapper<Usuario> {
        // ... (el mapRow sigue igual que antes)
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setNombreCompleto(rs.getString("nombreCompleto"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setUsuario(rs.getString("usuario"));
            usuario.setContrasena(rs.getString("contrasena"));
            usuario.setRol(rs.getString("rol"));
            return usuario;
        }
    }

    public Optional<Usuario> findById(int id) {
        String sql = "SELECT * FROM Usuarios WHERE idUsuario = ?";
        try {
            Usuario usuario = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
            return Optional.ofNullable(usuario);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Usuario> findByUsuario(String username) {
        String sql = "SELECT * FROM Usuarios WHERE usuario = ?";
        try {
            Usuario usuario = jdbcTemplate.queryForObject(sql, new Object[]{username}, rowMapper);
            return Optional.ofNullable(usuario);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Usuario> findByCorreo(String correo) {
        String sql = "SELECT * FROM Usuarios WHERE correo = ?";
        try {
            Usuario usuario = jdbcTemplate.queryForObject(sql, new Object[]{correo}, rowMapper);
            return Optional.ofNullable(usuario);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void save(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nombreCompleto, correo, telefono, usuario, contrasena, rol) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                usuario.getNombreCompleto(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getUsuario(),
                usuario.getContrasena(),
                usuario.getRol());
    }
    
    public void update(Usuario usuario) {
        String sql = "UPDATE Usuarios SET nombreCompleto = ?, telefono = ?, contrasena = ? WHERE idUsuario = ?";
        jdbcTemplate.update(sql,
                usuario.getNombreCompleto(),
                usuario.getTelefono(),
                usuario.getContrasena(),
                usuario.getIdUsuario());
    }
}
