package org.una.aeropuerto.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Esteban Vargas
 */
@Entity
@Table(name = "precios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Precio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private Float monto;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @Column
    private boolean estado;

    @ManyToOne 
    @JoinColumn(name="tiposServicio_id")
    private TipoServicio tipoServicio;
    
    private static final long serialVersionUID = 1L;
    
    @PrePersist
    public void prePersist() {
        estado = true;
        fechaRegistro = new Date();
    }

    @PreUpdate
    public void preUpdate() {
    }
}
