package com.example.demo.facade;

import com.example.demo.dto.ReservaFormDTO;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.Entities.Reserva;
import com.example.demo.Entities.Usuario;
import com.example.demo.Entities.TipoMesa;
import com.example.demo.Entities.ConfiguracionFranja;
import java.time.LocalDate;
import java.util.List;

/**
 * Facade para las operaciones de reservas
 * Encapsula la lógica de negocio relacionada con reservas
 */
public interface ReservaFacade {

    /**
     * Crea una nueva reserva para un usuario autenticado
     */
    Reserva crearReserva(ReservaFormDTO reservaFormDTO, Usuario usuario);

    /**
     * Obtiene las reservas futuras de un usuario
     */
    List<ReservaDTO> obtenerReservasFuturasUsuario(Usuario usuario);

    /**
     * Obtiene todas las reservas de un usuario
     */
    List<ReservaDTO> obtenerReservasUsuario(Usuario usuario);

    /**
     * Cancela una reserva
     */
    boolean cancelarReserva(Integer idReserva, Usuario usuario);

    /**
     * Verifica la disponibilidad para una fecha y franja
     */
    boolean verificarDisponibilidad(LocalDate fecha, Integer idFranja, Integer numeroPersonas, Integer idTipoMesa);

    /**
     * Calcula el número de mesas necesarias según el número de personas
     */
    int calcularMesasNecesarias(Integer numeroPersonas);

    /**
     * Obtiene los tipos de mesa disponibles
     */
    List<TipoMesa> obtenerTiposMesa();

    /**
     * Obtiene las franjas horarias disponibles
     */
    List<ConfiguracionFranja> obtenerFranjasHorarias();

    /**
     * Convierte una entidad Reserva a DTO
     */
    ReservaDTO convertirAReservaDTO(Reserva reserva);

    /**
     * Valida los datos de una reserva
     */
    void validarDatosReserva(ReservaFormDTO reservaFormDTO);
}
