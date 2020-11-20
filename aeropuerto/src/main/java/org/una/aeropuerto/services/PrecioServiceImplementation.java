package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.PrecioDTO;
import org.una.aeropuerto.entities.Precio;
import org.una.aeropuerto.repositories.IPrecioRepository;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class PrecioServiceImplementation  implements IPrecioService{
    
    @Autowired
    private IPrecioRepository precioRepository;

    private Optional<List<PrecioDTO>> findList(List<Precio> list) {
        if (list != null) {
            List<PrecioDTO> precioDTO = MapperUtils.DtoListFromEntityList(list, PrecioDTO.class);
            return Optional.ofNullable(precioDTO);
        } else {
            return null;
        }
    }

    private Optional<List<PrecioDTO>> findList(Optional<List<Precio>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<PrecioDTO> oneToDto(Optional<Precio> one) {
        if (one.isPresent()) {
            PrecioDTO precioDTO = MapperUtils.DtoFromEntity(one.get(), PrecioDTO.class);
            return Optional.ofNullable(precioDTO);
        } else {
            return null;
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PrecioDTO> findById(Long id) {
        return oneToDto(precioRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PrecioDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(precioRepository.findByFechaRegistroBetween(startDate, endDate));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<PrecioDTO>> findByTipoServicioId(Long tipoServicioId) {
        return findList(precioRepository.findByTipoServicioId(tipoServicioId));
    }
    
    @Override
    public Optional<List<PrecioDTO>> findByTipoServicioIdAndFechaRegistroBetween(Date fechInicio, Date fechaFinal, Long tipoServicio) {
        return findList(precioRepository.findByTipoServicioIdAndFechaRegistroBetween(fechInicio, fechaFinal, tipoServicio));
    }
    
    @Override
    @Transactional
    public PrecioDTO create(PrecioDTO precioDTO) {
        Precio precio = MapperUtils.EntityFromDto(precioDTO,Precio.class);
        precio = precioRepository.save(precio);
        return MapperUtils.DtoFromEntity(precio, PrecioDTO.class);
    }


    @Override
    @Transactional
    public Optional<PrecioDTO> update(PrecioDTO precioDTO, Long id) {
        if (precioRepository.findById(id).isPresent()) {
            Precio precio = MapperUtils.EntityFromDto(precioDTO, Precio.class);
            precio.setId(id);
            precio = precioRepository.save(precio);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(precio, PrecioDTO.class));
        } else {
            return null;
        }
    }

}
