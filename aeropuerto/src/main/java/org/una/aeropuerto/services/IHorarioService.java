
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.HorarioDTO;

/**
 *
 * @author Adrian
 */
public interface IHorarioService {
    
    public Optional<HorarioDTO> findById(Long id);
    
    public Optional<List<HorarioDTO>> findByEstado(boolean estado);
    
    public Optional<List<HorarioDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<HorarioDTO>> findByUsuarioId(Long id);
    
    public Optional<List<HorarioDTO>> findByEstadoAndUsuarioId(boolean estado, Long id);
    
    public HorarioDTO create(HorarioDTO horario);

    public Optional<HorarioDTO> update(HorarioDTO horario, Long id);
}