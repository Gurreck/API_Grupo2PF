package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.AreaTrabajoAvionDTO;
import org.una.aeropuerto.entities.AreaTrabajoAvion;
import org.una.aeropuerto.repositories.IAreaTrabajoAvionRepository;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@Service
public class AreaTrabajoAvionServiceImplementation  implements IAreaTrabajoAvionService {

    @Autowired
    private IAreaTrabajoAvionRepository areaTrabajoAvionRepository;

    private Optional<List<AreaTrabajoAvionDTO>> findList(List<AreaTrabajoAvion> list) {
        if (list != null) {
            List<AreaTrabajoAvionDTO> areaTrabajoAvionDTO = MapperUtils.DtoListFromEntityList(list, AreaTrabajoAvionDTO.class);
            return Optional.ofNullable(areaTrabajoAvionDTO);
        } else {
            return null;
        }
    }

    private Optional<List<AreaTrabajoAvionDTO>> findList(Optional<List<AreaTrabajoAvion>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<AreaTrabajoAvionDTO> oneToDto(Optional<AreaTrabajoAvion> one) {
        if (one.isPresent()) {
            AreaTrabajoAvionDTO areaTrabajoAvionDTO = MapperUtils.DtoFromEntity(one.get(), AreaTrabajoAvionDTO.class);
            return Optional.ofNullable(areaTrabajoAvionDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AreaTrabajoAvionDTO> findById(Long id) {
        return oneToDto(areaTrabajoAvionRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajoAvionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(areaTrabajoAvionRepository.findByFechaRegistroBetween(startDate, endDate));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<AreaTrabajoAvionDTO> findByAvionId(Long id) {
           return oneToDto(areaTrabajoAvionRepository.findByAvionId(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajoAvionDTO>> findByAreaTrabajoId(Long id) {
           return findList(areaTrabajoAvionRepository.findByAreaTrabajoId(id));
    }
    
    
    @Override
    public Optional<List<AreaTrabajoAvionDTO>> findByFechaRegistroAndAerolineaAndZona(Date startDate, Date endDate, long idAerolinea, long idZona) {
        return findList(areaTrabajoAvionRepository.findByFechaRegistroAndAerolineaAndZona(startDate, endDate, idAerolinea, idZona));
    }

    @Override
    @Transactional
    public AreaTrabajoAvionDTO create(AreaTrabajoAvionDTO areaTrabajoAvionDTO) {
        AreaTrabajoAvion areaTrabajoAvion = MapperUtils.EntityFromDto(areaTrabajoAvionDTO, AreaTrabajoAvion.class);
        areaTrabajoAvion = areaTrabajoAvionRepository.save(areaTrabajoAvion);
        return MapperUtils.DtoFromEntity(areaTrabajoAvion, AreaTrabajoAvionDTO.class);
    }


    @Override
    @Transactional
    public Optional<AreaTrabajoAvionDTO> update(AreaTrabajoAvionDTO areaTrabajoAvionDTO, Long id) {
        if (areaTrabajoAvionRepository.findById(id).isPresent()) {
            AreaTrabajoAvion areaTrabajoAvion = MapperUtils.EntityFromDto(areaTrabajoAvionDTO, AreaTrabajoAvion.class);
            areaTrabajoAvion.setId(id);
            areaTrabajoAvion = areaTrabajoAvionRepository.save(areaTrabajoAvion);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(areaTrabajoAvion, AreaTrabajoAvionDTO.class));
        } else {
            return null;
        }
    }
}