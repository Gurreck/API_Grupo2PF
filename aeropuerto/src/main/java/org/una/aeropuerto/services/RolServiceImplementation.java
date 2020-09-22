package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.repositories.IRolRepository;
import org.una.aeropuerto.repositories.IUsuarioRepository;


@Service
public class RolServiceImplementation implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public Optional<List<Rol>> findAll() {
         return Optional.ofNullable(rolRepository.findAll());
    }

    @Override
    public Optional<Rol> findById(Long id) {
        return rolRepository.findById(id);
    }
    
    @Override
    public Optional<List<Rol>> findByTipo(String cedula) {
        return Optional.ofNullable(rolRepository.findByTipo(cedula));
    }
    
     @Override
    public Optional<List<Rol>> findByEstado(boolean estado) {
        return  Optional.ofNullable(rolRepository.findByEstado(estado));
    }
    
    @Override
    public Rol create(Rol Rol) {
       return rolRepository.save(Rol);
    }

    @Override
    public Optional<Rol> update(Rol Rol, Long id) {
        
        if (rolRepository.findById(id).isPresent()) {
            return Optional.ofNullable(rolRepository.save(Rol));
        } else {
            return null;
        }
    }  

   

    
}
