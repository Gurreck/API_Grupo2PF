package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.TipoServicioDTO;
import org.una.aeropuerto.entities.TipoServicio;
import org.una.aeropuerto.repositories.ITipoServicioRepository;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class TipoServicioServiceImplementation implements ITipoServicioService{

     @Autowired
    private ITipoServicioRepository tipoServicioRepository;

    private Optional<List<TipoServicioDTO>> findList(List<TipoServicio> list) {
        if (list != null) {
            List<TipoServicioDTO> tipoServicioDTO = MapperUtils.DtoListFromEntityList(list, TipoServicioDTO.class);
            return Optional.ofNullable(tipoServicioDTO);
        } else {
            return null;
        }
    }

    private Optional<List<TipoServicioDTO>> findList(Optional<List<TipoServicio>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<TipoServicioDTO> oneToDto(Optional<TipoServicio> one) {
        if (one.isPresent()) {
            TipoServicioDTO tipoServicioDTO = MapperUtils.DtoFromEntity(one.get(), TipoServicioDTO.class);
            return Optional.ofNullable(tipoServicioDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoServicioDTO>> findAll() {
        return findList(tipoServicioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoServicioDTO> findById(Long id) {
        return oneToDto(tipoServicioRepository.findById(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoServicioDTO>> findByNombreAproximateIgnoreCase(String nombre) {
        return findList(tipoServicioRepository.findByNombreContainingIgnoreCase(nombre));
    }
    
    @Override
    @Transactional
    public TipoServicioDTO create(TipoServicioDTO tipoServicioDTO) {
        TipoServicio tipoServicio = MapperUtils.EntityFromDto(tipoServicioDTO, TipoServicio.class);
        tipoServicio = tipoServicioRepository.save(tipoServicio);
        return MapperUtils.DtoFromEntity(tipoServicio, TipoServicioDTO.class);
    }

    @Override
    @Transactional
    public Optional<TipoServicioDTO> update(TipoServicioDTO tipoServicioDTO, Long id) {
        if (tipoServicioRepository.findById(id).isPresent()) {
            TipoServicio tipoServicio = MapperUtils.EntityFromDto(tipoServicioDTO, TipoServicio.class);
            tipoServicio = tipoServicioRepository.save(tipoServicio);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(tipoServicio, TipoServicioDTO.class));
        } else {
            return null;
        }
    }
}
