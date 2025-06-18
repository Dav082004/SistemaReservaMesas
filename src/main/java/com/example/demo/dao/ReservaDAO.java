package com.example.demo.dao;

import com.example.demo.Entities.Reserva;
import com.example.demo.Entities.Usuario;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz DAO para la entidad Reserva
 * Define las operaciones de acceso a datos para reservas
 */
public interface ReservaDAO {

    /**
     * Guarda una reserva en la base de datos
     */
    Reserva save(Reserva reserva);

    /**
     * Busca una reserva por su ID
     */
    Optional<Reserva> findById(Integer id);

    /**
     * Obtiene todas las reservas
     */
    List<Reserva> findAll();

    /**
     * Obtiene reservas por usuario
     */
    List<Reserva> findByUsuario(Usuario usuario);

    /**
     * Obtiene reservas por fecha
     */
    List<Reserva> findByFecha(LocalDate fecha);

    /**
     * Obtiene reservas por estado
     */
    List<Reserva> findByEstado(String estado);

    /**
     * Obtiene reservas por usuario y fecha
     */
    List<Reserva> findByUsuarioAndFecha(Usuario usuario, LocalDate fecha);

    /**
     * Obtiene reservas por franja horaria y fecha
     */
    List<Reserva> findByFranjaAndFecha(Integer idFranja, LocalDate fecha);

    /**
     * Obtiene reservas futuras de un usuario
     */
    List<Reserva> findReservasFuturasByUsuario(Usuario usuario);

    /**
     * Cuenta las reservas por franja y fecha
     */
    Long countByFranjaAndFecha(Integer idFranja, LocalDate fecha);

    /**
     * Actualiza una reserva
     */
    Reserva update(Reserva reserva);

    /**
     * Elimina una reserva por su ID
     */
    void deleteById(Integer id);
}
