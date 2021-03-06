package org.una.aeropuerto.entities;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
/**
 *
 * @author acer
 */

@Entity
@Table(name = "areas_trabajo_aviones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AreaTrabajoAvion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @ManyToOne
    @JoinColumn(name="areas_trabajo_id")
    private AreaTrabajo areaTrabajo;

    @ManyToOne
    @JoinColumn(name="aviones_id")
    private Avion avion;

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        fechaRegistro = new Date();

    }

    @PreUpdate
    public void preUpdate() {

    }
}
