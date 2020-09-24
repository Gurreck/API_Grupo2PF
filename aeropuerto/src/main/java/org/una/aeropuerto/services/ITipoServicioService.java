package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.TipoServicio;

/**
 *
 * @author Esteban Vargas
 */
public interface ITipoServicioService {
    
    public Optional<List<TipoServicio>> findAll();

    public Optional<TipoServicio> findById(Long id);

    public Optional<List<TipoServicio>> findByNombre(String nombre);
    
    public TipoServicio create(TipoServicio tipoServicio);

    public Optional<TipoServicio> update(TipoServicio tipoServicio, Long id);
}
