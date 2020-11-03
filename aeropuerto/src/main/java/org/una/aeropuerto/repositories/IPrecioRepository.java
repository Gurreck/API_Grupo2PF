package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Precio;

/**
 *
 * @author Esteban Vargas
 */
public interface IPrecioRepository extends JpaRepository<Precio, Long>{
    
    public List<Precio> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public List<Precio> findByTipoServicioId(Long tipoServicio);
}
