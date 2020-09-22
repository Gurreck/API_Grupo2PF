package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.entities.Transaccion;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.repositories.IRolRepository;
import org.una.aeropuerto.repositories.ITransaccionRepository;
import org.una.aeropuerto.repositories.IUsuarioRepository;


@Service
public class TransaccionServiceImplementation implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Override
    public Optional<List<Transaccion>> findAll() {
         return Optional.ofNullable(transaccionRepository.findAll());
    }

    @Override
    public Optional<Transaccion> findById(Long id) {
        return transaccionRepository.findById(id);
    }

    @Override
    public Optional<List<Transaccion>> findByEstado(boolean estado) {
       return Optional.ofNullable(transaccionRepository.findByEstado(estado));
    }

    @Override
    public Transaccion create(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    @Override
    public Optional<Transaccion> update(Transaccion transaccion, Long id) {
        if (transaccionRepository.findById(id).isPresent()) {
            return Optional.ofNullable(transaccionRepository.save(transaccion));
        } else {
            return null;
        }
    }

}