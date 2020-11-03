package org.una.aeropuerto.services;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.HorarioDTO;
import org.una.aeropuerto.entities.Horario;
import org.una.aeropuerto.repositories.IHorarioRepository;
import org.una.aeropuerto.utils.MapperUtils;


@Service
public class HorarioServiceImplementation implements IHorarioService {

    @Autowired
    private IHorarioRepository horarioRepository;
    private Optional<List<HorarioDTO>> findList(List<Horario> list) {
        if (list != null) {
            List<HorarioDTO> horariosDTO = MapperUtils.DtoListFromEntityList(list, HorarioDTO.class);
            return Optional.ofNullable(horariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<HorarioDTO>> findList(Optional<List<Horario>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<HorarioDTO> oneToDto(Optional<Horario> one) {
        if (one.isPresent()) {
            HorarioDTO horarioDTO = MapperUtils.DtoFromEntity(one.get(), HorarioDTO.class);
            return Optional.ofNullable(horarioDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HorarioDTO> findById(Long id) {
        return oneToDto(horarioRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HorarioDTO>> findByEstado(boolean estado) {
        return findList(horarioRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HorarioDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(horarioRepository.findByFechaRegistroBetween(startDate, endDate));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<HorarioDTO>> findByUsuarioId(Long id) {
        return findList(horarioRepository.findByUsuarioId(id));
    }
    
    @Override
    @Transactional
    public HorarioDTO create(HorarioDTO horarioDTO) {
        Horario horario = MapperUtils.EntityFromDto(horarioDTO, Horario.class);
        horario = horarioRepository.save(horario);
        return MapperUtils.DtoFromEntity(horario, HorarioDTO.class);
    }

    @Override
    @Transactional
    public Optional<HorarioDTO> update(HorarioDTO horarioDTO, Long id) {
        if (horarioRepository.findById(id).isPresent()) {
            Horario horario = MapperUtils.EntityFromDto(horarioDTO, Horario.class);
            horario = horarioRepository.save(horario);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(horario, HorarioDTO.class));
        } else {
            return null;
        }
    }

    
}
