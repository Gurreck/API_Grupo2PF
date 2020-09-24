package org.una.aeropuerto.services;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.HoraMarcaje;
import org.una.aeropuerto.entities.Rol;

/**
 *
 * @author adria
 */
public interface IHoraMarcajeService {
    
    public Optional<List<HoraMarcaje>> findAll();

    public Optional<HoraMarcaje> findById(Long id);
    
    public Optional<List<HoraMarcaje>> findByHoraEntrada(Date horaEntrada);

    public Optional<List<HoraMarcaje>> findByHoraSalida(Date horaSalida);

    public Optional<List<HoraMarcaje>> findByFechaRegistro(Date fechaRegistro);
    
    public HoraMarcaje create(HoraMarcaje horaMarcaje);

    public Optional<HoraMarcaje> update(HoraMarcaje horaMarcaje, Long id);
}
