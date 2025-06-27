package com.example.demo.Controller;

import com.example.demo.dto.ReservaFormDTO;
import com.example.demo.dto.FranjaHorariaDTO;
import com.example.demo.Entities.TipoMesa;
import com.example.demo.Entities.Usuario;
import com.example.demo.Services.ReservaService;
import com.example.demo.Services.TipoMesaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
public class ReservaController {
    private final ReservaService reservaService;
    private final TipoMesaService tipoMesaService;

    @Autowired
    public ReservaController(ReservaService reservaService, TipoMesaService tipoMesaService) {
        this.reservaService = reservaService;
        this.tipoMesaService = tipoMesaService;
    }

    @GetMapping("/reservaciones")
    public String mostrarFormularioReservaciones(Model model) {
        model.addAttribute("fechaMin", LocalDate.now().toString());
        model.addAttribute("fechaMax", "2025-12-31");
        model.addAttribute("tiposDeMesa", tipoMesaService.obtenerTodosLosTipos());
        return "reservaciones";
    }

    @PostMapping("/reservaciones")
    public String procesarReserva(@ModelAttribute ReservaFormDTO reservaDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) return "redirect:/login";
        try {
            reservaService.crearReserva(reservaDTO, usuario);
            redirectAttributes.addFlashAttribute("successMessage", "¡Tu reserva ha sido registrada con éxito!");
            return "redirect:/perfil";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/reservaciones";
        }
    }

    @RestController
    @RequestMapping("/api/disponibilidad")
    public static class DisponibilidadApiController {
        private final ReservaService reservaService;

        @Autowired
        public DisponibilidadApiController(ReservaService reservaService) { this.reservaService = reservaService; }

        @GetMapping("/franjas")
        public List<FranjaHorariaDTO> getDisponibilidad(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
            if (fecha == null) {
                return Collections.emptyList();
            }
            return reservaService.getDisponibilidadPorFecha(fecha);
        }
    }
}