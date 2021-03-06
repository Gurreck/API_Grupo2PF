package org.una.aeropuerto.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.TransaccionDTO;
import org.una.aeropuerto.entities.Transaccion;
import org.una.aeropuerto.repositories.ITransaccionRepository;
import org.una.aeropuerto.utils.MapperUtils;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServiceImplementation implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    private Optional<List<TransaccionDTO>> findList(List<Transaccion> list) {
        if (list != null) {
            List<TransaccionDTO> transaccionDTO = MapperUtils.DtoListFromEntityList(list, TransaccionDTO.class);
            return Optional.ofNullable(transaccionDTO);
        } else {
            return null;
        }
    }

    private Optional<List<TransaccionDTO>> findList(Optional<List<Transaccion>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<TransaccionDTO> oneToDto(Optional<Transaccion> one) {
        if (one.isPresent()) {
            TransaccionDTO transaccionDTO = MapperUtils.DtoFromEntity(one.get(), TransaccionDTO.class);
            return Optional.ofNullable(transaccionDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDTO> findById(Long id) {
        return oneToDto(transaccionRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByEstadoAndTipo(boolean estado, String tipo) {
        return findList(transaccionRepository.findByEstadoAndTipo(estado, tipo));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByFechaRegistroBetweenAndTipo(Date startDate, Date endDate, String tipo) {
       return findList(transaccionRepository.findByFechaRegistroBetweenAndTipo(startDate, endDate, tipo));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByFechaRegistroBetweenAndTipoAndUsuarioUsuarioJefeId(Date startDate, Date endDate, String tipo, Long idJefe) {
       return findList(transaccionRepository.findByFechaRegistroBetweenAndTipoAndUsuarioUsuarioJefeId(startDate, endDate, tipo, idJefe));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByUsuarioIdAndTipo(Long id, String tipo) {
       return findList(transaccionRepository.findByUsuarioIdAndTipo(id, tipo));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByUsuarioIdAndTipoAndUsuarioUsuarioJefeId(Long id, String tipo, Long idJefe) {
       return findList(transaccionRepository.findByUsuarioIdAndTipoAndUsuarioUsuarioJefeId(id, tipo, idJefe));
    }
    
    @Override
    @Transactional
    public TransaccionDTO create(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = MapperUtils.EntityFromDto(transaccionDTO, Transaccion.class);
        transaccion = transaccionRepository.save(transaccion);
        return MapperUtils.DtoFromEntity(transaccion, TransaccionDTO.class);

    }
    @Override
    @Transactional
    public Optional<TransaccionDTO> update(TransaccionDTO transaccionDTO, Long id) {
        if (transaccionRepository.findById(id).isPresent()) {
            Transaccion transaccion = MapperUtils.EntityFromDto(transaccionDTO, Transaccion.class);
            transaccion.setId(id);
            transaccion = transaccionRepository.save(transaccion);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(transaccion, TransaccionDTO.class));
        } else {
            return null;
        }
    }    
}