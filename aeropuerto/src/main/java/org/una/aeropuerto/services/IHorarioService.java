
package org.una.aeropuerto.services;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.HorarioDTO;
import org.una.aeropuerto.entities.Horario;
import org.una.aeropuerto.entities.Rol;

/**
 *
 * @author adria
 */
public interface IHorarioService {
    
    public Optional<HorarioDTO> findById(Long id);
    
    public Optional<List<HorarioDTO>> findByEstado(boolean estado);
    
    public Optional<List<HorarioDTO>> findByDiaEntrada(String diaEntrada);

    public Optional<List<HorarioDTO>> findByDiaSalida(String diaSalida);
    
    public HorarioDTO create(HorarioDTO horario);

    public Optional<HorarioDTO> update(HorarioDTO horario, Long id);
}