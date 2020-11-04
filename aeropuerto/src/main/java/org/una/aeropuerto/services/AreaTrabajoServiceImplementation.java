package org.una.aeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.AreaTrabajoDTO;
import org.una.aeropuerto.entities.AreaTrabajo;
import org.una.aeropuerto.repositories.IAreaTrabajoRepository;
import org.una.aeropuerto.utils.MapperUtils;

import java.util.List;
import java.util.Optional;


@Service
public class AreaTrabajoServiceImplementation implements IAreaTrabajoService {

    @Autowired
    private IAreaTrabajoRepository areaTrabajoRepository;

    private Optional<List<AreaTrabajoDTO>> findList(List<AreaTrabajo> list) {
        if (list != null) {
            List<AreaTrabajoDTO> AreaTrabajosDTO = MapperUtils.DtoListFromEntityList(list, AreaTrabajoDTO.class);
            return Optional.ofNullable(AreaTrabajosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<AreaTrabajoDTO>> findList(Optional<List<AreaTrabajo>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<AreaTrabajoDTO> oneToDto(Optional<AreaTrabajo> one) {
        if (one.isPresent()) {
            AreaTrabajoDTO AreaTrabajoDTO = MapperUtils.DtoFromEntity(one.get(), AreaTrabajoDTO.class);
            return Optional.ofNullable(AreaTrabajoDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajoDTO>> findAll() {
        return findList(areaTrabajoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AreaTrabajoDTO> findById(Long id) {
        return oneToDto(areaTrabajoRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajoDTO>> findByNombreAreaAproximateIgnoreCase(String area) {
        return findList(areaTrabajoRepository.findByNombreAreaContainingIgnoreCase(area));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajoDTO>> findByNombreResponsableAproximateIgnoreCase(String nombreCompleto) {
        return findList(areaTrabajoRepository.findByNombreResponsableContainingIgnoreCase(nombreCompleto));
    }

    @Override
    @Transactional
    public AreaTrabajoDTO create(AreaTrabajoDTO areaTrabajoDTO) {
        AreaTrabajo areaTrabajo = MapperUtils.EntityFromDto(areaTrabajoDTO, AreaTrabajo.class);
        areaTrabajo = areaTrabajoRepository.save(areaTrabajo);
        return MapperUtils.DtoFromEntity(areaTrabajo, AreaTrabajoDTO.class);
    }

    @Override
    @Transactional
    public Optional<AreaTrabajoDTO> update(AreaTrabajoDTO areaTrabajoDTO, Long id) {
        if (areaTrabajoRepository.findById(id).isPresent()) {
            AreaTrabajo areaTrabajo = MapperUtils.EntityFromDto(areaTrabajoDTO, AreaTrabajo.class);
            areaTrabajo.setId(id);
            areaTrabajo = areaTrabajoRepository.save(areaTrabajo);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(areaTrabajo, AreaTrabajoDTO.class));
        } else {
            return null;
        }
    }

}
