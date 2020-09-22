package org.una.aeropuerto.controllers;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.services.IRolService;
import org.una.aeropuerto.utils.MapperUtils;

@RestController
@RequestMapping("/roles") 
public class RolController {

    @Autowired
    private IRolService rolService;
    
    
    @GetMapping() 
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Rol>> result = rolService.findAll();
            if (result.isPresent()) {
                List<RolDTO> rolesDTO = MapperUtils.DtoListFromEntityList(result.get(), RolDTO.class);
                return new ResponseEntity<>(rolesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Rol> rolFound = rolService.findById(id);
            if (rolFound.isPresent()) {
                RolDTO rolesDTO = MapperUtils.DtoFromEntity(rolFound.get(), RolDTO.class);
                return new ResponseEntity<>(rolesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{Tipo}") 
    public ResponseEntity<?> findByTipo(@PathVariable(value = "tipo") String tipo) {
        try {
            Optional<List<Rol>> rolFound = rolService.findByTipo(tipo);
            if (rolFound.isPresent()) {
                RolDTO rolesDTO = MapperUtils.DtoFromEntity(rolFound.get(), RolDTO.class);
                return new ResponseEntity<>(rolesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{Estado}") 
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Rol>> rolFound = rolService.findByEstado(estado);
            if (rolFound.isPresent()) {
                RolDTO rolesDTO = MapperUtils.DtoFromEntity(rolFound.get(), RolDTO.class);
                return new ResponseEntity<>(rolesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Rol rol) {
        try {
            Rol rolCreated = rolService.create(rol);
            RolDTO rolDto = MapperUtils.DtoFromEntity(rolCreated, RolDTO.class);
            return new ResponseEntity<>(rolDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Rol rolModified) {
        try {
            Optional<Rol> rolUpdated = rolService.update(rolModified, id);
            if (rolUpdated.isPresent()) {
                RolDTO rolDto = MapperUtils.DtoFromEntity(rolUpdated.get(), RolDTO.class);
                return new ResponseEntity<>(rolDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
