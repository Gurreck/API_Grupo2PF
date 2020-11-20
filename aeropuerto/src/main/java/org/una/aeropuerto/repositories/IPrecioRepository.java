package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.Precio;

/**
 *
 * @author Esteban Vargas
 */
public interface IPrecioRepository extends JpaRepository<Precio, Long>{
    
    public List<Precio> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public List<Precio> findByTipoServicioId(Long tipoServicio);
    
    @Query(value = "SELECT pre FROM Precio pre WHERE (pre.fechaRegistro BETWEEN ?1 AND ?2) AND pre.tipoServicio IN (SELECT tipoSe FROM TipoServicio tipoSe WHERE tipoSe.id = ?3)")
    public List<Precio> findByTipoServicioIdAndFechaRegistroBetween(Date fechInicio, Date fechaFinal, Long tipoServicio);
}
