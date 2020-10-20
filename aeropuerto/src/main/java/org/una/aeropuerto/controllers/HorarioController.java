package org.una.aeropuerto.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.sql.Time;
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
import org.una.aeropuerto.dto.HorarioDTO;
import org.una.aeropuerto.services.IHorarioService;

@RestController
@RequestMapping("/horarios")
@Api(tags = {"Horarios"})
public class HorarioController {

    @Autowired
    private IHorarioService horarioService;
   
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un horario por su Id", response = HorarioDTO.class, tags = "Horarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(horarioService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado/{termino}")
    @ApiOperation(value = "Obtiene una lista de horarios por estado", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "termino") boolean term) {
        try {
            return new ResponseEntity(horarioService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/diaEntrada/{termino}")
    @ApiOperation(value = "Obtiene una lista de horarios por medio del dia de entrada", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByDiaEntrada(@PathVariable(value = "termino") String term) {
        try {
            return new ResponseEntity(horarioService.findByDiaEntrada(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @GetMapping("/diaSalida/{termino}")
    @ApiOperation(value = "Obtiene una lista de horarios por medio del dia de salida", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByDiaSalida(@PathVariable(value = "termino") String term) {
        try {
            return new ResponseEntity(horarioService.findByDiaSalida(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un horario", response = HorarioDTO.class, tags = "Horarios")
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
    @ApiOperation(value = "Permite modificar un horario a partir de su Id", response = HorarioDTO.class, tags = "Horarios")
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
