package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Esteban Vargas
 */
@Entity
@Table(name = "tipos_servicios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TipoServicio implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column
    private Long duracion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoServicio") 
    private List<Servicio> servicio = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoServicio") 
    private List<Precio> precio = new ArrayList<>();
    
    private static final long serialVersionUID = 1L;
    
}
