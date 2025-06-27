package com.example.demo.Repository;

import com.example.demo.Entities.Reserva;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Set;

@Repository
public class ReservaRepository {
    private final JdbcTemplate jdbcTemplate;
    public ReservaRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }
    
    public void save(Reserva reserva) {
        jdbcTemplate.update("INSERT INTO Reserva (nombreCliente, correoCliente, telefonoCliente, fecha, numeroPersonas, estado, comentarios, idFranja, idMesa, idUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                reserva.getNombreCliente(), reserva.getCorreoCliente(), reserva.getTelefonoCliente(), reserva.getFecha(), reserva.getNumeroPersonas(), reserva.getEstado(), reserva.getComentarios(), reserva.getIdFranja(), reserva.getIdMesa(), reserva.getIdUsuario());
    }
    
    public int countByUsuarioAndFecha(int idUsuario, LocalDate fecha) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Reserva WHERE idUsuario = ? AND fecha = ?", Integer.class, idUsuario, fecha);
        return (count != null) ? count : 0;
    }
    
    public Set<Integer> findMesasOcupadasIds(LocalDate fecha, int idFranja) {
        return Set.copyOf(jdbcTemplate.queryForList("SELECT idMesa FROM Reserva WHERE fecha = ? AND idFranja = ?", Integer.class, fecha, idFranja));
    }
}