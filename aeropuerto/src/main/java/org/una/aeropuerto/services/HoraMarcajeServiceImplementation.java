package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.aeropuerto.entities.HoraMarcaje;
import org.una.aeropuerto.repositories.IHoraMarcajeRepository;


@Service
public class HoraMarcajeServiceImplementation implements IHoraMarcajeService {

    @Autowired
    private IHoraMarcajeRepository horaMarcajeRepository;
    
    @Override
    public Optional<List<HoraMarcaje>> findAll() {
       return Optional.ofNullable(horaMarcajeRepository.findAll());
    }

    @Override
    public Optional<HoraMarcaje> findById(Long id) {
        return horaMarcajeRepository.findById(id);
    }

    @Override
    public Optional<List<HoraMarcaje>> findByHoraEntrada(Date horaEntrada) {
        return  Optional.ofNullable(horaMarcajeRepository.findByHoraEntrada(horaEntrada));
    }

    @Override
    public Optional<List<HoraMarcaje>> findByHoraSalida(Date horaSalida) {
        return  Optional.ofNullable(horaMarcajeRepository.findByHoraSalida(horaSalida));
    }

    @Override
    public Optional<List<HoraMarcaje>> findByFechaRegistro(Date fechaRegistro) {
        return  Optional.ofNullable(horaMarcajeRepository.findByFechaRegistro(fechaRegistro));
    }

    @Override
    public HoraMarcaje create(HoraMarcaje horaMarcaje) {
         return horaMarcajeRepository.save(horaMarcaje);
    }

    @Override
    public Optional<HoraMarcaje> update(HoraMarcaje horaMarcaje, Long id) {
       if (horaMarcajeRepository.findById(id).isPresent()) {
            return Optional.ofNullable(horaMarcajeRepository.save(horaMarcaje));
        } else {
            return null;
        }
    }

    
}
