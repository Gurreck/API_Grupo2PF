 package org.una.aeropuerto.controllers;

 import io.swagger.annotations.Api;
 import io.swagger.annotations.ApiOperation;
 import java.util.Date;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.validation.BindingResult;
 import org.springframework.web.bind.annotation.*;
 import org.una.aeropuerto.dto.RolDTO;
 import org.una.aeropuerto.services.IRolService;
 import javax.validation.Valid;
 import java.util.Optional;
 import org.springframework.format.annotation.DateTimeFormat;

 @RestController
@RequestMapping("/roles")
@Api(tags = {"Roles"})
public class RolController {

    @Autowired
    private IRolService rolService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findAll")
    @ApiOperation(value = "Obtiene una lista de todos los Roles", response = RolDTO.class, responseContainer = "List", tags = "Roles")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(rolService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene un Rol por su Id", response = RolDTO.class, tags = "Roles")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(rolService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNombre/{nombre}")
    @ApiOperation(value = "Obtiene una lista de Roles por su nombre", response = RolDTO.class, responseContainer = "List", tags = "Roles")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value = "nombre") String nombre) {
        try {
            return new ResponseEntity(rolService.findByNombreAproximateIgnoreCase(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de Roles por estado", response = RolDTO.class, responseContainer = "List", tags = "Roles")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(rolService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByFechaRegistroBetween/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene una lista de roles entre las fechas de registro especificadas", response = RolDTO.class, responseContainer = "List", tags = "Roles")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
    @PathVariable(value = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            return new ResponseEntity(rolService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/countByEstado/{estado}")
    @ApiOperation(value = "Realiza un conteo de Roles por medio del estado", response = RolDTO.class, responseContainer = "List", tags = "Roles")
    public ResponseEntity<?> countByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(rolService.countByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Rol", response = RolDTO.class, tags = "Roles")
    public ResponseEntity<?> create(@Valid @RequestBody RolDTO rolDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(rolService.create(rolDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Rol a partir de su Id", response = RolDTO.class, tags = "Roles")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody RolDTO rolDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<RolDTO> rolUpdated = rolService.update(rolDTO, id);
                if (rolUpdated.isPresent()) {
                    return new ResponseEntity(rolUpdated, HttpStatus.OK);
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
