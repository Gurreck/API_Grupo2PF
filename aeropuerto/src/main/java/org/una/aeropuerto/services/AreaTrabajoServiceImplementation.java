package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.AreaTrabajo;
import org.una.aeropuerto.repositories.IAreaTrabajoRepository;


@Service
public class AreaTrabajoServiceImplementation implements IAreaTrabajoService {

    @Autowired
    private IAreaTrabajoRepository areaTrabajoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajo>> findAll() {
        return Optional.ofNullable(areaTrabajoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AreaTrabajo> findById(Long id) {
        return areaTrabajoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional findByNombreArea(String area) {
        return Optional.ofNullable(areaTrabajoRepository.findByNombreArea(area));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajo>> findByNombreResponsable(String nombreCompleto) {
        return Optional.ofNullable(areaTrabajoRepository.findByNombreResponsable(nombreCompleto));
    }

    @Override
    @Transactional
    public AreaTrabajo create(AreaTrabajo AreaTrabajo) {
        return areaTrabajoRepository.save(AreaTrabajo);
    }

    @Override
    @Transactional
    public Optional<AreaTrabajo> update(AreaTrabajo AreaTrabajo, Long id) {
        if (areaTrabajoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(areaTrabajoRepository.save(AreaTrabajo));
        } else {
            return null;
        }

    }
   
 
}
