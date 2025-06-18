package com.example.demo.dao;

import com.example.demo.Entities.Mesa;
import com.example.demo.Entities.TipoMesa;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz DAO para la entidad Mesa
 * Define las operaciones de acceso a datos para mesas
 */
public interface MesaDAO {

    /**
     * Guarda una mesa en la base de datos
     */
    Mesa save(Mesa mesa);

    /**
     * Busca una mesa por su ID
     */
    Optional<Mesa> findById(Integer id);

    /**
     * Obtiene todas las mesas
     */
    List<Mesa> findAll();

    /**
     * Obtiene mesas por tipo de mesa
     */
    List<Mesa> findByTipoMesa(TipoMesa tipoMesa);

    /**
     * Obtiene mesas disponibles por tipo
     */
    List<Mesa> findMesasDisponiblesByTipo(Integer idTipoMesa);

    /**
     * Cuenta las mesas por tipo
     */
    Long countByTipoMesa(TipoMesa tipoMesa);

    /**
     * Obtiene una mesa disponible por tipo (para asignar a una reserva)
     */
    Optional<Mesa> findFirstAvailableByTipo(Integer idTipoMesa);

    /**
     * Actualiza una mesa
     */
    Mesa update(Mesa mesa);

    /**
     * Elimina una mesa por su ID
     */
    void deleteById(Integer id);
}
