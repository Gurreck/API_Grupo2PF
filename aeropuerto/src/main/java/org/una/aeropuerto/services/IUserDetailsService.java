/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author acer
 */
public interface IUserDetailsService {
    
    public UserDetails loadUserByUsername(String username);
    
}
