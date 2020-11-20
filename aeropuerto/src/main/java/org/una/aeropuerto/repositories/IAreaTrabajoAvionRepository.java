package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.AreaTrabajoAvion;

public interface IAreaTrabajoAvionRepository extends JpaRepository<AreaTrabajoAvion, Long>  {

    public List<AreaTrabajoAvion> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<AreaTrabajoAvion> findByAvionId(Long id);
     
    public List<AreaTrabajoAvion> findByAreaTrabajoId(Long id);
    
    @Query(value = "SELECT trabAv FROM AreaTrabajoAvion trabAv JOIN trabAv.avion av JOIN av.aerolinea aero WHERE (trabAv.fechaRegistro BETWEEN ?1 AND ?2) AND trabAv.avion IN (SELECT av FROM Avion av JOIN av.aerolinea aerol WHERE aerol.id = ?3 AND trabAv.areaTrabajo IN (SELECT areaT FROM AreaTrabajo areaT WHERE areaT.id = ?4))")
    public List<AreaTrabajoAvion> findByFechaRegistroAndAerolineaAndZona(Date startDate, Date endDate, long idAerolinea, long idZona);

}
