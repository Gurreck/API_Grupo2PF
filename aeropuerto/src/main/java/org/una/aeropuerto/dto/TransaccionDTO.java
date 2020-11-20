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
public class TransaccionDTO {
 
    private Long id; 
    private String informacion; 
    private boolean estado; 
    private Date fechaRegistro;
    private String tipo;
    private UsuarioDTO usuario;
     
}
