package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
