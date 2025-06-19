package com.example.demo.Controller;

import com.example.demo.facade.ReservaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * Controlador principal del sistema de reservas
 * 
 * Responsabilidades:
 * - Mostrar la página de inicio
 * - Proporcionar funcionalidad de consulta de disponibilidad
 * - Redirigir a las diferentes secciones del sistema
 */
@Controller
public class HomeController {

    @Autowired
    private ReservaFacade reservaFacade;

    /**
     * Muestra la página principal del sistema
     * 
     * @return Vista index.jsp con información básica
     */
    @GetMapping("/")
    public String mostrarPaginaInicio() {
        return "index"; // Retorna la vista index.jsp
    }

    /**
     * Procesa la consulta de disponibilidad desde la página principal
     * 
     * @param fecha Fecha seleccionada para la consulta (formato yyyy-MM-dd)
     * @param numeroPersonas Cantidad de personas para la reserva
     * @param model Modelo para pasar datos a la vista
     * @return Vista con los resultados de disponibilidad o error
     */
    @GetMapping("/consultar-disponibilidad")
    public String consultarDisponibilidad(
            @RequestParam("fecha") String fecha,
            @RequestParam("numeroPersonas") Integer numeroPersonas,
            Model model) {
        
        try {
            // Validar parámetros de entrada
            if (fecha == null || fecha.trim().isEmpty()) {
                model.addAttribute("error", "La fecha es obligatoria");
                return "index";
            }
            
            if (numeroPersonas == null || numeroPersonas <= 0) {
                model.addAttribute("error", "El número de personas debe ser mayor a 0");
                return "index";
            }

            // Convertir fecha string a LocalDate
            LocalDate fechaConsulta = LocalDate.parse(fecha);
            
            // Validar que la fecha no sea en el pasado
            if (fechaConsulta.isBefore(LocalDate.now())) {
                model.addAttribute("error", "No se puede consultar disponibilidad para fechas pasadas");
                return "index";
            }

            // Consultar disponibilidad a través del facade
            var disponibilidades = reservaFacade.consultarDisponibilidadPorFecha(fechaConsulta, numeroPersonas);
            
            // Preparar datos para la vista
            model.addAttribute("disponibilidades", disponibilidades);
            model.addAttribute("fecha", fecha);
            model.addAttribute("numeroPersonas", numeroPersonas);
            model.addAttribute("fechaFormateada", fechaConsulta);
            
            return "disponibilidad"; // Vista disponibilidad.jsp
            
        } catch (Exception e) {
            // Manejo de errores y retorno a la página principal con mensaje
            model.addAttribute("error", "Error al consultar disponibilidad: " + e.getMessage());
            return "index";
        }
    }
}