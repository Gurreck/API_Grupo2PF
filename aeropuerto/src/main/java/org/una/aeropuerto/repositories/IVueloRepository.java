package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Vuelo;

public interface IVueloRepository extends JpaRepository<Vuelo, Long> {

    public List<Vuelo> findByAeropuerto(String aeropueto);

    public List<Vuelo> findByEstado(boolean nombreCompleto);
    
    public List<Vuelo>findByFechaLlegada(Date startDate);
    
    public List<Vuelo>findByFechaSalida(Date startDate);
 
}