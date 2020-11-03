package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.AerolineaDTO;
import org.una.aeropuerto.entities.Aerolinea;
import org.una.aeropuerto.repositories.IAerolineaRepository;
import org.una.aeropuerto.utils.MapperUtils;

@Service
public class AerolineaServiceImplementation implements IAerolineaService {

    @Autowired
    private IAerolineaRepository AerolineaRepository;

    private Optional<List<AerolineaDTO>> findList(List<Aerolinea> list) {
        if (list != null) {
            List<AerolineaDTO> AerolineasDTO = MapperUtils.DtoListFromEntityList(list, AerolineaDTO.class);
            return Optional.ofNullable(AerolineasDTO);
        } else {
            return null;
        }
    }

    private Optional<List<AerolineaDTO>> findList(Optional<List<Aerolinea>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<AerolineaDTO> oneToDto(Optional<Aerolinea> one) {
        if (one.isPresent()) {
            AerolineaDTO AerolineaDTO = MapperUtils.DtoFromEntity(one.get(), AerolineaDTO.class);
            return Optional.ofNullable(AerolineaDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AerolineaDTO>> findAll() {
         return findList(AerolineaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AerolineaDTO> findById(Long id) {
        return oneToDto(AerolineaRepository.findById(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<AerolineaDTO>> findByNombreResponsableAproximateIgnoreCase(String nombreResponsable) {
        return findList(AerolineaRepository.findByNombreResponsableContainingIgnoreCase(nombreResponsable));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<AerolineaDTO> findByNombreAerolineaAproximateIgnoreCase(String nombreAerolinea) {
        return oneToDto(Optional.ofNullable(AerolineaRepository.findByNombreAerolineaContainingIgnoreCase(nombreAerolinea)));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<AerolineaDTO>> findByEstado(boolean estado) {
        return  findList(AerolineaRepository.findByEstado(estado));
    }
    
    @Override
    @Transactional
    public AerolineaDTO create(AerolineaDTO aerolineaDTO) {
        Aerolinea aerolinea = MapperUtils.EntityFromDto(aerolineaDTO, Aerolinea.class);
        aerolinea = AerolineaRepository.save(aerolinea);
        return MapperUtils.DtoFromEntity(aerolinea, AerolineaDTO.class);
    }

    @Override
    @Transactional
    public Optional<AerolineaDTO> update(AerolineaDTO aerolineaDTO, Long id) {
        if (AerolineaRepository.findById(id).isPresent()) {
            Aerolinea aerolinea = MapperUtils.EntityFromDto(aerolineaDTO, Aerolinea.class);
            aerolinea = AerolineaRepository.save(aerolinea);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(aerolinea, AerolineaDTO.class));
        } else {
            return null;
        }
    }
}

