package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.TransaccionDTO;


/**
 *
 * @author adrian
 */
public interface ITransaccionService {
    
    public Optional<TransaccionDTO> findById(Long id);
    
    public Optional<List<TransaccionDTO>> findByEstado(boolean estado);
    
    public Optional<List<TransaccionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public Optional<List<TransaccionDTO>> findByUsuarioId(Long id);
    
    public Optional<List<TransaccionDTO>> findByTipo(String tipo);

    public TransaccionDTO create(TransaccionDTO transaccion);

    public Optional<TransaccionDTO> update(TransaccionDTO transaccion, Long id);

}

