package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Avion;

public interface IAvionRepository extends JpaRepository<Avion, Long> {

    public List<Avion> findByMatriculaContaining(String matricula);

    public List<Avion> findByTipoAvion(String tipoAvion);

    public List<Avion> findByFechaRegistroBetween(Date startDate, Date endDate);

    public List<Avion> findByEstado(boolean estado);
    
    public List<Avion> findByAerolineaId(Long aerolinea);
}
