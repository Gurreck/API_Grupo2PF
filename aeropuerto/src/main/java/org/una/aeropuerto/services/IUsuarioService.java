/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.entities.Usuario;

/**
 *
 * @author adrian
 */
public interface IUsuarioService {
    
    public Optional<List<Usuario>> findAll();

    public Optional<Usuario> findById(Long id);
    
    public Optional <Usuario> findByCedula(String cedula);

    public Optional<List<Usuario>> findByCedulaAproximate(String cedula);

    public Optional<List<Usuario>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);
    
    public Optional<List<Usuario>> findByEsJefe(boolean esJefe);
    
    public Usuario create(Usuario usuario);

    public Optional<Usuario> update(Usuario usuario, Long id);

    public String login(AuthenticationRequest authenticationRequest); 
}


