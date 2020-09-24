package org.una.aeropuerto.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.aeropuerto.entities.Rol;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class AreaTrabajoDTO {
 
    private Long id;  
    private String nombreArea;   
    private boolean estado; 
    private Date fechaRegistro; 
    private String Responsable; 
     
}
