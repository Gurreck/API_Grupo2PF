package org.una.aeropuerto.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.una.aeropuerto.dto.AreaTrabajoAvionDTO;
import org.una.aeropuerto.services.IAreaTrabajoAvionService;
import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/areasTrabajoAviones")
@Api(tags = {"Areas Trabajo Aviones"})
public class AreaTrabajoAvionController {

    @Autowired
    private IAreaTrabajoAvionService areaTrabajoAvionService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene una Area de Trabajo con Avion por su Id", response = AreaTrabajoAvionDTO.class, tags = "Areas Trabajo Aviones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(areaTrabajoAvionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFechaRegistroBetween/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene una lista de Area de Trabajo con Avion entre las fechas de registro especificadas", response = AreaTrabajoAvionDTO.class, responseContainer = "List", tags = "Areas Trabajo Aviones")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fechaInicial") Date fechaInicio, @PathVariable(value = "fechaFinal") Date fechaFinal) {
        try {
            return new ResponseEntity(areaTrabajoAvionService.findByFechaRegistroBetween(fechaInicio, fechaFinal), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear una Area de Trabajo con Avion", response = AreaTrabajoAvionDTO.class, tags = "Areas Trabajo Aviones")
    public ResponseEntity<?> create(@Valid @RequestBody AreaTrabajoAvionDTO areaTrabajoAvionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(areaTrabajoAvionService.create(areaTrabajoAvionDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar una Area de Trabajo con Avion", response = AreaTrabajoAvionDTO.class, tags = "Areas Trabajo Aviones")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AreaTrabajoAvionDTO areaTrabajoAvionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AreaTrabajoAvionDTO> precioUpdated = areaTrabajoAvionService.update(areaTrabajoAvionDTO, id);
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