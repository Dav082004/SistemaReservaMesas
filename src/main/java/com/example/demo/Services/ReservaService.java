package com.example.demo.Services;

import com.example.demo.dto.FranjaHorariaDTO;
import com.example.demo.dto.ReservaFormDTO;
import com.example.demo.Entities.Mesa;
import com.example.demo.Entities.Reserva;
import com.example.demo.Entities.Usuario;
import com.example.demo.Repository.MesaRepository;
import com.example.demo.Repository.ReservaRepository;
import com.example.demo.Repository.ConfiguracionFranjaRepository;
import com.example.demo.Entities.ConfiguracionFranja;
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
    private final ConfiguracionFranjaRepository configuracionFranjaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, MesaRepository mesaRepository,
            ConfiguracionFranjaRepository configuracionFranjaRepository) {
        this.reservaRepository = reservaRepository;
        this.mesaRepository = mesaRepository;
        this.configuracionFranjaRepository = configuracionFranjaRepository;
    }

    public List<FranjaHorariaDTO> getDisponibilidadPorFecha(LocalDate fecha) {
        // Permitir franjas para cualquier fecha del 2025
        if (fecha.getYear() != 2025) {
            return Collections.emptyList();
        }
        List<ConfiguracionFranja> franjasBD = configuracionFranjaRepository.findAll();
        List<Mesa> todasLasMesas = mesaRepository.findAll();
        System.out.println("Mesas totales en BD: " + todasLasMesas.size());
        List<FranjaHorariaDTO> resultado = new ArrayList<>();
        for (ConfiguracionFranja franja : franjasBD) {
            int idFranja = franja.getIdFranja();
            Set<Integer> mesasOcupadasIds = reservaRepository.findMesasOcupadasIds(fecha, idFranja);
            int mesasDisponibles = franja.getCantidadMesas() - mesasOcupadasIds.size();
            boolean casiLleno = mesasOcupadasIds.size() > (franja.getCantidadMesas() * 0.7);
            resultado.add(new FranjaHorariaDTO(idFranja, franja.getFranjaHoraria(), mesasDisponibles, casiLleno));
        }
        return resultado;
    }

    @Transactional
    public void crearReserva(ReservaFormDTO reservaDTO, Usuario usuario) throws Exception {
        if (reservaRepository.countByUsuarioAndFecha(usuario.getIdUsuario(), reservaDTO.getFecha()) > 0) {
            throw new Exception("Ya tienes una reserva para este día. Solo se permite una reserva diaria por usuario.");
        }
        Set<Integer> mesasOcupadasIds = reservaRepository.findMesasOcupadasIds(reservaDTO.getFecha(),
                reservaDTO.getIdFranja());
        Optional<Mesa> mesaAsignadaOpt = mesaRepository.findAll().stream()
                .filter(mesa -> mesa.getIdTipoMesa() == reservaDTO.getIdTipoMesa()
                        && !mesasOcupadasIds.contains(mesa.getIdMesa()))
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