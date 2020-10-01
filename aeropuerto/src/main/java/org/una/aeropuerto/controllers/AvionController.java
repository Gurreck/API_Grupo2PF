package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.una.aeropuerto.dto.AvionDTO;
import org.una.aeropuerto.services.IAvionService;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/aviones") 
@Api(tags = {"Aviones"})
public class AvionController {

    @Autowired
    private IAvionService avionService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos los Aviones", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(avionService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Avion por medio del Id", response = AvionDTO.class, tags = "Aviones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(avionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/matricula/{termino}")
    @ApiOperation(value = "Obtiene un Avion por medio de la matricula", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByMatricula(@PathVariable(value = "termino") String term) {
        try {
            return new ResponseEntity(avionService.findByMatricula(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/TipoAvion/{termino}")
    @ApiOperation(value = "Obtiene una lista de Aviones por medio del tipo de avion", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByTipoAvion(@PathVariable(value = "termino") String term) {
        try {
            return new ResponseEntity(avionService.findByTipoAvion(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/FechaRegistro/{termino}")
    @ApiOperation(value = "Obtiene una lista de Aviones por medio de la fecha de registro", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByFechaRegistro(@PathVariable(value = "fechaRegistro") Date term) {
        try {
            return new ResponseEntity(avionService.findByFechaRegistro(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/Estado/{termino}")
    @ApiOperation(value = "Obtiene una lista de Aviones por medio del estado", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean term) {
        try {
            return new ResponseEntity(avionService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Avion", response = AvionDTO.class, tags = "Aviones")
    public ResponseEntity<?> create(@Valid  @RequestBody AvionDTO avionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(avionService.create(avionDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Avion a partir de su Id", response = AvionDTO.class, tags = "Aviones")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AvionDTO avionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AvionDTO> avionUpdated = avionService.update(avionDTO, id);
                if (avionUpdated.isPresent()) {
                    return new ResponseEntity(avionUpdated, HttpStatus.OK);
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
