package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "aviones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Avion implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, unique = true)
    private String matricula;

    @Column(name = "tipo_avion", length = 50)
    private String tipoAvion;

    @Column
    private boolean estado;

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    
    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    
    @Column
    private Integer recorrido;
    
    @Column(name = "recorrido_max")
    private Integer recorridoMaximo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avion") 
    private List<Servicio> servicios = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avion") 
    private List<Vuelo> vuelos = new ArrayList<>();
    
    @ManyToOne 
    @JoinColumn(name="aerolineas_id")
    private Aerolinea aerolinea;
    
    @ManyToMany(mappedBy = "avion")
    private List<AreaTrabajo> areasTrabajo;

    
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

