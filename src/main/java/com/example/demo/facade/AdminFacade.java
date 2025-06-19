package com.example.demo.facade;

import com.example.demo.dto.ReservaDTO;
import com.example.demo.Entities.TipoMesa;
import com.example.demo.Entities.ConfiguracionFranja;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Facade para las operaciones administrativas del sistema
 * 
 * Responsabilidades:
 * - Gestión de reservas desde perspectiva administrativa
 * - Generación de reportes básicos
 * - Coordinación de operaciones complejas de administración
 * 
 * Patrón Facade: Simplifica la interacción con múltiples services y DAOs
 * para operaciones administrativas
 */
public interface AdminFacade {

    /**
     * Busca reservas para una fecha específica
     * 
     * @param fecha Fecha para la búsqueda de reservas
     * @return Lista de reservas en formato DTO
     */
    List<ReservaDTO> buscarReservasPorFecha(LocalDate fecha);

    /**
     * Busca reservas aplicando múltiples filtros
     * 
     * @param fecha Fecha de las reservas
     * @param nombreCliente Nombre del cliente (búsqueda parcial)
     * @param idTipoMesa ID del tipo de mesa
     * @param idFranja ID de la franja horaria
     * @param estado Estado de la reserva
     * @return Lista de reservas filtradas en formato DTO
     */
    List<ReservaDTO> buscarReservasConFiltros(LocalDate fecha, String nombreCliente, 
                                            Integer idTipoMesa, Integer idFranja, String estado);

    /**
     * Actualiza el estado de una reserva específica
     * 
     * @param idReserva ID de la reserva a actualizar
     * @param nuevoEstado Nuevo estado para la reserva
     * @throws IllegalArgumentException si la reserva no existe o el estado es inválido
     */
    void actualizarEstadoReserva(Integer idReserva, String nuevoEstado);

    /**
     * Genera estadísticas básicas del sistema para un período específico
     * Retorna un mapa con datos simples para mostrar en la vista
     * 
     * @param fechaInicio Fecha de inicio del período
     * @param fechaFin Fecha de fin del período
     * @return Mapa con estadísticas básicas (totalReservas, confirmadas, etc.)
     */
    Map<String, Object> generarEstadisticasBasicas(LocalDate fechaInicio, LocalDate fechaFin);

    /**
     * Genera un reporte de reservas para un período y estado específicos
     * 
     * @param fechaInicio Fecha de inicio del reporte
     * @param fechaFin Fecha de fin del reporte
     * @param estado Estado de las reservas a incluir (opcional)
     * @return Lista de reservas que cumplen los criterios
     */
    List<ReservaDTO> generarReporte(LocalDate fechaInicio, LocalDate fechaFin, String estado);

    /**
     * Obtiene todos los tipos de mesa disponibles en el sistema
     * 
     * @return Lista de tipos de mesa
     */
    List<TipoMesa> obtenerTiposMesa();

    /**
     * Obtiene todas las franjas horarias configuradas en el sistema
     * 
     * @return Lista de franjas horarias
     */
    List<ConfiguracionFranja> obtenerFranjasHorarias();

    /**
     * Obtiene la lista de estados disponibles para las reservas
     * 
     * @return Lista de strings con los estados válidos
     */
    List<String> obtenerEstadosDisponibles();
}
