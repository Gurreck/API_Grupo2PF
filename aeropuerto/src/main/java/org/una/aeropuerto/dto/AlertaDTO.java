package org.una.aeropuerto.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Esteban Vargas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class AlertaDTO {
    
    private Long id; 
    private String descripcion;   
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private boolean estado; 
    private VueloDTO vuelo; 
}
