package org.una.aeropuerto.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.entities.Usuario;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class HoraMarcajeDTO {
 
    private Long id; 
    private Date horaEntrada;
    private Date horaSalida;
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private Usuario usuario;
     
}
