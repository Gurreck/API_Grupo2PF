package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.dto.UsuarioDTO;

/**
 *
 * @author adrian
 */
public interface IUsuarioService {
    
    public Optional<List<UsuarioDTO>> findAll();

    public Optional<UsuarioDTO> findById(Long id);
    
    public Optional <UsuarioDTO> findByCedula(String cedula);

    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula);

    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);
    
    public Optional<List<UsuarioDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    public RolDTO findRolByCedula(String cedula);
    
    public Optional<List<UsuarioDTO>> findByUsuarioJefeId(Long id);
    
    public UsuarioDTO create(UsuarioDTO usuario);

    public Optional<UsuarioDTO> update(UsuarioDTO usuario, Long id);

}


