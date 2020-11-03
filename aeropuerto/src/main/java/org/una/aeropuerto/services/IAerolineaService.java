package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;

import org.una.aeropuerto.dto.AerolineaDTO;

/**
 *
 * @author a
 */
public interface IAerolineaService {
    
    public Optional<List<AerolineaDTO>> findAll();

    public Optional<AerolineaDTO> findById(Long id);

    public Optional<AerolineaDTO> findByNombreAerolineaAproximateIgnoreCase(String nombreAerolinea);

    public Optional<List<AerolineaDTO>> findByNombreResponsableAproximateIgnoreCase(String nombreResponsable);
    
    public Optional<List<AerolineaDTO>> findByEstado(boolean estado);

    public AerolineaDTO create(AerolineaDTO AerolineaDTO);

    public Optional<AerolineaDTO> update(AerolineaDTO AerolineaDTO, Long id);
    
}
