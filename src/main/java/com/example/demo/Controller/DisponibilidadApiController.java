package com.example.demo.Controller;

import com.example.demo.Services.ReservaService;
import com.example.demo.dto.FranjaHorariaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadApiController {
     private final ReservaService reservaService;

     @Autowired
     public DisponibilidadApiController(ReservaService reservaService) {
          this.reservaService = reservaService;
     }

     @GetMapping("/franjas")
     public List<FranjaHorariaDTO> getDisponibilidad(
               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
          if (fecha == null) {
               return Collections.emptyList();
          }
          return reservaService.getDisponibilidadPorFecha(fecha);
     }
}
