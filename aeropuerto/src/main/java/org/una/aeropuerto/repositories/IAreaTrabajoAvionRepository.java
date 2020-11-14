package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.AreaTrabajoAvion;

public interface IAreaTrabajoAvionRepository extends JpaRepository<AreaTrabajoAvion, Long>  {

    public List<AreaTrabajoAvion> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<AreaTrabajoAvion> findByAvionId(Long id);
     
    public List<AreaTrabajoAvion> findByAreaTrabajoId(Long id);

}
