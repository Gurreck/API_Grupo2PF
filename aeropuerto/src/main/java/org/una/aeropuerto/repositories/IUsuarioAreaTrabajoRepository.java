package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.UsuarioAreaTrabajo;

public interface IUsuarioAreaTrabajoRepository extends JpaRepository<UsuarioAreaTrabajo, Long> {

     public List<UsuarioAreaTrabajo> findByFechaRegistroBetween(Date startDate, Date endDate);
 
}
