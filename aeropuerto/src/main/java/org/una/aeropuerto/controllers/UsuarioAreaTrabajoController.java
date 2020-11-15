package org.una.aeropuerto.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.una.aeropuerto.dto.UsuarioAreaTrabajoDTO;
import org.una.aeropuerto.services.IUsuarioAreaTrabajoService;
import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;
import org.springframework.format.annotation.DateTimeFormat;


@RestController
@RequestMapping("/usuariosAreasTrabajo")
@Api(tags = {"Areas Trabajo Usuarios"})
public class UsuarioAreaTrabajoController {

    @Autowired
    private IUsuarioAreaTrabajoService usuarioAreaTrabajoService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene una Area de Trabajo con Usuario por su Id", response = UsuarioAreaTrabajoDTO.class, tags = "Areas Trabajo Usuarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(usuarioAreaTrabajoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFechaRegistroBetween/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene Area de Trabajo con Usuario entre las fechas de registro especificadas", response = UsuarioAreaTrabajoDTO.class, responseContainer = "List", tags = "Areas Trabajo Usuarios")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
    @PathVariable(value = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            return new ResponseEntity(usuarioAreaTrabajoService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByUsuarioId/{id}")
    @ApiOperation(value = "Obtiene un Area de Trabajo con Usuario por medio del id del Usuario", response = UsuarioAreaTrabajoDTO.class, responseContainer = "List", tags = "Areas Trabajo Usuarios")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(usuarioAreaTrabajoService.findByUsuarioId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByAreaTrabajoId/{id}")
    @ApiOperation(value = "Obtiene un Area de Trabajo con Usuario por medio del id del Area de Trabajo", response = UsuarioAreaTrabajoDTO.class, responseContainer = "List", tags = "Areas Trabajo Usuarios")
    public ResponseEntity<?> findByAreaTrabajoId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(usuarioAreaTrabajoService.findByAreaTrabajoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear una Area de Trabajo con Usuario", response = UsuarioAreaTrabajoDTO.class, tags = "Areas Trabajo Usuarios")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioAreaTrabajoDTO usuarioAreaTrabajoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(usuarioAreaTrabajoService.create(usuarioAreaTrabajoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar una Area de Trabajo con Usuario", response = UsuarioAreaTrabajoDTO.class, tags = "Areas Trabajo Usuarios")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioAreaTrabajoDTO usuarioAreaTrabajoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<UsuarioAreaTrabajoDTO> usuarioAreaTrabajoUpdated = usuarioAreaTrabajoService.update(usuarioAreaTrabajoDTO, id);
                if (usuarioAreaTrabajoUpdated.isPresent()) {
                    return new ResponseEntity(usuarioAreaTrabajoUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
}
