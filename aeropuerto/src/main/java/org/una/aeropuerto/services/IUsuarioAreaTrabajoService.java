package org.una.aeropuerto.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.una.aeropuerto.dto.UsuarioAreaTrabajoDTO;

public interface IUsuarioAreaTrabajoService {

    public Optional<UsuarioAreaTrabajoDTO> findById(Long id);

    public Optional<List<UsuarioAreaTrabajoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<UsuarioAreaTrabajoDTO>> findByUsuarioId(Long id);
     
    public Optional<List<UsuarioAreaTrabajoDTO>> findByAreaTrabajoId(Long id);

    public UsuarioAreaTrabajoDTO create(UsuarioAreaTrabajoDTO UsuarioAreaTrabajoDTO);

    public Optional<UsuarioAreaTrabajoDTO> update(UsuarioAreaTrabajoDTO UsuarioAreaTrabajoDTO, Long id);
}

