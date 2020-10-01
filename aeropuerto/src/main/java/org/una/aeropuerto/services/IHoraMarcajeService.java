package org.una.aeropuerto.services;

import org.una.aeropuerto.dto.HoraMarcajeDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author adria
 */
public interface IHoraMarcajeService {
    
    public Optional<List<HoraMarcajeDTO>> findAll();

    public Optional<HoraMarcajeDTO> findById(Long id);
    
    public Optional<List<HoraMarcajeDTO>> findByHoraEntrada(Date horaEntrada);

    public Optional<List<HoraMarcajeDTO>> findByHoraSalida(Date horaSalida);

    public Optional<List<HoraMarcajeDTO>> findByFechaRegistro(Date fechaRegistro);
    
    public HoraMarcajeDTO create(HoraMarcajeDTO horaMarcajeDTO);

    public Optional<HoraMarcajeDTO> update(HoraMarcajeDTO horaMarcajeDTO, Long id);
}
