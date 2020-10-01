package org.una.aeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.aeropuerto.dto.AvionDTO;
import org.una.aeropuerto.entities.Avion;
import org.una.aeropuerto.repositories.IAvionRepository;
import org.una.aeropuerto.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AvionServiceImplementation implements IAvionService {

    @Autowired
    private IAvionRepository avionRepository;

    private Optional<List<AvionDTO>> findList(List<Avion> list) {
        if (list != null) {
            List<AvionDTO> AvionsDTO = MapperUtils.DtoListFromEntityList(list, AvionDTO.class);
            return Optional.ofNullable(AvionsDTO);
        } else {
            return null;
        }
    }

    private Optional<List<AvionDTO>> findList(Optional<List<Avion>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<AvionDTO> oneToDto(Optional<Avion> one) {
        if (one.isPresent()) {
            AvionDTO AvionDTO = MapperUtils.DtoFromEntity(one.get(), AvionDTO.class);
            return Optional.ofNullable(AvionDTO);
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<AvionDTO>> findAll() {
        return findList(avionRepository.findAll());
    }

    @Override
    public Optional<AvionDTO> findById(Long id) {
        return oneToDto(avionRepository.findById(id));
    }

    @Override
    public Optional<List<AvionDTO>> findByMatricula(String matricula) {
        return findList(avionRepository.findByMatricula(matricula));
    }

    @Override
    public Optional<List<AvionDTO>> findByTipoAvion(String tipoAvion) {
        return findList(avionRepository.findByTipoAvion(tipoAvion));
    }

    @Override
    public Optional<List<AvionDTO>> findByFechaRegistro(Date fechaRegistro) {
        return findList(avionRepository.findByFechaRegistro(fechaRegistro));
    }

    @Override
    public Optional<List<AvionDTO>> findByEstado(boolean estado) {
        return findList(avionRepository.findByEstado(estado));
    }

    @Override
    public AvionDTO create(AvionDTO avionDTO) {
        Avion avion = MapperUtils.EntityFromDto(avionDTO, Avion.class);
        avion = avionRepository.save(avion);
        return MapperUtils.DtoFromEntity(avion, AvionDTO.class);
    }

    @Override
    public Optional<AvionDTO> update(AvionDTO avionDTO, Long id) {
        if (avionRepository.findById(id).isPresent()) {
            Avion avion = MapperUtils.EntityFromDto(avionDTO, Avion.class);
            avion = avionRepository.save(avion);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(avion, AvionDTO.class));
        } else {
            return null;
        }
    }

}
