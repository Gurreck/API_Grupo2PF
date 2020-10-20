package org.una.aeropuerto.services;

import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.dto.AuthenticationResponse;

public interface IAutenticacionService {


public AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    


}

