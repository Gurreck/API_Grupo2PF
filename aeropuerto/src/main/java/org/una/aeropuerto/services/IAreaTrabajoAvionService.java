package org.una.aeropuerto.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.una.aeropuerto.dto.AreaTrabajoAvionDTO;
import org.una.aeropuerto.dto.AreaTrabajoDTO;
import org.una.aeropuerto.dto.PrecioDTO;
import org.una.aeropuerto.entities.AreaTrabajoAvion;

public interface IAreaTrabajoAvionService {

    public Optional<AreaTrabajoAvionDTO> findById(Long id);

    public Optional<List<AreaTrabajoAvionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public AreaTrabajoAvionDTO create(AreaTrabajoAvionDTO AreaTrabajoDTO);

    public Optional<AreaTrabajoAvionDTO> update(AreaTrabajoAvionDTO AreaTrabajoDTO, Long id);
}
