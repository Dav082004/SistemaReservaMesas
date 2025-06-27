package com.example.demo.Repository;

import com.example.demo.Entities.Mesa;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MesaRepository {
    private final JdbcTemplate jdbcTemplate;
    public MesaRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }
    public List<Mesa> findAll() {
        return jdbcTemplate.query("SELECT * FROM Mesa", (rs, rowNum) -> {
            Mesa mesa = new Mesa();
            mesa.setIdMesa(rs.getInt("idMesa"));
            mesa.setIdTipoMesa(rs.getInt("idTipoMesa"));
            mesa.setCapacidad(rs.getInt("capacidad"));
            return mesa;
        });
    }
}