
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.una.aeropuerto.dto.ParametroSistemaDTO;

/**
 *
 * @author adrian
 */
public interface IParametroSistemaService {
    
    public Optional<List<ParametroSistemaDTO>> findAll();

    public Optional<ParametroSistemaDTO> findById(Long id);
    
    public Optional<List<ParametroSistemaDTO>> findByEstado(boolean estado);
    
    public Optional<List<ParametroSistemaDTO>> findByNombre(String nombre);

    public Optional<List<ParametroSistemaDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public ParametroSistemaDTO create(ParametroSistemaDTO parametroSistema);

    public Optional<ParametroSistemaDTO> update(ParametroSistemaDTO parametroSistema, Long id);
}
