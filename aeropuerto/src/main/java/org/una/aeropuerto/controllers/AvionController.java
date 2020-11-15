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
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/aviones") 
@Api(tags = {"Aviones"})
public class AvionController {

    @Autowired
    private IAvionService avionService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findAll")
    @ApiOperation(value = "Obtiene una lista de todos los Aviones", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(avionService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene un Avion por medio del Id", response = AvionDTO.class, tags = "Aviones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(avionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByMatricula/{matricula}")
    @ApiOperation(value = "Obtiene un Avion por medio de la Matricula", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByMatriculaAproximate(@PathVariable(value = "matricula") String term) {
        try {
            return new ResponseEntity(avionService.findByMatriculaAproximate(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByTipoAvion/{tipoAvion}")
    @ApiOperation(value = "Obtiene una lista de Aviones por medio del Tipo de Avion", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByTipoAvion(@PathVariable(value = "tipoAvion") String term) {
        try {
            return new ResponseEntity(avionService.findByTipoAvion(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByFechaRegistroBetween/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene una lista de Aviones entre la Fecha Especificada", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
    @PathVariable(value = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            return new ResponseEntity(avionService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de Aviones por medio del Estado", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean term) {
        try {
            return new ResponseEntity(avionService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByAerolinea/{id}")
    @ApiOperation(value = "Obtiene una lista de Aviones por medio de la Aerolinea", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByAerolineaId(@PathVariable(value = "id") Long term) {
        try {
            return new ResponseEntity(avionService.findByAerolineaId(term), HttpStatus.OK);
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
