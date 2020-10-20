package org.una.aeropuerto.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class AerolineaDTO {
    
    private Long id; 
    private String nombreAerolinea;
    private String nombreResponsable;  
    private Date fechaRegistro;
    private Date fechaModificacion;
    private boolean estado;
}
