package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.ServicioDTO;
import org.una.aeropuerto.entities.Servicio;
import org.una.aeropuerto.repositories.IServicioRepository;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class ServicioServiceImplementation implements IServicioService {

    @Autowired
    private IServicioRepository servicioRepository;

    private Optional<List<ServicioDTO>> findList(List<Servicio> list) {
        if (list != null) {
            List<ServicioDTO> serviciosDTO = MapperUtils.DtoListFromEntityList(list, ServicioDTO.class);
            return Optional.ofNullable(serviciosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<ServicioDTO>> findList(Optional<List<Servicio>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<ServicioDTO> oneToDto(Optional<Servicio> one) {
        if (one.isPresent()) {
            ServicioDTO ServicioDTO = MapperUtils.DtoFromEntity(one.get(), ServicioDTO.class);
            return Optional.ofNullable(ServicioDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicioDTO> findById(Long id) {
        return oneToDto(servicioRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioDTO>> findByEstado(boolean estado) {
        return findList(servicioRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioDTO>> findByEstadoCobro(boolean estadoCobro) {
        return findList(servicioRepository.findByEstado(estadoCobro));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(servicioRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioDTO>> findByAvionId(Long id) {
        return findList(servicioRepository.findByAvionId(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioDTO>> findByTipoServicioId(Long id) {
        return findList(servicioRepository.findByTipoServicioId(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServicioDTO>> findByTipoServicioIdAndAvionId(Long tipoServicio, Long avion) {
        return findList(servicioRepository.findByTipoServicioIdAndAvionId(tipoServicio, avion));
    }

    @Override
    @Transactional
    public ServicioDTO create(ServicioDTO servicioDTO) {

        Servicio servicio = MapperUtils.EntityFromDto(servicioDTO, Servicio.class);
        servicio = servicioRepository.save(servicio);
        return MapperUtils.DtoFromEntity(servicio, ServicioDTO.class);

    }

    @Override
    @Transactional
    public Optional<ServicioDTO> update(ServicioDTO servicioDTO, Long id) {
        if (servicioRepository.findById(id).isPresent()) {
            Servicio servicio = MapperUtils.EntityFromDto(servicioDTO, Servicio.class);
            servicio = servicioRepository.save(servicio);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(servicio, ServicioDTO.class));
        } else {
            return null;
        }
    }
    
}
