package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.VueloDTO;
import org.una.aeropuerto.entities.Vuelo;
import org.una.aeropuerto.repositories.IVueloRepository;
import org.una.aeropuerto.utils.MapperUtils;


@Service
public class VueloServiceImplementation implements IVueloService {

    @Autowired
    private IVueloRepository vueloRepository;

    private Optional<List<VueloDTO>> findList(List<Vuelo> list) {
        if (list != null) {
            List<VueloDTO> vueloDTO = MapperUtils.DtoListFromEntityList(list, VueloDTO.class);
            return Optional.ofNullable(vueloDTO);
        } else {
            return null;
        }
    }

    private Optional<List<VueloDTO>> findList(Optional<List<Vuelo>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<VueloDTO> oneToDto(Optional<Vuelo> one) {
        if (one.isPresent()) {
            VueloDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(), VueloDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VueloDTO> findById(Long id) {
        return oneToDto(vueloRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VueloDTO>> findByAeropuerto(String cedula) {
        return findList(vueloRepository.findByAeropuerto(cedula));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<VueloDTO>> findByEstado(boolean estado) {
        return findList(vueloRepository.findByEstado(estado));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<VueloDTO>> findByFechaLlegadaBetween(Date startDate, Date endDate) {
        return findList(vueloRepository.findByFechaLlegadaBetween(startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VueloDTO>> findByFechaSalidaBetween(Date startDate, Date endDate) {
        return findList(vueloRepository.findByFechaSalidaBetween(startDate, endDate));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<VueloDTO>> findByAvionId(Long avion) {
        return findList(vueloRepository.findByAvionId(avion));
    }

    @Override
    @Transactional
    public VueloDTO create(VueloDTO vueloDTO) {
        Vuelo vuelo = MapperUtils.EntityFromDto(vueloDTO, Vuelo.class);
        vuelo = vueloRepository.save(vuelo);
        return MapperUtils.DtoFromEntity(vuelo, VueloDTO.class);

    }
    @Override
    @Transactional
    public Optional<VueloDTO> update(VueloDTO vueloDTO, Long id) {
        if (vueloRepository.findById(id).isPresent()) {
            Vuelo vuelo = MapperUtils.EntityFromDto(vueloDTO, Vuelo.class);
            vuelo= vueloRepository.save(vuelo);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(vuelo, VueloDTO.class));

        } else {
            return null;
        }

    }    
 
}
