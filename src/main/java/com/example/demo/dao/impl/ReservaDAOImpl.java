package com.example.demo.dao.impl;

import com.example.demo.dao.ReservaDAO;
import com.example.demo.Entities.Reserva;
import com.example.demo.Entities.Usuario;
import com.example.demo.Repository.ReservaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del DAO para Reserva usando Spring Data JPA
 */
@Repository
public class ReservaDAOImpl implements ReservaDAO {

    private final ReservaRepository reservaRepository;

    public ReservaDAOImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<Reserva> findById(Integer id) {
        return reservaRepository.findById(id);
    }

    @Override
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> findByUsuario(Usuario usuario) {
        return reservaRepository.findByUsuario(usuario);
    }

    @Override
    public List<Reserva> findByFecha(LocalDate fecha) {
        return reservaRepository.findByFecha(fecha);
    }

    @Override
    public List<Reserva> findByEstado(String estado) {
        return reservaRepository.findByEstado(estado);
    }

    @Override
    public List<Reserva> findByUsuarioAndFecha(Usuario usuario, LocalDate fecha) {
        return reservaRepository.findByUsuarioAndFecha(usuario, fecha);
    }

    @Override
    public List<Reserva> findByFranjaAndFecha(Integer idFranja, LocalDate fecha) {
        // Implementamos usando la consulta personalizada
        return reservaRepository.findAll().stream()
                .filter(r -> r.getFranja().getIdFranja().equals(idFranja) && r.getFecha().equals(fecha))
                .toList();
    }

    @Override
    public List<Reserva> findReservasFuturasByUsuario(Usuario usuario) {
        return reservaRepository.findReservasFuturasByUsuario(usuario, LocalDate.now());
    }

    @Override
    public Long countByFranjaAndFecha(Integer idFranja, LocalDate fecha) {
        return reservaRepository.countByFranjaAndFecha(idFranja, fecha);
    }

    @Override
    public Reserva update(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public void deleteById(Integer id) {
        reservaRepository.deleteById(id);
    }
}
