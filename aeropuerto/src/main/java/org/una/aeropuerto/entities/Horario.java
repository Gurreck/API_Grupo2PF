package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.sql.Time;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author adrian
 */

@Entity
@Table(name = "horarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Horario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dia_entrada")
    private Short diaEntrada;
    
    @Column(name = "dia_salida")
    private Short diaSalida;
    
    @Column(name = "hora_entrada")
    private Time horaEntrada;

    @Column(name = "hora_salida")
    private Time horaSalida;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    
    @Column
    private boolean estado;
    
    @ManyToOne 
    @JoinColumn(name="usuarios_id")
    private Usuario usuario;
    
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
