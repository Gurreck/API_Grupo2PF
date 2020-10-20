package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.una.aeropuerto.dto.PrecioDTO;
import org.una.aeropuerto.services.IPrecioService;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/precios")
@Api(tags = {"Precios"})
public class PrecioController {
    
    @Autowired
    private IPrecioService precioService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un precio por su Id", response = PrecioDTO.class, tags = "Precios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(precioService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fecha/{termino1}{termino2}/")
    @ApiOperation(value = "Obtiene una lista de precios entre fechas", response = PrecioDTO.class, responseContainer = "List", tags = "Precios")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "termino1") Date fechaInicio, @PathVariable(value = "termino2") Date fechaFinal) {
        try {
            return new ResponseEntity(precioService.findByFechaRegistroBetween(fechaInicio, fechaFinal), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/tipoServicioId/{id}")
    @ApiOperation(value = "Obtiene una lista de precios por medio del Id del tipo de servicio", response = PrecioDTO.class, responseContainer = "List", tags = "Precios")
    public ResponseEntity<?> findByTipoServicioId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(precioService.findByTipoServicioId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un precio", response = PrecioDTO.class, tags = "Precios")
    public ResponseEntity<?> create(@Valid @RequestBody PrecioDTO precioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(precioService.create(precioDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un precio a partir de su Id", response = PrecioDTO.class, tags = "Precios")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PrecioDTO precioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<PrecioDTO> precioUpdated = precioService.update(precioDTO, id);
                if (precioUpdated.isPresent()) {
                    return new ResponseEntity(precioUpdated, HttpStatus.OK);
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
