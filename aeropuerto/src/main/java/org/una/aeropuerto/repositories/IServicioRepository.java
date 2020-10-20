package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Servicio;

/**
 *
 * @author Esteban Vargas
 */
public interface IServicioRepository extends JpaRepository<Servicio, Long> {

    public List<Servicio> findByEstado(boolean estado);
    
    public List<Servicio> findByEstadoCobro(boolean estadoCobro);

    public Optional<List<Servicio>> findByFechaRegistroBetween(Date startDate, Date endDate);
     
    public List<Servicio> findByAvionId(Long avion);
    
    public List<Servicio> findByTipoServicioId(Long tipoServicio);
    
    public List<Servicio> findByTipoServicioIdAndAvionId(Long tipoServicio, Long avion);
 
}