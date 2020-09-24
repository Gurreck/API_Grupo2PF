package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Aerolinea;

/**
 *
 * @author a
 */
public interface IAerolineaService {
    
    public Optional<List<Aerolinea>> findAll();

    public Optional<Aerolinea> findById(Long id);

    public Optional<Aerolinea> findByNombreAerolinea(String nombreAerolinea);

    public Optional<List<Aerolinea>> findByNombreResponsable(String nombreResponsable);
    
     public Optional<List<Aerolinea>> findByEstado(boolean estado);

    public Aerolinea create(Aerolinea Aerolinea);

    public Optional<Aerolinea> update(Aerolinea Aerolinea, Long id);
    
}
