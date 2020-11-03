package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.repositories.IRolRepository;
import org.una.aeropuerto.utils.MapperUtils;


@Service
public class RolServiceImplementation implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    private Optional<List<RolDTO>> findList(List<Rol> list) {
        if (list != null) {
            List<RolDTO> rolDTO = MapperUtils.DtoListFromEntityList(list, RolDTO.class);
            return Optional.ofNullable(rolDTO);
        } else {
            return null;
        }
    }

    private Optional<List<RolDTO>> findList(Optional<List<Rol>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<RolDTO> oneToDto(Optional<Rol> one) {
        if (one.isPresent()) {
            RolDTO rolDTO = MapperUtils.DtoFromEntity(one.get(), RolDTO.class);
            return Optional.ofNullable(rolDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findAll() {
         return findList(rolRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolDTO> findById(Long id) {
        return oneToDto(rolRepository.findById(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<RolDTO> findByNombreAproximateIgnoreCase(String nombre) {
        return oneToDto(rolRepository.findByNombreContainingIgnoreCase(nombre));
    }
    
     @Override
     @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findByEstado(boolean estado) {
        return  findList(rolRepository.findByEstado(estado));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(rolRepository.findByFechaRegistroBetween(startDate, endDate));
    }   
    
    @Override
    @Transactional(readOnly = true)
    public Long countByEstado(boolean estado) {
        return rolRepository.countByEstado(estado);
    }

    @Override
    @Transactional
    public RolDTO create(RolDTO rolDTO) {
        Rol rol = MapperUtils.EntityFromDto(rolDTO, Rol.class);
        rol = rolRepository.save(rol);
        return MapperUtils.DtoFromEntity(rol, RolDTO.class);
    }

    @Override
    @Transactional
    public Optional<RolDTO> update(RolDTO rolDTO, Long id) {
        if (rolRepository.findById(id).isPresent()) {
            Rol rol = MapperUtils.EntityFromDto(rolDTO, Rol.class);
            rol = rolRepository.save(rol);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(rol, RolDTO.class));
        } else {
            return null;
        }
    }  

    
}
