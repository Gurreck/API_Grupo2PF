package org.una.aeropuerto.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.dto.ParametroSistemaDTO;
import org.una.aeropuerto.services.IParametroSistemaService;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    private int expiration;

    @Autowired
    private IParametroSistemaService parametroSistemaService;
     
    public String generateToken(AuthenticationRequest authenticationRequest) {
        ParametroSistemaDTO parametro = parametroSistemaService.findById(Long.parseLong("1")).get();
        expiration = Integer.parseInt(parametro.getValor());
        return Jwts.builder().setSubject(authenticationRequest.getCedula())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isValid(String token) {
      try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        
        } catch (ExpiredJwtException | MalformedJwtException | io.jsonwebtoken.SignatureException | UnsupportedJwtException | IllegalArgumentException ex) {
            return false;
        }
        
    }

}

