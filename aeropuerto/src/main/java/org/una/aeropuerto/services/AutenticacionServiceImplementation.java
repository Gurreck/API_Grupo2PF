package org.una.aeropuerto.services;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.dto.AuthenticationResponse;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.jwt.JwtProvider;

@Service
public class AutenticacionServiceImplementation implements IAutenticacionService {

    @Autowired
    private IUsuarioService usuarioService;
       
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        Optional<UsuarioDTO> usuario = usuarioService.findByCedula(authenticationRequest.getCedula());
       
        
        if (usuario.isPresent()) {
            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
            authenticationResponse.setUsuario(usuario.get());
            authenticationResponse.setRoles(usuarioService.findRolByCedula(authenticationRequest.getCedula()));

            return authenticationResponse;
        } else {
            return null;
        }
    }
}