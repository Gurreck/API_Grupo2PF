package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.TipoServicioDTO;

/**
 *
 * @author Esteban Vargas
 */
public interface ITipoServicioService {
    
    public Optional<List<TipoServicioDTO>> findAll();

    public Optional<TipoServicioDTO> findById(Long id);

    public Optional<TipoServicioDTO> findByNombre(String nombre);
    
    public TipoServicioDTO create(TipoServicioDTO tipoServicio);

    public Optional<TipoServicioDTO> update(TipoServicioDTO tipoServicio, Long id);
}
