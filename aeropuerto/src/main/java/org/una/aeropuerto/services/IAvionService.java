package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.una.aeropuerto.dto.AvionDTO;

/**
 *
 * @author adrian
 */
public interface IAvionService {
    
    public Optional<List<AvionDTO>> findAll();

    public Optional<AvionDTO> findById(Long id);

    public Optional<AvionDTO> findByMatricula(String matricula);

    public Optional<List<AvionDTO>> findByTipoAvion(String tipoAvion);
    
    public Optional<List<AvionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<AvionDTO>> findByEstado(boolean estado);
    
    public Optional<List<AvionDTO>> findByAerolineaId(Long aerolinea);

    public AvionDTO create(AvionDTO avionDTO);

    public Optional<AvionDTO> update(AvionDTO avionDTO, Long id);
}

