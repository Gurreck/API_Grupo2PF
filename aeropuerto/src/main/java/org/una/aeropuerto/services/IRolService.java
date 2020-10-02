package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;

import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.entities.Rol;

/**
 *
 * @author adria
 */
public interface IRolService {
    
    public Optional<List<RolDTO>> findAll();

    public Optional<RolDTO> findById(Long id);

    public Optional<RolDTO> findByTipo(String tipo);
    
    public Optional<List<RolDTO>> findByEstado(boolean estado);
    
    public Long countByEstado(boolean estado);

    public RolDTO create(RolDTO Rol);

    public Optional<RolDTO> update(RolDTO Rol, Long id);
}
