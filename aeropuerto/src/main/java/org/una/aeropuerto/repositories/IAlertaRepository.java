package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Alerta;

/**
 *
 * @author Esteban Vargas
 */
public interface IAlertaRepository extends JpaRepository<Alerta, Long>{
    
    public List<Alerta> findByFechaRegistroBetween(Date startDate, Date endDate);

    public List<Alerta> findByEstado(boolean estado);
    
    public List<Alerta> findByVueloId(Long vuelo);
}
