package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.services.IAlertaService;
import org.una.aeropuerto.dto.AlertaDTO;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/alertas") 
@Api(tags = {"Alertas"})
public class AlertaController {
    
    @Autowired
    private IAlertaService alertaService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene un Alerta por medio del Id", response = AlertaDTO.class, tags = "Alertas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(alertaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByFechaRegistroBetween/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene una lista de Alertas entre la Fecha Especificada", response = AlertaDTO.class, responseContainer = "List", tags = "Alertas")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fechaInicial") Date startDate, @PathVariable(value = "fechaFinal") Date endDate) {
        try {
            return new ResponseEntity(alertaService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de Alertas por medio del Estado", response = AlertaDTO.class, responseContainer = "List", tags = "Alertas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean term) {
        try {
            return new ResponseEntity(alertaService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByVueloId/{id}")
    @ApiOperation(value = "Obtiene una lista de Alertas por Vuelo", response = AlertaDTO.class, responseContainer = "List", tags = "Alertas")
    public ResponseEntity<?> findByVueloId(@PathVariable(value = "id") Long term) {
        try {
            return new ResponseEntity(alertaService.findByVueloId(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear una Alerta", response = AlertaDTO.class, tags = "Alertas")
    public ResponseEntity<?> create(@Valid  @RequestBody AlertaDTO alertaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(alertaService.create(alertaDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar una Alerta a partir de su Id", response = AlertaDTO.class, tags = "Alertas")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AlertaDTO alertaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AlertaDTO> alertaUpdated = alertaService.update(alertaDTO, id);
                if (alertaUpdated.isPresent()) {
                    return new ResponseEntity(alertaUpdated, HttpStatus.OK);
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
