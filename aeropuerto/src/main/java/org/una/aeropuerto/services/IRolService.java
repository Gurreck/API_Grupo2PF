package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Rol;

/**
 *
 * @author adria
 */
public interface IRolService {
    
    public Optional<List<Rol>> findAll();

    public Optional<Rol> findById(Long id);

    public Optional<List<Rol>> findByTipo(String tipo);
    
    public Optional<List<Rol>> findByEstado(boolean estado);

    public Rol create(Rol Rol);

    public Optional<Rol> update(Rol Rol, Long id);
}
