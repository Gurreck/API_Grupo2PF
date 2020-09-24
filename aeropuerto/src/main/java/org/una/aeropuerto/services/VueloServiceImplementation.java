package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.Vuelo;
import org.una.aeropuerto.repositories.IVueloRepository;


@Service
public class VueloServiceImplementation implements IVueloService {

    @Autowired
    private IVueloRepository vueloRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Vuelo>> findAll() {
        return Optional.ofNullable(vueloRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vuelo> findById(Long id) {
        return vueloRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Vuelo>> findByAeropuerto(String cedula) {
        return Optional.ofNullable(vueloRepository.findByAeropuerto(cedula));
    }
    
    @Override
    public Optional<List<Vuelo>> findByEstado(boolean estado) {
        return  Optional.ofNullable(vueloRepository.findByEstado(estado));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Vuelo>> findByFechaLlegada(Date startDate) {
       return Optional.ofNullable(vueloRepository.findByFechaLlegada(startDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Vuelo>> findByFechaSalida(Date startDate) {
       return Optional.ofNullable(vueloRepository.findByFechaSalida(startDate));
    }

    @Override
    @Transactional
    public Vuelo create(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    @Transactional
    public Optional<Vuelo> update(Vuelo vuelo, Long id) {
        if (vueloRepository.findById(id).isPresent()) {
            return Optional.ofNullable(vueloRepository.save(vuelo));
        } else {
            return null;
        }

    }
 
}
