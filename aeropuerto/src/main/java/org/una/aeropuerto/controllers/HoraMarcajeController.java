package org.una.aeropuerto.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.sql.Time;
import java.util.Date;
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
import org.una.aeropuerto.dto.HoraMarcajeDTO;
import org.una.aeropuerto.dto.HorarioDTO;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.dto.TransaccionDTO;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.entities.HoraMarcaje;
import org.una.aeropuerto.entities.Horario;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.entities.Transaccion;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.services.IHoraMarcajeService;
import org.una.aeropuerto.services.IHorarioService;
import org.una.aeropuerto.utils.MapperUtils;

@RestController
@RequestMapping("/horaMarcaje")
@Api(tags = {"Hora Marcaje"})
public class HoraMarcajeController {

    @Autowired
    private IHoraMarcajeService horaMarcajeService;
   
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las Horas de marcaje", response = HoraMarcajeDTO.class, responseContainer = "List", tags = "Hora Marcaje")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<HoraMarcaje>> result = horaMarcajeService.findAll();
            if (result.isPresent()) {
                List<HoraMarcajeDTO> horaMarcajeDTO = MapperUtils.DtoListFromEntityList(result.get(), HoraMarcajeDTO.class);
                return new ResponseEntity<>(horaMarcajeDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene la Hora de marcaje por medio del Id", response = HoraMarcajeDTO.class, responseContainer = "List", tags = "Hora Marcaje")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<HoraMarcaje> horaMarcajeFound = horaMarcajeService.findById(id);
            if (horaMarcajeFound.isPresent()) {
                HoraMarcajeDTO horaMarcajeDto = MapperUtils.DtoFromEntity(horaMarcajeFound.get(), HoraMarcajeDTO.class);
                return new ResponseEntity<>(horaMarcajeDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{HoraEntrada}")
    @ApiOperation(value = "Obtiene la Hora de marcaje por medio de la Hora de entrada", response = HoraMarcajeDTO.class, responseContainer = "List", tags = "Hora Marcaje")
    public ResponseEntity<?> findByHoraEntrada(@PathVariable(value = "hora_Entrada") Date horaEntrada) {
        try {
            Optional<List<HoraMarcaje>> horaMarcajeFound = horaMarcajeService.findByHoraEntrada(horaEntrada);
            if (horaMarcajeFound.isPresent()) {
                HoraMarcajeDTO horaMarcajeDto = MapperUtils.DtoFromEntity(horaMarcajeFound.get(), HoraMarcajeDTO.class);
                return new ResponseEntity<>(horaMarcajeDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{HoraSalida}")
    @ApiOperation(value = "Obtiene la Hora de marcaje por medio de la Hora de salida", response = HoraMarcajeDTO.class, responseContainer = "List", tags = "Hora Marcaje")
    public ResponseEntity<?> findByHoraSalida(@PathVariable(value = "hora_Salida") Date horaSalida) {
        try {
            Optional<List<HoraMarcaje>> horaMarcajeFound = horaMarcajeService.findByHoraSalida(horaSalida);
            if (horaMarcajeFound.isPresent()) {
                HoraMarcajeDTO horaMarcajeDto = MapperUtils.DtoFromEntity(horaMarcajeFound.get(), HoraMarcajeDTO.class);
                return new ResponseEntity<>(horaMarcajeDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{FechaRegistro}")
    @ApiOperation(value = "Obtiene la Hora de marcaje por medio de la fecha de registro", response = HoraMarcajeDTO.class, responseContainer = "List", tags = "Hora Marcaje")
    public ResponseEntity<?> findByFechaRegistro(@PathVariable(value = "fecha_Registro") Date fecha_Registro) {
        try {
             Optional<List<HoraMarcaje>> horaMarcajeFound = horaMarcajeService.findByFechaRegistro(fecha_Registro);
            if (horaMarcajeFound.isPresent()) {
                HoraMarcajeDTO horaMarcajeDto = MapperUtils.DtoFromEntity(horaMarcajeFound.get(), HoraMarcajeDTO.class);
                return new ResponseEntity<>(horaMarcajeDto, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear una Hora de marcaje", response = HoraMarcajeDTO.class, tags = "Hora Marcaje")
    public ResponseEntity<?> create(@RequestBody HoraMarcaje horaMarcaje) {
        try {
            HoraMarcaje horaMarcajeCreated = horaMarcajeService.create(horaMarcaje);
            HoraMarcajeDTO horaMarcajeDto = MapperUtils.DtoFromEntity(horaMarcajeCreated, HoraMarcajeDTO.class);
            return new ResponseEntity<>(horaMarcajeDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar una Hora de marcaje a partir de su Id", response = HoraMarcajeDTO.class, tags = "Hora Marcaje")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody HoraMarcaje horaMarcajeModified) {
        try {
            Optional<HoraMarcaje> horaMarcajeUpdated = horaMarcajeService.update(horaMarcajeModified, id);
            if (horaMarcajeUpdated.isPresent()) {
                HoraMarcajeDTO horaMarcajeDto = MapperUtils.DtoFromEntity(horaMarcajeUpdated.get(), HoraMarcajeDTO.class);
                return new ResponseEntity<>(horaMarcajeDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
