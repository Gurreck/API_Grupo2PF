package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.Avion;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.repositories.IAvionRepository;
import org.una.aeropuerto.repositories.IUsuarioRepository;


@Service
public class AvionServiceImplementation implements IAvionService {

    @Autowired
    private IAvionRepository avionRepository;
    
    @Override
    public Optional<List<Avion>> findAll() {
        return Optional.ofNullable(avionRepository.findAll());
    }

    @Override
    public Optional<Avion> findById(Long id) {
        return avionRepository.findById(id);
    }

    @Override
    public Optional<List<Avion>> findByMatricula(String matricula) {
        return Optional.ofNullable(avionRepository.findByMatricula(matricula));
    }

    @Override
    public Optional<List<Avion>> findByTipoAvion(String tipoAvion) {
        return Optional.ofNullable(avionRepository.findByTipoAvion(tipoAvion));
    }

    @Override
    public Optional<List<Avion>> findByFechaRegistro(Date fechaRegistro) {
        return Optional.ofNullable(avionRepository.findByFechaRegistro(fechaRegistro));
    }

    @Override
    public Optional<List<Avion>> findByEstado(boolean estado) {
        return Optional.ofNullable(avionRepository.findByEstado(estado));
    }

    @Override
    public Avion create(Avion avion) {
         return avionRepository.save(avion);
    }

    @Override
    public Optional<Avion> update(Avion avion, Long id) {
        if (avionRepository.findById(id).isPresent()) {
            return Optional.ofNullable(avionRepository.save(avion));
        } else {
            return null;
        }
    }

}
