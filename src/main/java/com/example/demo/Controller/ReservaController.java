package com.example.demo.Controller;

import com.example.demo.dto.ReservaFormDTO;
import com.example.demo.dto.FranjaHorariaDTO;
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
    public String mostrarFormularioReservaciones(@RequestParam(value = "fecha", required = false) String fechaStr,
            Model model, HttpSession session) {
        // Autocompletar datos del usuario
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        ReservaFormDTO reservaForm = new ReservaFormDTO();
        if (usuario != null) {
            reservaForm.setNombreCliente(usuario.getNombreCompleto());
            reservaForm.setTelefonoCliente(usuario.getTelefono());
            reservaForm.setCorreoCliente(usuario.getCorreo());
        }
        List<FranjaHorariaDTO> franjas = Collections.emptyList();
        System.out.println("Fecha recibida: " + fechaStr);
        if (fechaStr != null && !fechaStr.isEmpty()) {
            LocalDate fecha = LocalDate.parse(fechaStr);
            franjas = reservaService.getDisponibilidadPorFecha(fecha);
            reservaForm.setFecha(fecha);
            System.out.println("Franjas encontradas: " + franjas.size());
        }
        model.addAttribute("reservaForm", reservaForm);
        model.addAttribute("franjasHorarias", franjas);
        model.addAttribute("fechaSeleccionada", fechaStr);
        model.addAttribute("fechaMin", "2025-01-01");
        model.addAttribute("fechaMax", "2025-12-31");
        model.addAttribute("tiposDeMesa", tipoMesaService.obtenerTodosLosTipos());
        return "reservaciones";
    }

    @PostMapping("/reservaciones")
    public String procesarReserva(@ModelAttribute ReservaFormDTO reservaDTO, HttpSession session,
            RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null)
            return "redirect:/login";
        try {
            reservaService.crearReserva(reservaDTO, usuario);
            redirectAttributes.addFlashAttribute("successMessage", "¡Tu reserva ha sido registrada con éxito!");
            return "redirect:/perfil";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/reservaciones";
        }
    }
}