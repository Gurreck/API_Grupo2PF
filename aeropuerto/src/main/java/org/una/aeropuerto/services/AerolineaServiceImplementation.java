package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.aeropuerto.entities.Aerolinea;
import org.una.aeropuerto.repositories.IAerolineaRepository;


@Service
public class AerolineaServiceImplementation implements IAerolineaService {

    @Autowired
    private IAerolineaRepository AerolineaRepository;

    @Override
    public Optional<List<Aerolinea>> findAll() {
         return Optional.ofNullable(AerolineaRepository.findAll());
    }

    @Override
    public Optional<Aerolinea> findById(Long id) {
        return AerolineaRepository.findById(id);
    }
    
    @Override
    public Optional<List<Aerolinea>> findByNombreResponsable(String nombreResponsable) {
        return Optional.ofNullable(AerolineaRepository.findByNombreResponsable(nombreResponsable));
    }
    
    @Override
    public Optional<Aerolinea>findByNombreAerolinea(String nombreAerolinea) {
        return Optional.ofNullable(AerolineaRepository.findByNombreAerolinea(nombreAerolinea));
    }
    
    @Override
    public Optional<List<Aerolinea>> findByEstado(boolean estado) {
        return  Optional.ofNullable(AerolineaRepository.findByEstado(estado));
    }
    
    @Override
    public Aerolinea create(Aerolinea Aerolinea) {
       return AerolineaRepository.save(Aerolinea);
    }

    @Override
    public Optional<Aerolinea> update(Aerolinea Aerolinea, Long id) {
        
        if (AerolineaRepository.findById(id).isPresent()) {
            return Optional.ofNullable(AerolineaRepository.save(Aerolinea));
        } else {
            return null;
        }
    }
}

