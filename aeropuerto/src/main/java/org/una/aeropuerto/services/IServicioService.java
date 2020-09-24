package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Servicio;

/**
 *
 * @author Esteban Vargas
 */
public interface IServicioService {
    
    public Optional<List<Servicio>> findAll();

    public Optional<Servicio> findById(Long id);


    public Optional<List<Servicio>> findByEstado(boolean estado);
    
    public Optional<List<Servicio>> findByEstadoCobro(boolean estadoCobro);
    
    public Optional<List<Servicio>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
   // public Optional<List<Servicio>>  findByAvionId(Long id);
    
    public Optional<List<Servicio>>  findByTipoServicioId(Long id);

    public Servicio create(Servicio servicio);

    public Optional<Servicio> update(Servicio servicio, Long id);
}
