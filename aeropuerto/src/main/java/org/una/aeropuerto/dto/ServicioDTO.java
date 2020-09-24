package org.una.aeropuerto.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.aeropuerto.entities.TipoServicio;

/**
 *
 * @author Esteban Vargas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class ServicioDTO {
    
    private Long id;  
    private boolean estadoCobro; 
    private boolean estado; 
    private Date fechaRegistro;
    private String factura;
    private String responsable; 
    private String observacion;  
    private TipoServicio tipoServicio;
 //   private Avion avion;
}
