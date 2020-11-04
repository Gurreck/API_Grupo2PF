package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.UsuarioAreaTrabajoDTO;
import org.una.aeropuerto.entities.UsuarioAreaTrabajo;
import org.una.aeropuerto.repositories.IUsuarioAreaTrabajoRepository;
import org.una.aeropuerto.utils.MapperUtils;

@Service
public class UsuarioAreaTrabajoServiceImplementation  implements IUsuarioAreaTrabajoService {

    @Autowired
    private IUsuarioAreaTrabajoRepository usuarioAreaTrabajoRepository;

    private Optional<List<UsuarioAreaTrabajoDTO>> findList(List<UsuarioAreaTrabajo> list) {
        if (list != null) {
            List<UsuarioAreaTrabajoDTO> precioDTO = MapperUtils.DtoListFromEntityList(list, UsuarioAreaTrabajoDTO.class);
            return Optional.ofNullable(precioDTO);
        } else {
            return null;
        }
    }

    private Optional<List<UsuarioAreaTrabajoDTO>> findList(Optional<List<UsuarioAreaTrabajo>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<UsuarioAreaTrabajoDTO> oneToDto(Optional<UsuarioAreaTrabajo> one) {
        if (one.isPresent()) {
            UsuarioAreaTrabajoDTO usuarioAreaTrabajoDTO = MapperUtils.DtoFromEntity(one.get(), UsuarioAreaTrabajoDTO.class);
            return Optional.ofNullable(usuarioAreaTrabajoDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioAreaTrabajoDTO> findById(Long id) {
        return oneToDto(usuarioAreaTrabajoRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioAreaTrabajoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(usuarioAreaTrabajoRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional
    public UsuarioAreaTrabajoDTO create(UsuarioAreaTrabajoDTO usuarioAreaTrabajoDTO) {
        UsuarioAreaTrabajo usuarioAreaTrabajo = MapperUtils.EntityFromDto(usuarioAreaTrabajoDTO, UsuarioAreaTrabajo.class);
        usuarioAreaTrabajo = usuarioAreaTrabajoRepository.save(usuarioAreaTrabajo);
        return MapperUtils.DtoFromEntity(usuarioAreaTrabajo, UsuarioAreaTrabajoDTO.class);
    }


    @Override
    @Transactional
    public Optional<UsuarioAreaTrabajoDTO> update(UsuarioAreaTrabajoDTO usuarioAreaTrabajoDTO, Long id) {
        if (usuarioAreaTrabajoRepository.findById(id).isPresent()) {
            UsuarioAreaTrabajo precio = MapperUtils.EntityFromDto(usuarioAreaTrabajoDTO, UsuarioAreaTrabajo.class);
            precio.setId(id);
            precio = usuarioAreaTrabajoRepository.save(precio);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(precio, UsuarioAreaTrabajoDTO.class));
        } else {
            return null;
        }
    }
}
