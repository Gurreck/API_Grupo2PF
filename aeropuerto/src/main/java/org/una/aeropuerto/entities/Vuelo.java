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
 * @author acer
 */

@Entity
@Table(name = "vuelos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Vuelo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private Float duracion;
    
    @Column(name = "aeropuerto", length = 100)
    private String aeropuerto;

    @Column(name = "fecha_salida", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaSalida;

    @Column(name = "fecha_llegada")
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaLlegada;
    
    @Column
    private Long distancia;
   
    @Column
    private boolean estado;

    @ManyToOne 
    @JoinColumn(name="aviones_id")
    private Avion avion;
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado=true;
        fechaSalida = new Date();
        fechaLlegada = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        
    }
}
