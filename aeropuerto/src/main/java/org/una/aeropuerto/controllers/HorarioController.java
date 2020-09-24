package org.una.aeropuerto.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.HorarioDTO;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.dto.TransaccionDTO;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.entities.Horario;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.entities.Transaccion;
import org.una.aeropuerto.services.IHorarioService;
import org.una.aeropuerto.utils.MapperUtils;

@RestController
@RequestMapping("/horarios")
@Api(tags = {"Horarios"})
public class HorarioController {

    @Autowired
    private IHorarioService horarioService;
   
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los horarios", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Horario>> result = horarioService.findAll();
            if (result.isPresent()) {
                List<HorarioDTO> horarioDTO = MapperUtils.DtoListFromEntityList(result.get(), HorarioDTO.class);
                return new ResponseEntity<>(horarioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene el horario por medio del Id", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Horario> horarioFound = horarioService.findById(id);
            if (horarioFound.isPresent()) {
                HorarioDTO horarioDto = MapperUtils.DtoFromEntity(horarioFound.get(), HorarioDTO.class);
                return new ResponseEntity<>(horarioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     @GetMapping("/{Estado}") 
    @ApiOperation(value = "Obtiene una lista con los horarios por medio del estado", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Horario>> horarioFound = horarioService.findByEstado(estado);
            if (horarioFound.isPresent()) {
                HorarioDTO horarioDTO = MapperUtils.DtoFromEntity(horarioFound.get(), HorarioDTO.class);
                return new ResponseEntity<>(horarioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{Dia_Entrada}") 
    @ApiOperation(value = "Obtiene una lista con los horarios por medio del dia de entrada", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByDiaEntrada(@PathVariable(value = "dia_entrada") Time diaEntrada) {
        try {
            Optional<List<Horario>> horarioFound = horarioService.findByDiaEntrada(diaEntrada);
            if (horarioFound.isPresent()) {
                HorarioDTO horarioDTO = MapperUtils.DtoFromEntity(horarioFound.get(), HorarioDTO.class);
                return new ResponseEntity<>(horarioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @GetMapping("/{Dia_Salida}") 
    @ApiOperation(value = "Obtiene una lista con los horarios por medio del dia de salida", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> findByDiaSalida(@PathVariable(value = "dia_salida") Time diaSalida) {
        try {
            Optional<List<Horario>> horarioFound = horarioService.findByDiaSalida(diaSalida);
            if (horarioFound.isPresent()) {
                HorarioDTO horarioDTO = MapperUtils.DtoFromEntity(horarioFound.get(), HorarioDTO.class);
                return new ResponseEntity<>(horarioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ResponseBody
   @ApiOperation(value = "Permite crear un horario", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> create(@RequestBody Horario horario) {
        try {
            Horario horarioCreated = horarioService.create(horario);
            HorarioDTO horarioDto = MapperUtils.DtoFromEntity(horarioCreated, HorarioDTO.class);
            return new ResponseEntity<>(horarioDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite actualizar un horario", response = HorarioDTO.class, responseContainer = "List", tags = "Horarios")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Horario horarioModified) {
        try {
            Optional<Horario> horarioUpdated = horarioService.update(horarioModified, id);
            if (horarioUpdated.isPresent()) {
                HorarioDTO horarioDto = MapperUtils.DtoFromEntity(horarioUpdated.get(), HorarioDTO.class);
                return new ResponseEntity<>(horarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
