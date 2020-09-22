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
public class UsuarioDTO {
 
    private Long id; 
    private String cedula; 
    private String nombreCompleto;   
    private boolean estado; 
    private String passwordEncriptado;
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private String correo; 
    private Rol rol;
     
}

