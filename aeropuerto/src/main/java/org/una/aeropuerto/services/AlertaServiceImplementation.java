package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.repositories.IAlertaRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.entities.Alerta;
import org.una.aeropuerto.dto.AlertaDTO;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class AlertaServiceImplementation implements IAlertaService{
    
    @Autowired
    private IAlertaRepository alertaRepository;

    private Optional<List<AlertaDTO>> findList(List<Alerta> list) {
        if (list != null) {
            List<AlertaDTO> AlertasDTO = MapperUtils.DtoListFromEntityList(list, AlertaDTO.class);
            return Optional.ofNullable(AlertasDTO);
        } else {
            return null;
        }
    }

    private Optional<List<AlertaDTO>> findList(Optional<List<Alerta>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<AlertaDTO> oneToDto(Optional<Alerta> one) {
        if (one.isPresent()) {
            AlertaDTO AlertaDTO = MapperUtils.DtoFromEntity(one.get(), AlertaDTO.class);
            return Optional.ofNullable(AlertaDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlertaDTO> findById(Long id) {
        return oneToDto(alertaRepository.findById(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaDTO>> findByVueloId(Long vuelo) {
        return findList(alertaRepository.findByVueloId(vuelo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(alertaRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaDTO>> findByEstado(boolean estado) {
        return findList(alertaRepository.findByEstado(estado));
    }

    @Override
    @Transactional
    public AlertaDTO create(AlertaDTO alertaDTO) {
        Alerta alerta = MapperUtils.EntityFromDto(alertaDTO, Alerta.class);
        alerta = alertaRepository.save(alerta);
        return MapperUtils.DtoFromEntity(alerta, AlertaDTO.class);
    }

    @Override
    @Transactional
    public Optional<AlertaDTO> update(AlertaDTO alertaDTO, Long id) {
        if (alertaRepository.findById(id).isPresent()) {
            Alerta alerta = MapperUtils.EntityFromDto(alertaDTO, Alerta.class);
            alerta = alertaRepository.save(alerta);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(alerta, AlertaDTO.class));
        } else {
            return null;
        }
    }
}
