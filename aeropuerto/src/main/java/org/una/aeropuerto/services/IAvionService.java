package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Avion;
import org.una.aeropuerto.entities.Usuario;

/**
 *
 * @author adrian
 */
public interface IAvionService {
    
    public Optional<List<Avion>> findAll();

    public Optional<Avion> findById(Long id);

    public Optional<List<Avion>> findByMatricula(String matricula);

    public Optional<List<Avion>> findByTipoAvion(String tipoAvion);
    
    public Optional<List<Avion>> findByFechaRegistro(Date fechaRegistro);
    
    public Optional<List<Avion>> findByEstado(boolean estado);

    public Avion create(Avion avion);

    public Optional<Avion> update(Avion avion, Long id);
}

