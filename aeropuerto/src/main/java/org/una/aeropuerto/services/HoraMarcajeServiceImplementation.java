package org.una.aeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.aeropuerto.dto.AerolineaDTO;
import org.una.aeropuerto.dto.HoraMarcajeDTO;
import org.una.aeropuerto.entities.Aerolinea;
import org.una.aeropuerto.entities.HoraMarcaje;
import org.una.aeropuerto.repositories.IHoraMarcajeRepository;
import org.una.aeropuerto.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class HoraMarcajeServiceImplementation implements IHoraMarcajeService {

    @Autowired
    private IHoraMarcajeRepository horaMarcajeRepository;

    private Optional<List<HoraMarcajeDTO>> findList(List<HoraMarcaje> list) {
        if (list != null) {
            List<HoraMarcajeDTO> HoraMarcajesDTO = MapperUtils.DtoListFromEntityList(list, HoraMarcajeDTO.class);
            return Optional.ofNullable(HoraMarcajesDTO);
        } else {
            return null;
        }
    }

    private Optional<List<HoraMarcajeDTO>> findList(Optional<List<HoraMarcaje>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<HoraMarcajeDTO> oneToDto(Optional<HoraMarcaje> one) {
        if (one.isPresent()) {
            HoraMarcajeDTO HoraMarcajeDTO = MapperUtils.DtoFromEntity(one.get(), HoraMarcajeDTO.class);
            return Optional.ofNullable(HoraMarcajeDTO);
        } else {
            return null;
        }
    }

    @Override
    public Optional<HoraMarcajeDTO> findById(Long id) {
        return oneToDto(horaMarcajeRepository.findById(id));
    }
    
    @Override
    public Optional<List<HoraMarcajeDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return  findList(horaMarcajeRepository.findByFechaRegistroBetween(startDate, endDate));
    }
    
    @Override
    public Optional<List<HoraMarcajeDTO>> findByUsuarioId(Long id) {
        return  findList(horaMarcajeRepository.findByUsuarioId(id));
    }
    
    @Override
    public HoraMarcajeDTO create(HoraMarcajeDTO horaMarcajeDTO) {
        HoraMarcaje horaMarcaje = MapperUtils.EntityFromDto(horaMarcajeDTO, HoraMarcaje.class);
        horaMarcaje = horaMarcajeRepository.save(horaMarcaje);
        return MapperUtils.DtoFromEntity(horaMarcaje, HoraMarcajeDTO.class);
    }

    @Override
    public Optional<HoraMarcajeDTO> update(HoraMarcajeDTO horaMarcajeDTO, Long id) {
        if (horaMarcajeRepository.findById(id).isPresent()) {
            HoraMarcaje horaMarcaje = MapperUtils.EntityFromDto(horaMarcajeDTO, HoraMarcaje.class);
            horaMarcaje = horaMarcajeRepository.save(horaMarcaje);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(horaMarcaje, HoraMarcajeDTO.class));
        } else {
            return null;
        }
    }     

    
}
