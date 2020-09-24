package org.una.aeropuerto.entities;

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

    @ManyToOne 
    @JoinColumn(name="tiposServicio_id")
    private TipoServicio tipoServicio;
    
    private static final long serialVersionUID = 1L;
    
    @PrePersist
    public void prePersist() {
        fechaRegistro = new Date();
    }

    @PreUpdate
    public void preUpdate() {
    }
}
