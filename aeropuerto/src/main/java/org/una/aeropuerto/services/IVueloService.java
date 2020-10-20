package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.VueloDTO;

/**
 *
 * @author adrian
 */
public interface IVueloService {
    
    public Optional<List<VueloDTO>> findAll();

    public Optional<VueloDTO> findById(Long id);

    public Optional<List<VueloDTO>> findByAeropuerto(String aeropueto);

    public Optional<List<VueloDTO>> findByEstado(boolean nombreCompleto);
    
    public Optional<List<VueloDTO>>findByFechaLlegada(Date startDate);
    
    public Optional<List<VueloDTO>>findByFechaSalida(Date startDate);

    public VueloDTO create(VueloDTO VueloDTO);

    public Optional<VueloDTO> update(VueloDTO VueloDTO, Long id);

}





















