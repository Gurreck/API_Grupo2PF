package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.una.aeropuerto.dto.PrecioDTO;

/**
 *
 * @author Esteban Vargas
 */
public interface IPrecioService {

    public Optional<PrecioDTO> findById(Long id);

    public Optional<List<PrecioDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Optional<List<PrecioDTO>> findByTipoServicioId(Long tipoServicioId);
    
    public Optional<List<PrecioDTO>> findByTipoServicioIdAndFechaRegistroBetween(Date fechInicio, Date fechaFinal, Long tipoServicio);

    public PrecioDTO create(PrecioDTO precio);

    public Optional<PrecioDTO> update(PrecioDTO precio, Long id);
}
