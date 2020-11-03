package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.una.aeropuerto.dto.ParametroSistemaDTO;
import org.una.aeropuerto.services.IParametroSistemaService;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/parametroSistema")
@Api(tags = {"Parametros sistema"})
public class ParametroSistemaController {

    @Autowired
    private IParametroSistemaService parametroSistemaService;


    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findAll")
    @ApiOperation(value = "Obtiene una lista de todos los Parametros del Sistema", response = ParametroSistemaDTO.class, responseContainer = "List", tags = "Parametros sistema")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(parametroSistemaService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene un Parametro del Sistema por su Id", response = ParametroSistemaDTO.class, tags = "Parametros sistema")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(parametroSistemaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNombre/{nombre}")
    @ApiOperation(value = "Obtiene una lista de Parametros del Sistema por medio del nombre", response = ParametroSistemaDTO.class, responseContainer = "List", tags = "Parametros sistema")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value = "nombre") String nombre) {
        try {
            return new ResponseEntity(parametroSistemaService.findByNombreAproximateIgnoreCase(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de Parametros del Sistema por su estado", response = ParametroSistemaDTO.class, responseContainer = "List", tags = "Parametros sistema")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(parametroSistemaService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFechaRegistroBetween/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene una lista con los Parametros del Sistema entre las fechas de registro especificadas", response = ParametroSistemaDTO.class, responseContainer = "List", tags = "Parametros sistema")
     public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fechaInicial") Date startDate, @PathVariable(value = "fechaFinal") Date endDate) {
        try {
                return new ResponseEntity<>(parametroSistemaService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Parametro del Sistema", response = ParametroSistemaDTO.class, tags = "Parametros sistema")
    public ResponseEntity<?> create(@Valid @RequestBody ParametroSistemaDTO parametroSistemaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(parametroSistemaService.create(parametroSistemaDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Parametro del Sistema a partir de su Id", response = ParametroSistemaDTO.class, tags = "Parametros sistema")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ParametroSistemaDTO parametroSistemaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ParametroSistemaDTO> parametroSistemaUpdated = parametroSistemaService.update(parametroSistemaDTO, id);
                if (parametroSistemaUpdated.isPresent()) {
                    return new ResponseEntity(parametroSistemaUpdated, HttpStatus.OK);
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
