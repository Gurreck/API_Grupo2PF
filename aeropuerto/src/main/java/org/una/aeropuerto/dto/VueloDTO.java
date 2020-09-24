package org.una.aeropuerto.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.aeropuerto.entities.Avion;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class VueloDTO {
 
    private Long id; 
    private Float duracion; 
    private String aeropuerto;   
    private Date fechaSalida; 
    private Date fechaLlegada; 
    private Long distancia; 
    private boolean estado; 
    private Avion avion;
     
}
