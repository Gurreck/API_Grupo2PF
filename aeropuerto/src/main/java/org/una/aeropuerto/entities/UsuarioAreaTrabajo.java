package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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

