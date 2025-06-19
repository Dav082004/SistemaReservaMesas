package com.example.demo.Controller;

import com.example.demo.facade.AdminFacade;
import com.example.demo.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Controlador para la administración del sistema de reservas
 * 
 * Responsabilidades:
 * - Gestión de reservas (visualización, filtrado, actualización de estados)
 * - Generación de reportes y estadísticas
 * - Panel de administración general
 * 
 * Patrón MVC: Controlador que maneja toda la interacción administrativa,
 * delegando la lógica de negocio al AdminFacade
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminFacade adminFacade;

    /**
     * Muestra el panel principal de administración
     * Carga las reservas del día actual por defecto
     * 
     * @param model Modelo para pasar datos a la vista
     * @return Vista del panel de administración
     */
    @GetMapping
    public String mostrarPanelAdmin(Model model) {
        try {
            LocalDate hoy = LocalDate.now();
            
            // Obtener reservas del día actual
            List<ReservaDTO> reservasDelDia = adminFacade.buscarReservasPorFecha(hoy);
            
            // Obtener datos para los filtros
            model.addAttribute("reservas", reservasDelDia);
            model.addAttribute("fechaSeleccionada", hoy);
            model.addAttribute("tiposDeMesa", adminFacade.obtenerTiposMesa());
            model.addAttribute("franjasHorarias", adminFacade.obtenerFranjasHorarias());
            model.addAttribute("estadosDisponibles", adminFacade.obtenerEstadosDisponibles());
            
            return "admin/panel-admin"; // Vista admin/panel-admin.jsp
            
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar el panel de administración: " + e.getMessage());
            return "admin/panel-admin";
        }
    }

    /**
     * Busca y filtra reservas según los criterios especificados
     * 
     * @param fecha Fecha para filtrar las reservas
     * @param nombre Nombre del cliente (búsqueda parcial)
     * @param idTipoMesa ID del tipo de mesa para filtrar
     * @param idFranja ID de la franja horaria para filtrar
     * @param estado Estado de la reserva para filtrar
     * @param model Modelo para pasar datos a la vista
     * @return Vista con las reservas filtradas
     */
    @GetMapping("/reservas")
    public String buscarReservas(
            @RequestParam(value = "fecha", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "idTipoMesa", required = false) Integer idTipoMesa,
            @RequestParam(value = "idFranja", required = false) Integer idFranja,
            @RequestParam(value = "estado", required = false) String estado,
            Model model) {
        
        try {
            // Si no se especifica fecha, usar el día actual
            if (fecha == null) {
                fecha = LocalDate.now();
            }
            
            // Buscar reservas con los filtros aplicados
            List<ReservaDTO> reservas = adminFacade.buscarReservasConFiltros(
                fecha, nombre, idTipoMesa, idFranja, estado);
            
            // Preparar datos para la vista
            model.addAttribute("reservas", reservas);
            model.addAttribute("fechaSeleccionada", fecha);
            model.addAttribute("nombreSeleccionado", nombre);
            model.addAttribute("idTipoMesaSeleccionado", idTipoMesa);
            model.addAttribute("idFranjaSeleccionada", idFranja);
            model.addAttribute("estadoSeleccionado", estado);
            
            // Datos para los filtros
            model.addAttribute("tiposDeMesa", adminFacade.obtenerTiposMesa());
            model.addAttribute("franjasHorarias", adminFacade.obtenerFranjasHorarias());
            model.addAttribute("estadosDisponibles", adminFacade.obtenerEstadosDisponibles());
            
            return "admin/lista-reservas"; // Vista admin/lista-reservas.jsp
            
        } catch (Exception e) {
            model.addAttribute("error", "Error al buscar reservas: " + e.getMessage());
            model.addAttribute("tiposDeMesa", adminFacade.obtenerTiposMesa());
            model.addAttribute("franjasHorarias", adminFacade.obtenerFranjasHorarias());
            model.addAttribute("estadosDisponibles", adminFacade.obtenerEstadosDisponibles());
            return "admin/lista-reservas";
        }
    }

    /**
     * Actualiza el estado de una reserva específica
     * 
     * @param idReserva ID de la reserva a actualizar
     * @param nuevoEstado Nuevo estado para la reserva
     * @param redirectAttributes Atributos para mensajes flash
     * @return Redirección a la lista de reservas
     */
    @PostMapping("/reservas/{idReserva}/estado")
    public String actualizarEstadoReserva(
            @PathVariable Integer idReserva,
            @RequestParam("estado") String nuevoEstado,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Validar parámetros
            if (idReserva == null || idReserva <= 0) {
                redirectAttributes.addFlashAttribute("error", "ID de reserva inválido");
                return "redirect:/admin/reservas";
            }
            
            if (nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "El estado es obligatorio");
                return "redirect:/admin/reservas";
            }
            
            // Actualizar estado a través del facade
            adminFacade.actualizarEstadoReserva(idReserva, nuevoEstado);
            
            redirectAttributes.addFlashAttribute("mensaje", 
                "Estado de la reserva actualizado correctamente");
            
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error al actualizar la reserva: " + e.getMessage());
        }
        
        return "redirect:/admin/reservas";
    }

    /**
     * Muestra la página de estadísticas básicas del sistema
     * 
     * @param fechaInicio Fecha de inicio para el rango de estadísticas
     * @param fechaFin Fecha de fin para el rango de estadísticas
     * @param model Modelo para pasar datos a la vista
     * @return Vista de estadísticas
     */
    @GetMapping("/estadisticas")
    public String mostrarEstadisticas(
            @RequestParam(value = "fechaInicio", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            Model model) {
        
        try {
            // Valores por defecto si no se especifican fechas
            if (fechaInicio == null) {
                fechaInicio = LocalDate.now().minusMonths(1);
            }
            if (fechaFin == null) {
                fechaFin = LocalDate.now();
            }
            
            // Validar que la fecha de inicio no sea posterior a la fecha de fin
            if (fechaInicio.isAfter(fechaFin)) {
                model.addAttribute("error", 
                    "La fecha de inicio no puede ser posterior a la fecha de fin");
                model.addAttribute("fechaInicio", fechaInicio);
                model.addAttribute("fechaFin", fechaFin);
                return "admin/estadisticas";
            }
            
            // Obtener estadísticas básicas del facade
            Map<String, Object> estadisticas = adminFacade.generarEstadisticasBasicas(fechaInicio, fechaFin);
            
            model.addAttribute("estadisticas", estadisticas);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            
            return "admin/estadisticas"; // Vista admin/estadisticas.jsp
            
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar estadísticas: " + e.getMessage());
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            return "admin/estadisticas";
        }
    }

    /**
     * Genera y muestra un reporte de reservas para un período específico
     * 
     * @param fechaInicio Fecha de inicio del reporte
     * @param fechaFin Fecha de fin del reporte
     * @param estado Estado de las reservas a incluir (opcional)
     * @param model Modelo para pasar datos a la vista
     * @return Vista del reporte generado
     */
    @GetMapping("/reportes")
    public String generarReporte(
            @RequestParam(value = "fechaInicio", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam(value = "estado", required = false) String estado,
            Model model) {
        
        try {
            // Valores por defecto
            if (fechaInicio == null) {
                fechaInicio = LocalDate.now().minusWeeks(1);
            }
            if (fechaFin == null) {
                fechaFin = LocalDate.now();
            }
            
            // Generar reporte a través del facade
            List<ReservaDTO> reservasReporte = adminFacade.generarReporte(fechaInicio, fechaFin, estado);
            
            model.addAttribute("reservas", reservasReporte);
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            model.addAttribute("estadoFiltro", estado);
            model.addAttribute("estadosDisponibles", adminFacade.obtenerEstadosDisponibles());
            
            return "admin/reportes"; // Vista admin/reportes.jsp
            
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar reporte: " + e.getMessage());
            model.addAttribute("fechaInicio", fechaInicio);
            model.addAttribute("fechaFin", fechaFin);
            model.addAttribute("estadoFiltro", estado);
            model.addAttribute("estadosDisponibles", adminFacade.obtenerEstadosDisponibles());
            return "admin/reportes";
        }
    }
}