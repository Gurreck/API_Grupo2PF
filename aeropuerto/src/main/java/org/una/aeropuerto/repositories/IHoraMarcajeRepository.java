package org.una.aeropuerto.repositories;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.HoraMarcaje;

public interface IHoraMarcajeRepository extends JpaRepository<HoraMarcaje, Long> {

    public List<HoraMarcaje> findByHoraEntrada(Date horaEntrada);

    public List<HoraMarcaje> findByHoraSalida(Date horaSalida);

    public List<HoraMarcaje> findByFechaRegistro(Date fechaRegistro);
 
}

