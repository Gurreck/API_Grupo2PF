package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.Servicio;

/**
 *
 * @author Esteban Vargas
 */
public interface IServicioRepository extends JpaRepository<Servicio, Long> {

    public List<Servicio> findByEstado(boolean estado);
    
    public List<Servicio> findByEstadoCobro(boolean estadoCobro);

    public List<Servicio> findByFechaRegistroBetween(Date startDate, Date endDate);
     
    public List<Servicio> findByAvionId(Long avion);
    
    public List<Servicio> findByTipoServicioId(Long tipoServicio);
    
    public List<Servicio> findByFechaRegistroBetweenAndTipoServicioId(Date startDate, Date endDate, Long tipoServicio );
    
    public List<Servicio> findByTipoServicioIdAndAvionId(Long tipoServicio, Long avion);
    
    @Query(value = "SELECT ser FROM Servicio ser WHERE ser.id IN (SELECT MAX(servicio.id) FROM Servicio servicio JOIN servicio.avion avi WHERE avi.id = ?1 AND servicio.tipoServicio.id = ?2)")
    public Optional<Servicio> findUltimoServicioByAvionIdAndTipoServicioId(Long idAvion, Long idTipo); 
 
}