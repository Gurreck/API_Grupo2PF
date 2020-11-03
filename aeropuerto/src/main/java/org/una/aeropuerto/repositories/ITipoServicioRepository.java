package org.una.aeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.TipoServicio;

/**
 *
 * @author Esteban Vargas
 */
public interface ITipoServicioRepository extends JpaRepository<TipoServicio, Long>{
    
    public TipoServicio findByNombreContainingIgnoreCase(String nombre);
}
