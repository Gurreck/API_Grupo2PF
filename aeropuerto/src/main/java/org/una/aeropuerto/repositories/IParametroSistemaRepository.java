package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.ParametroSistema;

public interface IParametroSistemaRepository extends JpaRepository<ParametroSistema, Long> {

    public List<ParametroSistema> findByNombre(String nombre);
    
    public List<ParametroSistema> findByEstado(boolean estado);

    public List<ParametroSistema> findByFechaRegistroBetween(Date startDate, Date endDate);
 
}


