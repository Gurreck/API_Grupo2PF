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
import org.una.aeropuerto.dto.ParametroSistemaDTO;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.dto.TransaccionDTO;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.entities.HoraMarcaje;
import org.una.aeropuerto.entities.Horario;
import org.una.aeropuerto.entities.ParametroSistema;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.entities.Transaccion;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.services.IHoraMarcajeService;
import org.una.aeropuerto.services.IHorarioService;
import org.una.aeropuerto.services.IParametroSistemaService;
import org.una.aeropuerto.utils.MapperUtils;

@RestController
@RequestMapping("/parametroSistema")
@Api(tags = {"Parametros sistema"})
public class ParametroSistemaController {

    @Autowired
    private IParametroSistemaService parametroSistemaService;
    
    
   @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Parametros del sistema", response = ParametroSistemaDTO.class, responseContainer = "List", tags = "Parametros sistema")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ParametroSistema>> result = parametroSistemaService.findAll();
            if (result.isPresent()) {
                List<ParametroSistemaDTO> parametroSistemaDTO = MapperUtils.DtoListFromEntityList(result.get(), ParametroSistemaDTO.class);
                return new ResponseEntity<>(parametroSistemaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Parametro del sistema por medio del Id", response = ParametroSistemaDTO.class, responseContainer = "List", tags = "Parametros sistema")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<ParametroSistema> parametroSistemaFound = parametroSistemaService.findById(id);
            if (parametroSistemaFound.isPresent()) {
                ParametroSistemaDTO parametroSistemaDto = MapperUtils.DtoFromEntity(parametroSistemaFound.get(), ParametroSistemaDTO.class);
                return new ResponseEntity<>(parametroSistemaDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{Estado}") 
    @ApiOperation(value = "Obtiene una lista de Parametros del sistema por medio del estado", response = ParametroSistemaDTO.class, responseContainer = "List", tags = "Parametros sistema")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<ParametroSistema>> parametroSistemaFound = parametroSistemaService.findByEstado(estado);
            if (parametroSistemaFound.isPresent()) {
                ParametroSistemaDTO parametroSistemaDTO = MapperUtils.DtoFromEntity(parametroSistemaFound.get(), ParametroSistemaDTO.class);
                return new ResponseEntity<>(parametroSistemaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{Nombre}") 
    @ApiOperation(value = "Obtiene una lista de Parametros del sistema por medio del nombre", response = ParametroSistemaDTO.class, responseContainer = "List", tags = "Parametros sistema")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "nombre") String nombre) {
        try {
            Optional<List<ParametroSistema>> parametroSistemaFound = parametroSistemaService.findByNombre(nombre);
            if (parametroSistemaFound.isPresent()) {
                ParametroSistemaDTO parametroSistemaDTO = MapperUtils.DtoFromEntity(parametroSistemaFound.get(), ParametroSistemaDTO.class);
                return new ResponseEntity<>(parametroSistemaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{FechaRegistro}")
    @ApiOperation(value = "Obtiene una lista de Parametros del sistema por medio de la fecha de registro", response = ParametroSistemaDTO.class, responseContainer = "List", tags = "Parametros sistema")
    public ResponseEntity<?> findByFechaRegistro(@PathVariable(value = "fecha_Registro") Date fecha_Registro) {
        try {
             Optional<List<ParametroSistema>> parametroSistemaFound = parametroSistemaService.findByFechaRegistro(fecha_Registro);
            if (parametroSistemaFound.isPresent()) {
                ParametroSistemaDTO parametroSistemaDto = MapperUtils.DtoFromEntity(parametroSistemaFound.get(), ParametroSistemaDTO.class);
                return new ResponseEntity<>(parametroSistemaDto, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear un Parametro del sistema", response = ParametroSistemaDTO.class, responseContainer = "List", tags = "Parametros sistema")
    public ResponseEntity<?> create(@RequestBody ParametroSistema parametroSistema) {
        try {
            ParametroSistema parametroSistemaCreated = parametroSistemaService.create(parametroSistema);
            ParametroSistemaDTO parametroSistemaDto = MapperUtils.DtoFromEntity(parametroSistemaCreated, ParametroSistemaDTO.class);
            return new ResponseEntity<>(parametroSistemaDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar un Parametro del sistema", response = ParametroSistemaDTO.class, responseContainer = "List", tags = "Parametros sistema")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ParametroSistema parametroSistemaModified) {
        try {
            Optional<ParametroSistema> parametroSistemaUpdated = parametroSistemaService.update(parametroSistemaModified, id);
            if (parametroSistemaUpdated.isPresent()) {
                ParametroSistemaDTO parametroSistemaDto = MapperUtils.DtoFromEntity(parametroSistemaUpdated.get(), ParametroSistemaDTO.class);
                return new ResponseEntity<>(parametroSistemaDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
};
