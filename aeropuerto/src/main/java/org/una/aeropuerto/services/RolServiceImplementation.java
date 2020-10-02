package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.repositories.IRolRepository;
import org.una.aeropuerto.repositories.IUsuarioRepository;
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
    public Optional<List<RolDTO>> findAll() {
         return findList(rolRepository.findAll());
    }

    @Override
    public Optional<RolDTO> findById(Long id) {
        return oneToDto(rolRepository.findById(id));
    }
    
    @Override
    public Optional<RolDTO> findByTipo(String cedula) {

        return oneToDto(rolRepository.findByTipo(cedula));
    }
    
     @Override
    public Optional<List<RolDTO>> findByEstado(boolean estado) {
        return  findList(rolRepository.findByEstado(estado));
    }
    
    @Override
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
