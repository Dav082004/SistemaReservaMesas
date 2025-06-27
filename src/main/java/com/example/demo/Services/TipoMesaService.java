package com.example.demo.Services;

import com.example.demo.Entities.TipoMesa;
import com.example.demo.Repository.TipoMesaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoMesaService {
    private final TipoMesaRepository tipoMesaRepository;
    @Autowired
    public TipoMesaService(TipoMesaRepository tipoMesaRepository) { this.tipoMesaRepository = tipoMesaRepository; }
    public List<TipoMesa> obtenerTodosLosTipos() { return tipoMesaRepository.findAll(); }
}