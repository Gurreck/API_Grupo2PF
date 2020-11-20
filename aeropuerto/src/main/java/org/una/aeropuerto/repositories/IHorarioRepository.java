package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Horario;

public interface IHorarioRepository extends JpaRepository<Horario, Long> {

    public List<Horario> findByEstado(boolean estado);

    public List<Horario> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public List<Horario> findByUsuarioId(Long id);
    
    public List<Horario> findByEstadoAndUsuarioId(boolean estado,Long id);
 
}
