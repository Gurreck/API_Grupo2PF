package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.TipoServicio;
import org.una.aeropuerto.repositories.ITipoServicioRepository;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class TipoServicioServiceImplementation implements ITipoServicioService{

     @Autowired
    private ITipoServicioRepository tipoServicioRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoServicio>> findAll() {
        return Optional.ofNullable(tipoServicioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoServicio> findById(Long id) {
        return tipoServicioRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoServicio>> findByNombre(String nombre) {
        return Optional.ofNullable(tipoServicioRepository.findByNombre(nombre));
    }
    
    @Override
    @Transactional
    public TipoServicio create(TipoServicio tipoServicio) {
        return tipoServicioRepository.save(tipoServicio);
    }

    @Override
    @Transactional
    public Optional<TipoServicio> update(TipoServicio tipoServicio, Long id) {
        if (tipoServicioRepository.findById(id).isPresent()) {
            return Optional.ofNullable(tipoServicioRepository.save(tipoServicio));
        } else {
            return null;
        }

    }
}
