package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.una.aeropuerto.dto.AvionDTO;
import org.una.aeropuerto.entities.Usuario;

/**
 *
 * @author adrian
 */
public interface IAvionService {
    
    public Optional<List<AvionDTO>> findAll();

    public Optional<AvionDTO> findById(Long id);

    public Optional<List<AvionDTO>> findByMatricula(String matricula);

    public Optional<List<AvionDTO>> findByTipoAvion(String tipoAvion);
    
    public Optional<List<AvionDTO>> findByFechaRegistro(Date fechaRegistro);
    
    public Optional<List<AvionDTO>> findByEstado(boolean estado);

    public AvionDTO create(AvionDTO avionDTO);

    public Optional<AvionDTO> update(AvionDTO avionDTO, Long id);
}

