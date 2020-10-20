package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Aerolinea;
import org.una.aeropuerto.entities.AreaTrabajoAvion;
import org.una.aeropuerto.entities.Precio;

public interface IAreaTrabajoAvionRepository extends JpaRepository<AreaTrabajoAvion, Long>  {

    public Optional<List<AreaTrabajoAvion>> findByFechaRegistroBetween(Date startDate, Date endDate);

}
