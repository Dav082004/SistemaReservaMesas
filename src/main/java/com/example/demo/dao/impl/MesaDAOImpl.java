package com.example.demo.dao.impl;

import com.example.demo.dao.MesaDAO;
import com.example.demo.Entities.Mesa;
import com.example.demo.Entities.TipoMesa;
import com.example.demo.Repository.MesaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del DAO para Mesa usando Spring Data JPA
 */
@Repository
public class MesaDAOImpl implements MesaDAO {

    private final MesaRepository mesaRepository;

    public MesaDAOImpl(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Override
    public Mesa save(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    @Override
    public Optional<Mesa> findById(Integer id) {
        return mesaRepository.findById(id);
    }

    @Override
    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    @Override
    public List<Mesa> findByTipoMesa(TipoMesa tipoMesa) {
        return mesaRepository.findByTipoMesa(tipoMesa);
    }

    @Override
    public List<Mesa> findMesasDisponiblesByTipo(Integer idTipoMesa) {
        return mesaRepository.findByTipoMesaId(idTipoMesa);
    }

    @Override
    public Long countByTipoMesa(TipoMesa tipoMesa) {
        return mesaRepository.countByTipoMesa(tipoMesa);
    }

    @Override
    public Optional<Mesa> findFirstAvailableByTipo(Integer idTipoMesa) {
        List<Mesa> mesas = mesaRepository.findMesasByTipoMesaOrderById(idTipoMesa);
        return mesas.isEmpty() ? Optional.empty() : Optional.of(mesas.get(0));
    }

    @Override
    public Mesa update(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    @Override
    public void deleteById(Integer id) {
        mesaRepository.deleteById(id);
    }
}
