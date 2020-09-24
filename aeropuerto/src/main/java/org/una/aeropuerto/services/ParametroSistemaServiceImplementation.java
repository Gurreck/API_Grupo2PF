package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.aeropuerto.entities.ParametroSistema;
import org.una.aeropuerto.repositories.IParametroSistemaRepository;


@Service
public class ParametroSistemaServiceImplementation implements IParametroSistemaService {

    @Autowired
    private IParametroSistemaRepository parametroSistemaRepository;
    
    @Override
    public Optional<List<ParametroSistema>> findAll() {
        return Optional.ofNullable(parametroSistemaRepository.findAll());
    }

    @Override
    public Optional<ParametroSistema> findById(Long id) {
        return parametroSistemaRepository.findById(id);
    }

    @Override
    public Optional<List<ParametroSistema>> findByEstado(boolean estado) {
        return Optional.ofNullable(parametroSistemaRepository.findByEstado(estado));
    }

    @Override
    public Optional<List<ParametroSistema>> findByNombre(String nombre) {
        return Optional.ofNullable(parametroSistemaRepository.findByNombre(nombre));
    }

    @Override
    public Optional<List<ParametroSistema>> findByFechaRegistro(Date fechaRegistro) {
        return Optional.ofNullable(parametroSistemaRepository.findByFechaRegistro(fechaRegistro));
    }

    @Override
    public ParametroSistema create(ParametroSistema parametroSistema) {
         return parametroSistemaRepository.save(parametroSistema);
    }

    @Override
    public Optional<ParametroSistema> update(ParametroSistema parametroSistema, Long id) {
          if (parametroSistemaRepository.findById(id).isPresent()) {
            return Optional.ofNullable(parametroSistemaRepository.save(parametroSistema));
        } else {
            return null;
        }
    }
}
