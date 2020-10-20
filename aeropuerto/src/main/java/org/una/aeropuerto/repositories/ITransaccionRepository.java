package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Transaccion;

public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {

  

    public List<Transaccion> findByEstado(boolean estado);

    public List<Transaccion> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public List<Transaccion> findByUsuarioId(Long id);
     
 
}
