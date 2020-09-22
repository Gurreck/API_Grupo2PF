package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.entities.Transaccion;
import org.una.aeropuerto.entities.Usuario;

/**
 *
 * @author adrian
 */
public interface ITransaccionService {
    
    public Optional<List<Transaccion>> findAll();

    public Optional<Transaccion> findById(Long id);
    
    public Optional<List<Transaccion>> findByEstado(boolean estado);

    public Transaccion create(Transaccion transaccion);

    public Optional<Transaccion> update(Transaccion transaccion, Long id);

}

