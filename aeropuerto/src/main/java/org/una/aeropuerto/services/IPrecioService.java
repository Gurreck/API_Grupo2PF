package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Precio;

/**
 *
 * @author Esteban Vargas
 */
public interface IPrecioService {
    
    public Optional<List<Precio>> findAll();

    public Optional<Precio> findById(Long id);

    public Optional<List<Precio>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<Precio>> findByTipoServicioId(Long tipoServicioId);
    
    public Precio create(Precio precio);

    public Optional<Precio> update(Precio precio, Long id);
}
