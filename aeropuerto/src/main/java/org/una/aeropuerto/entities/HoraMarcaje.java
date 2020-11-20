package org.una.aeropuerto.entities;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author adrian
 */

@Entity
@Table(name = "horas_marcaje")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class HoraMarcaje implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "hora_entrada")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date horaEntrada;
    
    @Column(name = "hora_salida")
    @Temporal(TemporalType.TIMESTAMP) 
    private Date horaSalida;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    
    
    @ManyToOne 
    @JoinColumn(name="usuarios_id")
    private Usuario usuario;
    
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        fechaRegistro = new Date();
        fechaModificacion = new Date();
        horaEntrada = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
        horaSalida = new Date();
    }
}

