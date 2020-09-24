package org.una.aeropuerto.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByCedulaAndPasswordEncriptado(String cedula, String passwordEncriptado);

    public List<Usuario> findByCedulaContaining(String cedula);
    
    public Optional<Usuario> findByCedula(String cedula);

    public List<Usuario> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);
 
}

