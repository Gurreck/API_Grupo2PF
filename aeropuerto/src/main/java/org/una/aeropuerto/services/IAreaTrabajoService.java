package org.una.aeropuerto.services;

import org.una.aeropuerto.dto.AreaTrabajoDTO;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author acer
 */
public interface IAreaTrabajoService {
    public Optional<List<AreaTrabajoDTO>> findAll();

    public Optional<AreaTrabajoDTO> findById(Long id);

    public Optional<List<AreaTrabajoDTO>> findByNombreResponsableAproximateIgnoreCase(String responsable);

    public Optional<AreaTrabajoDTO> findByNombreAreaAproximateIgnoreCase(String area);

    public AreaTrabajoDTO create(AreaTrabajoDTO AreaTrabajoDTO);

    public Optional<AreaTrabajoDTO> update(AreaTrabajoDTO AreaTrabajoDTO, Long id);

}
