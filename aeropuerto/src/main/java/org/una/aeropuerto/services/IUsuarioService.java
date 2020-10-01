/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

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
    
    //public Optional<List<UsuarioDTO>> findByEsJefe(boolean esJefe);
    
    public RolDTO findRolByCedula(String cedula);
    
    public UsuarioDTO create(UsuarioDTO usuario);

    public Optional<UsuarioDTO> update(UsuarioDTO usuario, Long id);

}


