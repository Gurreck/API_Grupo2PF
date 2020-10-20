package org.una.aeropuerto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.repositories.IUsuarioRepository;
import org.una.aeropuerto.utils.MapperUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.entities.Rol;


@Service
public class UsuarioServiceImplementation implements IUsuarioService, UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    private UsuarioDTO encriptarPassword(UsuarioDTO usuario) {
        String password = usuario.getPasswordEncriptado();
        if (!password.isBlank()) {
            usuario.setPasswordEncriptado(bCrypt.encode(password));
        }
        return usuario;
    }

    private Optional<List<UsuarioDTO>> findList(List<Usuario> list) {
        if (list != null) {
            List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, UsuarioDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<UsuarioDTO>> findList(Optional<List<Usuario>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<UsuarioDTO> oneToDto(Optional<Usuario> one) {
        if (one.isPresent()) {
            UsuarioDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(), UsuarioDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findAll() {
        return findList(usuarioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {
        return oneToDto(usuarioRepository.findById(id));

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula) {
        return findList(usuarioRepository.findByCedulaContaining(cedula));

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return findList(usuarioRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto));

    }

    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByCedula(String cedula) {
        return oneToDto(usuarioRepository.findByCedula(cedula));
    }

    @Override
    @Transactional(readOnly = true)
    public RolDTO findRolByCedula(String cedula) {
        Optional<Usuario> result = usuarioRepository.findByCedula(cedula);
        if (result != null) {
            Rol rol = result.get().getRol();
            RolDTO rolDTO = MapperUtils.DtoFromEntity(rol, RolDTO.class);
            return rolDTO;
        } else {
            return null;
        }
    }
    
    @Override
    public Optional<List<UsuarioDTO>> findByUsuarioJefeId(Long id) {
           return findList(usuarioRepository.findByUsuarioJefeId(id));
    }
    
   @Override
    public Optional<List<UsuarioDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
       return findList(usuarioRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    
    @Override
    @Transactional
    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        usuarioDTO = encriptarPassword(usuarioDTO);
        Usuario usuario = MapperUtils.EntityFromDto(usuarioDTO, Usuario.class);
        usuario = usuarioRepository.save(usuario);
        return MapperUtils.DtoFromEntity(usuario, UsuarioDTO.class);
    }

    @Override
    @Transactional
    public Optional<UsuarioDTO> update(UsuarioDTO usuarioDTO, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            usuarioDTO = encriptarPassword(usuarioDTO);
            Usuario usuario = MapperUtils.EntityFromDto(usuarioDTO, Usuario.class);
            usuario = usuarioRepository.save(usuario);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(usuario, UsuarioDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByCedula(username);
        if (usuarioBuscado.isPresent()) {
            Usuario usuario = usuarioBuscado.get();
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ADMIN"));
            UserDetails userDetails = new User(usuario.getCedula(), usuario.getPasswordEncriptado(), roles);
            return userDetails;
        } else {
            return null;
        }

    }


}

