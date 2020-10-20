
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.una.aeropuerto.dto.ParametroSistemaDTO;
import org.una.aeropuerto.entities.ParametroSistema;

/**
 *
 * @author adrian
 */
public interface IParametroSistemaService {
    
    public Optional<List<ParametroSistemaDTO>> findAll();

    public Optional<ParametroSistemaDTO> findById(Long id);
    
    public Optional<List<ParametroSistemaDTO>> findByEstado(boolean estado);
    
    public Optional<List<ParametroSistemaDTO>> findByNombre(String nombre);

    public Optional<List<ParametroSistemaDTO>> findByFechaRegistro(Date fechaRegistro);
    
    public ParametroSistemaDTO create(ParametroSistemaDTO parametroSistema);

    public Optional<ParametroSistemaDTO> update(ParametroSistemaDTO parametroSistema, Long id);
}
