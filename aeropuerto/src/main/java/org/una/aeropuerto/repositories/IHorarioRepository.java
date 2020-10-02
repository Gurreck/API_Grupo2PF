package org.una.aeropuerto.repositories;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Horario;

public interface IHorarioRepository extends JpaRepository<Horario, Long> {

  

    public List<Horario> findByEstado(boolean estado);

    public List<Horario> findByDiaEntrada(String diaEntrada);

    public List<Horario> findByDiaSalida(String diaSalida);
    
 
}
