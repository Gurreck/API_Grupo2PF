
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.ParametroSistema;

/**
 *
 * @author adria
 */
public interface IParametroSistemaService {
    
    public Optional<List<ParametroSistema>> findAll();

    public Optional<ParametroSistema> findById(Long id);
    
    public Optional<List<ParametroSistema>> findByEstado(boolean estado);
    
    public Optional<List<ParametroSistema>> findByNombre(String nombre);

    public Optional<List<ParametroSistema>> findByFechaRegistro(Date fechaRegistro);
    
    public ParametroSistema create(ParametroSistema parametroSistema);

    public Optional<ParametroSistema> update(ParametroSistema parametroSistema, Long id);
}
