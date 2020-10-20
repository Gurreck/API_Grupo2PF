package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.una.aeropuerto.dto.HoraMarcajeDTO;
import org.una.aeropuerto.services.IHoraMarcajeService;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/horaMarcaje")
@Api(tags = {"Hora Marcaje"})
public class HoraMarcajeController {

    @Autowired
    private IHoraMarcajeService horaMarcajeService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";


    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene la Hora de marcaje por medio del Id", response = HoraMarcajeDTO.class, tags = "Hora Marcaje")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(horaMarcajeService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
      
    @GetMapping("/findByFechaRegistro/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene la hora de marcaje entre las fechas de registro especificadas", response = HoraMarcajeDTO.class, responseContainer = "List", tags = "Hora Marcaje")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fechaInicial") Date startDate, @PathVariable(value = "fechaFinal") Date endDate) {
        try {
            return new ResponseEntity(horaMarcajeService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUsuarioId/{usuarioId}")
    @ApiOperation(value = "Obtiene una lista de horarios por medio del id del usuario", response = HoraMarcajeDTO.class, responseContainer = "List", tags = "Hora Marcaje")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "usuarioId") Long id) {
        try {
            return new ResponseEntity(horaMarcajeService.findByUsuarioId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear una Hora de marcaje", response = HoraMarcajeDTO.class, tags = "Hora Marcaje")
    public ResponseEntity<?> create(@Valid @RequestBody HoraMarcajeDTO horaMarcajeDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(horaMarcajeService.create(horaMarcajeDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar una Hora de marcaje a partir de su Id", response = HoraMarcajeDTO.class, tags = "Hora Marcaje")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody HoraMarcajeDTO horaMarcajeDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<HoraMarcajeDTO> horaMarcajeUpdated = horaMarcajeService.update(horaMarcajeDTO, id);
                if (horaMarcajeUpdated.isPresent()) {
                    return new ResponseEntity(horaMarcajeUpdated, HttpStatus.OK);
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
