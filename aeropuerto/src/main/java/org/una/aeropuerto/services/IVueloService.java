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
    
    public Optional<VueloDTO> findById(Long id);

    public Optional<List<VueloDTO>> findByAeropuertoAproximateIgnoreCase(String aeropueto);

    public Optional<List<VueloDTO>> findByEstado(boolean nombreCompleto);
    
    public Optional<List<VueloDTO>> findByFechaLlegadaBetween(Date startDate, Date endDate);
    
    public Optional<List<VueloDTO>> findByFechaSalidaBetween(Date startDate, Date endDate);
    
    public Optional<List<VueloDTO>> findByAvionId(Long avion);

    public VueloDTO create(VueloDTO VueloDTO);

    public Optional<VueloDTO> update(VueloDTO VueloDTO, Long id);

}





















