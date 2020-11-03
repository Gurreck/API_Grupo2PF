package org.una.aeropuerto.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.AreaTrabajo;

public interface IAreaTrabajoRepository extends JpaRepository<AreaTrabajo, Long> {

    public List<AreaTrabajo> findByNombreAreaContainingIgnoreCase(String area);

    public List<AreaTrabajo> findByNombreResponsableContainingIgnoreCase(String responsable);
 
}


