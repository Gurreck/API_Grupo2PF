package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ServicioDTO;
import org.una.aeropuerto.entities.Servicio;


/**
 *
 * @author Esteban Vargas
 */
public interface IServicioService {

    public Optional<ServicioDTO> findById(Long id);

    public Optional<List<ServicioDTO>> findByEstado(boolean estado);
    
    public Optional<List<ServicioDTO>> findByEstadoCobro(boolean estadoCobro);
    
    public Optional<List<ServicioDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<ServicioDTO>>  findByAvionId(Long avion);
    
    public Optional<List<ServicioDTO>>  findByTipoServicioId(Long tipoServicio);
    
     public Optional<List<ServicioDTO>> findByFechaRegistroBetweenAndTipoServicioId(Date startDate, Date endDate, Long tipoServicio );
    
    public Optional<List<ServicioDTO>> findByTipoServicioIdAndAvionId(Long tipoServicio, Long avion);

    public ServicioDTO create(ServicioDTO servicio);

    public Optional<ServicioDTO> update(ServicioDTO servicio, Long id);
}
