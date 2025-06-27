package com.example.demo.Services;

import com.example.demo.dto.FranjaHorariaDTO;
import com.example.demo.dto.ReservaFormDTO;
import com.example.demo.Entities.Mesa;
import com.example.demo.Entities.Reserva;
import com.example.demo.Entities.Usuario;
import com.example.demo.Repository.MesaRepository;
import com.example.demo.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final MesaRepository mesaRepository;
    private static final Map<Integer, String> FRANJAS_HORARIAS = Map.of(1, "10:00 - 12:00", 2, "12:00 - 14:00", 3, "14:00 - 16:00", 4, "16:00 - 18:00", 5, "18:00 - 20:00", 6, "20:00 - 22:00", 7, "22:00 - 00:00");

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, MesaRepository mesaRepository) {
        this.reservaRepository = reservaRepository;
        this.mesaRepository = mesaRepository;
    }

    public List<FranjaHorariaDTO> getDisponibilidadPorFecha(LocalDate fecha) {
        // Permitir franjas para cualquier fecha del 2025
        if (fecha.getYear() != 2025) {
            return Collections.emptyList();
        }
        List<Mesa> todasLasMesas = mesaRepository.findAll();
        return FRANJAS_HORARIAS.entrySet().stream().map(franjaEntry -> {
            int idFranja = franjaEntry.getKey();
            Set<Integer> mesasOcupadasIds = reservaRepository.findMesasOcupadasIds(fecha, idFranja);
            int mesasDisponibles = todasLasMesas.size() - mesasOcupadasIds.size();
            boolean casiLleno = mesasOcupadasIds.size() > (todasLasMesas.size() * 0.7);
            return new FranjaHorariaDTO(idFranja, franjaEntry.getValue(), mesasDisponibles, casiLleno);
        }).collect(Collectors.toList());
    }

    @Transactional
    public void crearReserva(ReservaFormDTO reservaDTO, Usuario usuario) throws Exception {
        if (reservaRepository.countByUsuarioAndFecha(usuario.getIdUsuario(), reservaDTO.getFecha()) > 0) {
            throw new Exception("Ya tienes una reserva para este día. Solo se permite una reserva diaria por usuario.");
        }
        Set<Integer> mesasOcupadasIds = reservaRepository.findMesasOcupadasIds(reservaDTO.getFecha(), reservaDTO.getIdFranja());
        Optional<Mesa> mesaAsignadaOpt = mesaRepository.findAll().stream()
                .filter(mesa -> mesa.getIdTipoMesa() == reservaDTO.getIdTipoMesa() && !mesasOcupadasIds.contains(mesa.getIdMesa()))
                .findFirst();

        if (mesaAsignadaOpt.isEmpty()) {
            throw new Exception("Lo sentimos, no hay mesas de ese tipo disponibles en el horario seleccionado.");
        }
        
        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setNombreCliente(usuario.getNombreCompleto());
        nuevaReserva.setCorreoCliente(usuario.getCorreo());
        nuevaReserva.setTelefonoCliente(usuario.getTelefono());
        nuevaReserva.setIdUsuario(usuario.getIdUsuario());
        nuevaReserva.setFecha(reservaDTO.getFecha());
        nuevaReserva.setIdFranja(reservaDTO.getIdFranja());
        nuevaReserva.setNumeroPersonas(reservaDTO.getNumeroPersonas());
        nuevaReserva.setComentarios(reservaDTO.getComentarios());
        nuevaReserva.setIdMesa(mesaAsignadaOpt.get().getIdMesa());
        nuevaReserva.setEstado("POR_CONFIRMAR");
        reservaRepository.save(nuevaReserva);
    }
}