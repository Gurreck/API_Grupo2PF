package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.TransaccionDTO;


/**
 *
 * @author adrian
 */
public interface ITransaccionService {
    
    public Optional<List<TransaccionDTO>> findAll();

    public Optional<TransaccionDTO> findById(Long id);
    
    public Optional<List<TransaccionDTO>> findByEstado(boolean estado);

    public TransaccionDTO create(TransaccionDTO transaccion);

    public Optional<TransaccionDTO> update(TransaccionDTO transaccion, Long id);

}

