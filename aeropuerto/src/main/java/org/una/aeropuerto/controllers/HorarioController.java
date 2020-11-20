package org.una.aeropuerto.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.una.aeropuerto.dto.HorarioDTO;
import org.una.aeropuerto.services.IHorarioService;
import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/horarios")
@Api(tags = {"Horarios"})
public class HorarioController {

    @Autowired
    private IHorarioService horarioService;
   
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene un Horario por su Id", response = HorarioDTO.class, tags = "Horarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(horarioService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de Horarios por estado", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(horarioService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByFechaRegistroBetween/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene los Horarios entre las fechas de registro especificadas", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
    @PathVariable(value = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            return new ResponseEntity(horarioService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     
    @GetMapping("/findByUsuarioId/{id}")
    @ApiOperation(value = "Obtiene una lista de Horarios por medio del id del Usuario", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(horarioService.findByUsuarioId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByEstadoAndUsuarioId/{estado}/{id}")
    @ApiOperation(value = "Obtiene una lista de Horarios por medio del id del Usuario", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByEstadoAndUsuarioId(@PathVariable(value = "estado") boolean estado, @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(horarioService.findByEstadoAndUsuarioId(estado,id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Horario", response = HorarioDTO.class, tags = "Horarios")
    public ResponseEntity<?> create(@Valid @RequestBody HorarioDTO horarioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(horarioService.create(horarioDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Horario a partir de su Id", response = HorarioDTO.class, tags = "Horarios")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody HorarioDTO horarioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<HorarioDTO> horarioUpdated = horarioService.update(horarioDTO, id);
                if (horarioUpdated.isPresent()) {
                    return new ResponseEntity(horarioUpdated, HttpStatus.OK);
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
