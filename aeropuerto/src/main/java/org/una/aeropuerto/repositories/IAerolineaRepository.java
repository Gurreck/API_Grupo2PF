package org.una.aeropuerto.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Aerolinea;

/**
 *
 * @author acer
 */
public interface IAerolineaRepository extends JpaRepository<Aerolinea, Long> {

    public List<Aerolinea> findByNombreResponsableContainingIgnoreCase(String nombreResponsable);

    public List<Aerolinea> findByNombreAerolineaContainingIgnoreCase(String nombreAerolinea);
    
    public List<Aerolinea> findByEstado(boolean estado);
}
