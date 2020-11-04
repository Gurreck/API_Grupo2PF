package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.ParametroSistemaDTO;
import org.una.aeropuerto.entities.ParametroSistema;
import org.una.aeropuerto.repositories.IParametroSistemaRepository;
import org.una.aeropuerto.utils.MapperUtils;


@Service
public class ParametroSistemaServiceImplementation implements IParametroSistemaService {

    @Autowired
    private IParametroSistemaRepository parametroSistemaRepository;

    private Optional<List<ParametroSistemaDTO>> findList(List<ParametroSistema> list) {
        if (list != null) {
            List<ParametroSistemaDTO> parametroSistemaDTO = MapperUtils.DtoListFromEntityList(list, ParametroSistemaDTO.class);
            return Optional.ofNullable(parametroSistemaDTO);
        } else {
            return null;
        }
    }

    private Optional<List<ParametroSistemaDTO>> findList(Optional<List<ParametroSistema>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<ParametroSistemaDTO> oneToDto(Optional<ParametroSistema> one) {
        if (one.isPresent()) {
            ParametroSistemaDTO parametroSistemaDTO = MapperUtils.DtoFromEntity(one.get(), ParametroSistemaDTO.class);
            return Optional.ofNullable(parametroSistemaDTO);
        } else {
            return null;
        }
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroSistemaDTO>> findAll() {
        return findList(parametroSistemaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametroSistemaDTO> findById(Long id) {
        return oneToDto(parametroSistemaRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroSistemaDTO>> findByEstado(boolean estado) {
        return findList(parametroSistemaRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroSistemaDTO>> findByNombreAproximateIgnoreCase(String nombre) {
        return findList(parametroSistemaRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroSistemaDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
       return findList(parametroSistemaRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional
    public ParametroSistemaDTO create(ParametroSistemaDTO parametroSistemaDTO) {
        ParametroSistema parametroSistema = MapperUtils.EntityFromDto(parametroSistemaDTO, ParametroSistema.class);
        parametroSistema = parametroSistemaRepository.save(parametroSistema);
        return MapperUtils.DtoFromEntity(parametroSistema, ParametroSistemaDTO.class);
    }

    @Override
    @Transactional
    public Optional<ParametroSistemaDTO> update(ParametroSistemaDTO parametroSistemaDTO, Long id) {
        if (parametroSistemaRepository.findById(id).isPresent()) {
            ParametroSistema parametroSistema = MapperUtils.EntityFromDto(parametroSistemaDTO, ParametroSistema.class);
            parametroSistema.setId(id);
            parametroSistema = parametroSistemaRepository.save(parametroSistema);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(parametroSistema, ParametroSistemaDTO.class));
        } else {
            return null;
        }
    }

}
