package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    
    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    
    @Column(name = "nombre_responsable", length = 50)
    private String nombreResponsable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaTrabajo")
    private List<UsuarioAreaTrabajo> UsuariosAreasTrabajo = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaTrabajo")
    private List<AreaTrabajoAvion> AreasTrabajoAviones = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado=true;
        fechaRegistro = new Date();
        fechaModificacion = new Date();
        
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }
}

