package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.una.aeropuerto.dto.RolDTO;

/**
 *
 * @author adria
 */
public interface IRolService {
    
    public Optional<List<RolDTO>> findAll();

    public Optional<RolDTO> findById(Long id);

    public Optional<RolDTO> findByNombreAproximateIgnoreCase(String nombre);
    
    public Optional<List<RolDTO>> findByEstado(boolean estado);
    
    public Optional<List<RolDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Long countByEstado(boolean estado);

    public RolDTO create(RolDTO Rol);

    public Optional<RolDTO> update(RolDTO Rol, Long id);
}
