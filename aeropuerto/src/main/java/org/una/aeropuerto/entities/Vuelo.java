package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    
    @Column(name = "nombre_aeropuerto", length = 100)
    private String aeropuerto;

    @Column(name = "fecha_salida")
    @Temporal(TemporalType.TIMESTAMP) 
    @Setter(AccessLevel.PUBLIC)
    private Date fechaSalida;

    @Column(name = "fecha_llegada")
    @Temporal(TemporalType.TIMESTAMP) 
    @Setter(AccessLevel.PUBLIC)
    private Date fechaLlegada;
    
    @Column
    private Long distancia;
   
    @Column
    private boolean estado;

    @ManyToOne 
    @JoinColumn(name="aviones_id")
    private Avion avion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vuelo") 
    private List<Alerta> alertas = new ArrayList<>();
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado=false;
    }

    @PreUpdate
    public void preUpdate() {
        
    }
}
