package com.example.demo.Repository;

import com.example.demo.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>, JpaSpecificationExecutor<Reserva> {

    /**
     * Cuenta cuántas personas ya han reservado en una fecha y franja específicas.
     */
    @Query("SELECT COALESCE(SUM(r.numeroPersonas), 0) FROM Reserva r WHERE r.fecha = :fecha AND r.franja.idFranja = :idFranja AND r.estado = 'CONFIRMADA'")
    Integer countPersonasByFechaAndFranja(@Param("fecha") LocalDate fecha, @Param("idFranja") Integer idFranja);

    /**
     * Cuenta cuántas mesas ya se han consumido en una fecha y franja.
     * Cada 5 personas ocupan 1 mesa (redondeo hacia arriba).
     */
    @Query("SELECT COALESCE(SUM(CEIL(r.numeroPersonas / 5.0)), 0) FROM Reserva r WHERE r.fecha = :fecha AND r.franja.idFranja = :idFranja AND r.estado = 'CONFIRMADA'")
    Integer countMesasByFechaAndFranja(@Param("fecha") LocalDate fecha, @Param("idFranja") Integer idFranja);

    /**
     * Busca si un usuario ya tiene una reserva para un día específico.
     */
    List<Reserva> findByUsuarioAndFecha(Usuario usuario, LocalDate fecha);

    /**
     * Busca todas las reservas para una fecha específica.
     */
    List<Reserva> findByFecha(LocalDate fecha);

    /**
     * Busca reservas por usuario
     */
    List<Reserva> findByUsuario(Usuario usuario);

    /**
     * Busca reservas por estado
     */
    List<Reserva> findByEstado(String estado);

    /**
     * Busca reservas futuras de un usuario
     */
    @Query("SELECT r FROM Reserva r WHERE r.usuario = :usuario AND r.fecha >= :fecha ORDER BY r.fecha ASC")
    List<Reserva> findReservasFuturasByUsuario(@Param("usuario") Usuario usuario, @Param("fecha") LocalDate fecha);

    /**
     * Cuenta las reservas por franja y fecha
     */
    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.franja.idFranja = :idFranja AND r.fecha = :fecha AND r.estado = 'CONFIRMADA'")
    Long countByFranjaAndFecha(@Param("idFranja") Integer idFranja, @Param("fecha") LocalDate fecha);

    /**
     * Cuenta las mesas reservadas para un tipo específico en una fecha y franja.
     */
    @Query("SELECT COALESCE(SUM(CEIL(r.numeroPersonas / 5.0)), 0) FROM Reserva r WHERE r.fecha = :fecha AND r.franja.idFranja = :idFranja AND r.mesa.tipoMesa.idTipoMesa = :idTipoMesa AND r.estado = 'CONFIRMADA'")
    Integer countMesasByFechaAndFranjaAndTipoMesa(@Param("fecha") LocalDate fecha, @Param("idFranja") Integer idFranja,
            @Param("idTipoMesa") Integer idTipoMesa);
}
