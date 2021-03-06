package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Rol;

public interface IRolRepository extends JpaRepository<Rol, Long> {

    public List<Rol> findByEstado(boolean estado);

    public List<Rol> findByFechaRegistroBetween(Date startDate, Date endDate);
     
    public List<Rol> findByNombreContainingIgnoreCase(String nombre);
 
    public Long countByEstado(boolean estado);
}
