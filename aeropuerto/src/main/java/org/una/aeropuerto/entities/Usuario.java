package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
 * @author Adrian
 */

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, unique = true)
    private String cedula;

    @Column(name = "nombre_completo", length = 50)
    private String nombreCompleto;

    @Column
    private boolean estado;

    @Column(length = 100, name = "password_encriptado")
    private String passwordEncriptado;

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
           
    @ManyToOne 
    @JoinColumn(name="roles_id")
    private Rol rol;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario") 
    private List<Transaccion> transacciones = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario") 
    private List<HoraMarcaje> horaMarcaje= new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario") 
    private List<Horario> horarios= new ArrayList<>();  
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<UsuarioAreaTrabajo> UsuariosAreasTrabajo = new ArrayList<>();
    
    //Relacion a la misma tabla
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioJefe") 
    private List<Usuario> usuarios = new ArrayList<>();
    
    @ManyToOne 
    @JoinColumn(name="usuarios_id")
    private Usuario usuarioJefe;
    
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
