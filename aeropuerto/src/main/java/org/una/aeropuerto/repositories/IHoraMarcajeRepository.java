package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.HoraMarcaje;

public interface IHoraMarcajeRepository extends JpaRepository<HoraMarcaje, Long> {

    public List<HoraMarcaje> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public List<HoraMarcaje> findByUsuarioId(Long id);
    
    @Query(value = "SELECT mar FROM HoraMarcaje mar WHERE mar.id IN (SELECT MAX(hora.id) FROM HoraMarcaje hora JOIN hora.usuario usu WHERE usu.id = ?1)")
    public Optional<HoraMarcaje> findUltimaHoraMarcajeByUsuarioId(Long idUsuario); 
 
}

