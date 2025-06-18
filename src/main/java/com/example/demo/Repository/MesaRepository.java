package com.example.demo.Repository;

import com.example.demo.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {

    /**
     * Obtiene mesas por tipo de mesa
     */
    List<Mesa> findByTipoMesa(TipoMesa tipoMesa);

    /**
     * Obtiene mesas por ID de tipo de mesa
     */
    @Query("SELECT m FROM Mesa m WHERE m.tipoMesa.idTipoMesa = :idTipoMesa")
    List<Mesa> findByTipoMesaId(@Param("idTipoMesa") Integer idTipoMesa);

    /**
     * Cuenta las mesas por tipo
     */
    Long countByTipoMesa(TipoMesa tipoMesa);

    /**
     * Obtiene la primera mesa disponible por tipo
     */
    @Query("SELECT m FROM Mesa m WHERE m.tipoMesa.idTipoMesa = :idTipoMesa ORDER BY m.idMesa ASC")
    List<Mesa> findMesasByTipoMesaOrderById(@Param("idTipoMesa") Integer idTipoMesa);
}
