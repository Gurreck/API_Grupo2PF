package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Vuelo;

public interface IVueloRepository extends JpaRepository<Vuelo, Long> {

    public List<Vuelo> findByAeropuerto(String aeropueto);

    public List<Vuelo> findByEstado(boolean estado);
    
    public List<Vuelo> findByFechaLlegadaBetween(Date startDate, Date endDate);
    
    public List<Vuelo> findByFechaSalidaBetween(Date startDate, Date endDate);
    
    public List<Vuelo> findByAvionId(Long avion);
 
}