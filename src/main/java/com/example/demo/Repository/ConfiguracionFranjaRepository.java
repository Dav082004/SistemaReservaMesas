package com.example.demo.Repository;

import com.example.demo.Entities.ConfiguracionFranja;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ConfiguracionFranjaRepository {
     private final JdbcTemplate jdbcTemplate;

     public ConfiguracionFranjaRepository(JdbcTemplate jdbcTemplate) {
          this.jdbcTemplate = jdbcTemplate;
     }

     public List<ConfiguracionFranja> findAll() {
          return jdbcTemplate.query("SELECT * FROM ConfiguracionFranja ORDER BY idFranja", (rs, rowNum) -> {
               ConfiguracionFranja franja = new ConfiguracionFranja();
               franja.setIdFranja(rs.getInt("idFranja"));
               franja.setFranjaHoraria(rs.getString("franjaHoraria"));
               franja.setCapacidadMaxima(rs.getInt("capacidadMaxima"));
               franja.setCantidadMesas(rs.getInt("cantidadMesas"));
               return franja;
          });
     }
}
