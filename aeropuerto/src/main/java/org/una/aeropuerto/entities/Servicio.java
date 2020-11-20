package org.una.aeropuerto.entities;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Esteban Vargas
 */
@Entity
@Table(name = "servicios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Servicio implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "estado_cobro")
    private boolean estadoCobro;
    
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
    
    @Column(name = "factura", length = 50)
    private String factura;
    
    @Column(name = "nombre_responsable", length = 50)
    private String nombreResponsable;
    
    @Column(name = "observaciones")
    private String observacion;
    
    @ManyToOne 
    @JoinColumn(name="aviones_id")
    private Avion avion;
    
    @ManyToOne 
    @JoinColumn(name="tiposServicios_id")
    private TipoServicio tipoServicio;
    
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
