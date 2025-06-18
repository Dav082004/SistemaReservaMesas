package com.example.demo.facade.impl;

import com.example.demo.facade.ReservaFacade;
import com.example.demo.dao.ReservaDAO;
import com.example.demo.dao.MesaDAO;
import com.example.demo.dto.ReservaFormDTO;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.Entities.*;
import com.example.demo.Repository.TipoMesaRepository;
import com.example.demo.Repository.ConfiguracionFranjaRepository;
import com.example.demo.Repository.ReservaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del Facade para reservas
 * Contiene la lógica de negocio relacionada con reservas
 */
@Service
public class ReservaFacadeImpl implements ReservaFacade {

    private final ReservaDAO reservaDAO;
    private final MesaDAO mesaDAO;
    private final TipoMesaRepository tipoMesaRepository;
    private final ConfiguracionFranjaRepository configuracionFranjaRepository;
    private final ReservaRepository reservaRepository;
    // Constantes de negocio
    private static final int MAX_PERSONAS_POR_RESERVA = 10;
    private static final int PERSONAS_POR_MESA = 5;
    private static final int MESAS_POR_TIPO = 5;
    private static final String ESTADO_POR_CONFIRMAR = "POR_CONFIRMAR";
    private static final String ESTADO_CANCELADA = "CANCELADA";

    public ReservaFacadeImpl(ReservaDAO reservaDAO, MesaDAO mesaDAO,
            TipoMesaRepository tipoMesaRepository,
            ConfiguracionFranjaRepository configuracionFranjaRepository,
            ReservaRepository reservaRepository) {
        this.reservaDAO = reservaDAO;
        this.mesaDAO = mesaDAO;
        this.tipoMesaRepository = tipoMesaRepository;
        this.configuracionFranjaRepository = configuracionFranjaRepository;
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Reserva crearReserva(ReservaFormDTO reservaFormDTO, Usuario usuario) {
        // Validar datos de la reserva
        validarDatosReserva(reservaFormDTO);

        // Convertir fecha string a LocalDate
        LocalDate fecha = LocalDate.parse(reservaFormDTO.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Verificar que la fecha no sea en el pasado
        if (fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se pueden hacer reservas para fechas pasadas");
        }

        // Verificar disponibilidad
        if (!verificarDisponibilidad(fecha, reservaFormDTO.getIdFranja(),
                reservaFormDTO.getNumeroPersonas(), reservaFormDTO.getIdTipoMesa())) {
            throw new RuntimeException("No hay disponibilidad para la fecha y franja seleccionadas");
        }

        // Verificar que el usuario no tenga otra reserva el mismo día
        List<Reserva> reservasDelDia = reservaDAO.findByUsuarioAndFecha(usuario, fecha);
        if (!reservasDelDia.isEmpty()) {
            throw new RuntimeException("Ya tienes una reserva para esta fecha");
        }
        // Obtener entidades necesarias
        ConfiguracionFranja franja = configuracionFranjaRepository.findById(reservaFormDTO.getIdFranja())
                .orElseThrow(() -> new RuntimeException("Franja horaria no encontrada"));

        // Validar que el tipo de mesa existe
        tipoMesaRepository.findById(reservaFormDTO.getIdTipoMesa())
                .orElseThrow(() -> new RuntimeException("Tipo de mesa no encontrado"));

        // Asignar una mesa disponible del tipo seleccionado
        Mesa mesa = mesaDAO.findFirstAvailableByTipo(reservaFormDTO.getIdTipoMesa())
                .orElseThrow(() -> new RuntimeException("No hay mesas disponibles del tipo seleccionado"));

        // Crear la reserva
        Reserva reserva = new Reserva();
        reserva.setNombreCliente(reservaFormDTO.getNombreCliente());
        reserva.setCorreoCliente(reservaFormDTO.getCorreoCliente());
        reserva.setTelefonoCliente(reservaFormDTO.getTelefonoCliente());
        reserva.setFecha(fecha);
        reserva.setNumeroPersonas(reservaFormDTO.getNumeroPersonas());
        reserva.setEstado(ESTADO_POR_CONFIRMAR);
        reserva.setComentarios(reservaFormDTO.getComentarios());
        reserva.setFranja(franja);
        reserva.setMesa(mesa);
        reserva.setUsuario(usuario);

        return reservaDAO.save(reserva);
    }

    @Override
    public List<ReservaDTO> obtenerReservasFuturasUsuario(Usuario usuario) {
        List<Reserva> reservas = reservaDAO.findReservasFuturasByUsuario(usuario);
        return reservas.stream()
                .map(this::convertirAReservaDTO)
                .toList();
    }

    @Override
    public List<ReservaDTO> obtenerReservasUsuario(Usuario usuario) {
        List<Reserva> reservas = reservaDAO.findByUsuario(usuario);
        return reservas.stream()
                .map(this::convertirAReservaDTO)
                .toList();
    }

    @Override
    public boolean cancelarReserva(Integer idReserva, Usuario usuario) {
        Optional<Reserva> reservaOpt = reservaDAO.findById(idReserva);

        if (reservaOpt.isEmpty()) {
            return false;
        }

        Reserva reserva = reservaOpt.get();

        // Verificar que la reserva pertenece al usuario
        if (!reserva.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
            throw new RuntimeException("No tienes permisos para cancelar esta reserva");
        }

        // Verificar que la reserva no sea del pasado
        if (reserva.getFecha().isBefore(LocalDate.now())) {
            throw new RuntimeException("No se pueden cancelar reservas del pasado");
        }

        // Cambiar estado a cancelada
        reserva.setEstado(ESTADO_CANCELADA);
        reservaDAO.update(reserva);

        return true;
    }

    @Override
    public boolean verificarDisponibilidad(LocalDate fecha, Integer idFranja, Integer numeroPersonas,
            Integer idTipoMesa) {
        // Validar número máximo de personas
        if (numeroPersonas > MAX_PERSONAS_POR_RESERVA) {
            return false;
        }

        // Calcular mesas necesarias
        int mesasNecesarias = calcularMesasNecesarias(numeroPersonas);

        // Obtener mesas ya reservadas para esta fecha, franja y tipo de mesa
        Integer mesasReservadas = reservaRepository.countMesasByFechaAndFranjaAndTipoMesa(fecha, idFranja, idTipoMesa);
        if (mesasReservadas == null) {
            mesasReservadas = 0;
        }

        // Verificar si hay suficientes mesas disponibles
        int mesasDisponibles = MESAS_POR_TIPO - mesasReservadas;

        return mesasDisponibles >= mesasNecesarias;
    }

    @Override
    public int calcularMesasNecesarias(Integer numeroPersonas) {
        // Por cada 5 personas se usa 1 mesa (redondear hacia arriba)
        return (int) Math.ceil((double) numeroPersonas / PERSONAS_POR_MESA);
    }

    @Override
    public List<TipoMesa> obtenerTiposMesa() {
        return tipoMesaRepository.findAll();
    }

    @Override
    public List<ConfiguracionFranja> obtenerFranjasHorarias() {
        return configuracionFranjaRepository.findAll();
    }

    @Override
    public ReservaDTO convertirAReservaDTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setIdReserva(reserva.getIdReserva());
        dto.setNombreCliente(reserva.getNombreCliente());
        dto.setCorreoCliente(reserva.getCorreoCliente());
        dto.setTelefonoCliente(reserva.getTelefonoCliente());
        dto.setFecha(reserva.getFecha());
        dto.setNumeroPersonas(reserva.getNumeroPersonas());
        dto.setEstado(reserva.getEstado());
        dto.setComentarios(reserva.getComentarios());
        dto.setFranjaHoraria(reserva.getFranja().getFranjaHoraria());
        dto.setTipoMesa(reserva.getMesa().getTipoMesa().getNombre());
        dto.setNumeroMesa(reserva.getMesa().getIdMesa());
        return dto;
    }

    @Override
    public void validarDatosReserva(ReservaFormDTO reservaFormDTO) {
        if (reservaFormDTO.getNombreCliente() == null || reservaFormDTO.getNombreCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }

        if (reservaFormDTO.getCorreoCliente() == null || reservaFormDTO.getCorreoCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El correo del cliente es obligatorio");
        }

        if (reservaFormDTO.getFecha() == null || reservaFormDTO.getFecha().trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }

        if (reservaFormDTO.getNumeroPersonas() == null || reservaFormDTO.getNumeroPersonas() <= 0) {
            throw new IllegalArgumentException("El número de personas debe ser mayor a 0");
        }

        if (reservaFormDTO.getNumeroPersonas() > MAX_PERSONAS_POR_RESERVA) {
            throw new IllegalArgumentException(
                    "El número máximo de personas por reserva es " + MAX_PERSONAS_POR_RESERVA);
        }

        if (reservaFormDTO.getIdFranja() == null) {
            throw new IllegalArgumentException("Debe seleccionar una franja horaria");
        }

        if (reservaFormDTO.getIdTipoMesa() == null) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de mesa");
        }

        // Validar formato de correo
        if (!reservaFormDTO.getCorreoCliente().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido");
        }
    }
}
