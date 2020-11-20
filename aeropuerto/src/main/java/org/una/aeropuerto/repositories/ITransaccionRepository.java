package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Transaccion;

public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {

    public List<Transaccion> findByEstadoAndTipo(boolean estado, String tipo);

    public List<Transaccion> findByFechaRegistroBetweenAndTipo(Date startDate, Date endDate, String tipo);
    
    public List<Transaccion> findByFechaRegistroBetweenAndTipoAndUsuarioUsuarioJefeId(Date startDate, Date endDate, String tipo, Long idJefe);
    
    public List<Transaccion> findByUsuarioIdAndTipo(Long id, String tipo);     
    
    public List<Transaccion> findByUsuarioIdAndTipoAndUsuarioUsuarioJefeId(Long id, String tipo, Long idJefe);     
 
}
