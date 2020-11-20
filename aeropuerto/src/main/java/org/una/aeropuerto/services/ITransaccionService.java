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
    
    public Optional<List<TransaccionDTO>> findByEstadoAndTipo(boolean estado, String tipo);
    
    public Optional<List<TransaccionDTO>> findByFechaRegistroBetweenAndTipo(Date startDate, Date endDate, String tipo);
    
    public Optional<List<TransaccionDTO>> findByFechaRegistroBetweenAndTipoAndUsuarioUsuarioJefeId(Date startDate, Date endDate, String tipo, Long idJefe);
    
    public Optional<List<TransaccionDTO>> findByUsuarioIdAndTipo(Long id, String tipo);
    
    public Optional<List<TransaccionDTO>> findByUsuarioIdAndTipoAndUsuarioUsuarioJefeId(Long id, String tipo, Long idJefe);

    public TransaccionDTO create(TransaccionDTO transaccion);

    public Optional<TransaccionDTO> update(TransaccionDTO transaccion, Long id);

}

