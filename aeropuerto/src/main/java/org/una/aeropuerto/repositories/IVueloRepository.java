package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.Vuelo;

public interface IVueloRepository extends JpaRepository<Vuelo, Long> {

    public List<Vuelo> findByAeropuertoContainingIgnoreCase(String aeropueto);

    public List<Vuelo> findByEstado(boolean estado);
    
    public List<Vuelo> findByFechaLlegadaBetween(Date startDate, Date endDate);
    
    public List<Vuelo> findByFechaSalidaBetween(Date startDate, Date endDate);
    
    public List<Vuelo> findByAvionId(Long avion);
    
    @Query(value = "SELECT vu FROM Vuelo vu WHERE vu.id IN (SELECT MAX(vuelo.id)-1 FROM Vuelo vuelo JOIN vuelo.avion avi WHERE avi.id = ?1)")
    public Optional<Vuelo> findUltimoVueloByAvionId(Long idVuelo); 
    
    @Query(value = "SELECT vu FROM Vuelo vu WHERE vu.avion IN (SELECT av FROM Avion av WHERE av.aerolinea IN (SELECT aero FROM Aerolinea aero WHERE aero.id = ?1))")
    public List<Vuelo> findByAerolineaId(Long aerolineaId);
    
}