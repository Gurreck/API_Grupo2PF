package org.una.aeropuerto.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.una.aeropuerto.dto.AreaTrabajoAvionDTO;

public interface IAreaTrabajoAvionService {

    public Optional<AreaTrabajoAvionDTO> findById(Long id);

    public Optional<List<AreaTrabajoAvionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<AreaTrabajoAvionDTO> findByAvionId(Long id);
     
    public Optional<List<AreaTrabajoAvionDTO>> findByAreaTrabajoId(Long id);

    public AreaTrabajoAvionDTO create(AreaTrabajoAvionDTO AreaTrabajoDTO);

    public Optional<AreaTrabajoAvionDTO> update(AreaTrabajoAvionDTO AreaTrabajoDTO, Long id);
}
