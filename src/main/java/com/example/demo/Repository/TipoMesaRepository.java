package com.example.demo.Repository;

import com.example.demo.Entities.TipoMesa;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TipoMesaRepository {
    private final JdbcTemplate jdbcTemplate;
    public TipoMesaRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }
    public List<TipoMesa> findAll() {
        return jdbcTemplate.query("SELECT * FROM TipoMesa", (rs, rowNum) -> {
            TipoMesa tipoMesa = new TipoMesa();
            tipoMesa.setIdTipoMesa(rs.getInt("idTipoMesa"));
            tipoMesa.setNombre(rs.getString("nombre"));
            tipoMesa.setDescripcion(rs.getString("descripcion"));
            return tipoMesa;
        });
    }
}