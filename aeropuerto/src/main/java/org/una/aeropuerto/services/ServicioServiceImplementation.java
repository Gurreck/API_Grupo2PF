package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.Servicio;
import org.una.aeropuerto.repositories.IServicioRepository;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class ServicioServiceImplementation implements IServicioService {

    @Autowired
    private IServicioRepository servicioRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Servicio>> findAll() {
        return Optional.ofNullable(servicioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> findById(Long id) {
        return servicioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Servicio>> findByEstado(boolean estado) {
        return  Optional.ofNullable(servicioRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Servicio>> findByEstadoCobro(boolean estadoCobro) {
        return  Optional.ofNullable(servicioRepository.findByEstado(estadoCobro));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Servicio>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return servicioRepository.findByFechaRegistroBetween(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Servicio>> findByAvionId(Long id) {
        return Optional.ofNullable(servicioRepository.findByAvionId(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Servicio>> findByTipoServicioId(Long id) {
        return Optional.ofNullable(servicioRepository.findByTipoServicioId(id));
    }
        
    @Override
    @Transactional
    public Servicio create(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    @Transactional
    public Optional<Servicio> update(Servicio servicio, Long id) {
        if (servicioRepository.findById(id).isPresent()) {
            return Optional.ofNullable(servicioRepository.save(servicio));
        } else {
            return null;
        }

    }
    
}
