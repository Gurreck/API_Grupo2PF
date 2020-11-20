package org.una.aeropuerto.entities;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Adrian
 */

@Entity
@Table(name = "usuarios_Areas_Trabajo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UsuarioAreaTrabajo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @ManyToOne 
    @JoinColumn(name="usuarios_id")
    private Usuario usuario;
    
    @ManyToOne 
    @JoinColumn(name="areasTrabajo_id")
    private AreaTrabajo areaTrabajo;
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
      
        fechaRegistro = new Date();
 
    }

    @PreUpdate
    public void preUpdate() {
        
    }
}

