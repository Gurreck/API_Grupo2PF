package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.Precio;
import org.una.aeropuerto.repositories.IPrecioRepository;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class PrecioServiceImplementation  implements IPrecioService{
    
    @Autowired
    private IPrecioRepository precioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Precio>> findAll() {
        return Optional.ofNullable(precioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Precio> findById(Long id) {
        return precioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Precio>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return precioRepository.findByFechaRegistroBetween(startDate, endDate);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Precio>> findByTipoServicioId(Long tipoServicioId) {
        return Optional.ofNullable(precioRepository.findByTipoServicioId(tipoServicioId));
    }

    @Override
    @Transactional
    public Precio create(Precio vuelo) {
        return precioRepository.save(vuelo);
    }

    @Override
    @Transactional
    public Optional<Precio> update(Precio vuelo, Long id) {
        if (precioRepository.findById(id).isPresent()) {
            return Optional.ofNullable(precioRepository.save(vuelo));
        } else {
            return null;
        }

    }
}
