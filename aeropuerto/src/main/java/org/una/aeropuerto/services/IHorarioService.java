
package org.una.aeropuerto.services;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Horario;
import org.una.aeropuerto.entities.Rol;

/**
 *
 * @author adria
 */
public interface IHorarioService {
    
    public Optional<List<Horario>> findAll();

    public Optional<Horario> findById(Long id);
    
    public Optional<List<Horario>> findByEstado(boolean estado);
    
    public Optional<List<Horario>> findByDiaEntrada(Time diaEntrada);

    public Optional<List<Horario>> findByDiaSalida(Time diaSalida);
    
    public Horario create(Horario horario);

    public Optional<Horario> update(Horario horario, Long id);
}