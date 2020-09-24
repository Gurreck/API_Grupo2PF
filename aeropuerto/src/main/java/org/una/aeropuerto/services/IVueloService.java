package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Vuelo;

/**
 *
 * @author adrian
 */
public interface IVueloService {
    
    public Optional<List<Vuelo>> findAll();

    public Optional<Vuelo> findById(Long id);

    public Optional<List<Vuelo>> findByAeropuerto(String aeropueto);

    public Optional<List<Vuelo>> findByEstado(boolean nombreCompleto);
    
    public Optional<List<Vuelo>>findByFechaLlegada(Date startDate);
    
    public Optional<List<Vuelo>>findByFechaSalida(Date startDate);

    public Vuelo create(Vuelo Vuelo);

    public Optional<Vuelo> update(Vuelo Vuelo, Long id);

}





















