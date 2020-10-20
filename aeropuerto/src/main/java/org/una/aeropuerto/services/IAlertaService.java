package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AlertaDTO;
/**
 *
 * @author Esteban Vargas
 */
public interface IAlertaService {
    
    public Optional<AlertaDTO> findById(Long id);
    
    public Optional<List<AlertaDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<AlertaDTO>> findByEstado(boolean estado);
    
    public Optional<List<AlertaDTO>> findByVueloId(Long vuelo);
    
    public AlertaDTO create(AlertaDTO alertaDTO);

    public Optional<AlertaDTO> update(AlertaDTO alertaDTO, Long id);
}
