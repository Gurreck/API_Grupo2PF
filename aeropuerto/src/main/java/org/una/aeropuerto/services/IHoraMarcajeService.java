package org.una.aeropuerto.services;

import org.una.aeropuerto.dto.HoraMarcajeDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author adria
 */
public interface IHoraMarcajeService {
    
    public Optional<HoraMarcajeDTO> findById(Long id);
    
    public Optional<List<HoraMarcajeDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<HoraMarcajeDTO>> findByFechaRegistroBetweenAndUsuarioId(Date startDate, Date endDate, Long id);
    
    public Optional<List<HoraMarcajeDTO>> findByUsuarioId(Long id);
    
    public Optional<HoraMarcajeDTO> findUltimaHoraMarcajeByUsuarioId(Long idUsuario); 
    
    public HoraMarcajeDTO create(HoraMarcajeDTO horaMarcajeDTO);

    public Optional<HoraMarcajeDTO> update(HoraMarcajeDTO horaMarcajeDTO, Long id);
}
