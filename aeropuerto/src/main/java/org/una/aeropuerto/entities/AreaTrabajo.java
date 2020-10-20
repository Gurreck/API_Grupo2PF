package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * @author adrian
 */

@Entity
@Table(name = "areas_trabajo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AreaTrabajo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_area", length = 50)
    private String nombreArea;

    @Column
    private boolean estado;

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP) 
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    
    @Column(name = "nombre_responsable", length = 50)
    private String nombreResponsable;
    
    /*@ManyToMany(mappedBy = "areaTrabajo")
    private List<Usuario> usuarios;*/
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "areasTrabajo_aviones", 
      joinColumns = @JoinColumn(name = "aviones_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "areas_trabajo_id", 
      referencedColumnName = "id"))
    private List<Avion> avion;
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado=true;
        fechaRegistro = new Date();
        
    }

    @PreUpdate
    public void preUpdate() {
        
    }
}

