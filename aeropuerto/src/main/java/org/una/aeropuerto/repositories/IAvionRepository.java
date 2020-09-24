package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Aerolinea;
import org.una.aeropuerto.entities.Avion;

public interface IAvionRepository extends JpaRepository<Avion, Long> {

    public List<Avion> findByMatricula(String matricula);

    public List<Avion> findByTipoAvion(String tipoAvion);

    public List<Avion> findByFechaRegistro(Date fechaRegistro);

    public List<Avion> findByEstado(boolean estado);
}
