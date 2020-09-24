package org.una.aeropuerto.services;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.aeropuerto.entities.Horario;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.repositories.IHorarioRepository;




@Service
public class HorarioServiceImplementation implements IHorarioService {

    @Autowired
    private IHorarioRepository horarioRepository;

    @Override
    public Optional<List<Horario>> findAll() {
        return Optional.ofNullable(horarioRepository.findAll());
    }

    @Override
    public Optional<Horario> findById(Long id) {
        return horarioRepository.findById(id);
    }

    @Override
    public Optional<List<Horario>> findByEstado(boolean estado) {
        return Optional.ofNullable(horarioRepository.findByEstado(estado));
    }

    @Override
    public Optional<List<Horario>> findByDiaEntrada(Time diaEntrada) {
        return Optional.ofNullable(horarioRepository.findByDiaEntrada(diaEntrada));
    }

    @Override
    public Optional<List<Horario>> findByDiaSalida(Time diaSalida) {
        return Optional.ofNullable(horarioRepository.findByDiaSalida(diaSalida));
    }

    @Override
    public Horario create(Horario horario) {
 return horarioRepository.save(horario);
    }

    @Override
    public Optional<Horario> update(Horario horario, Long id) {
       
        if (horarioRepository.findById(id).isPresent()) {
            return Optional.ofNullable(horarioRepository.save(horario));
        } else {
            return null;
        }
    }
    
}
