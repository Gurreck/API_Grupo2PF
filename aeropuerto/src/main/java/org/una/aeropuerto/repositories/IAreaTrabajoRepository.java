package org.una.aeropuerto.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.AreaTrabajo;
import org.una.aeropuerto.entities.Usuario;

public interface IAreaTrabajoRepository extends JpaRepository<AreaTrabajo, Long> {

    public AreaTrabajo findByNombreArea(String area);

    public List<AreaTrabajo> findByNombreResponsable(String responsable);
 
}


