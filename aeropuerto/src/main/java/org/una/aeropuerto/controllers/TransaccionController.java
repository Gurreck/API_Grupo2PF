package org.una.aeropuerto.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.dto.TransaccionDTO;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.entities.Transaccion;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.services.IRolService;
import org.una.aeropuerto.services.ITransaccionService;
import org.una.aeropuerto.services.IUsuarioService;
import org.una.aeropuerto.utils.MapperUtils;

@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionController {
    
    @Autowired
    private ITransaccionService transaccionService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todas las Transacciones", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Transaccion>> result = transaccionService.findAll();
            if (result.isPresent()) {
                List<TransaccionDTO> rolesDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionDTO.class);
                return new ResponseEntity<>(rolesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una Transaccion por medio del Id", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Transaccion> transaccionFound = transaccionService.findById(id);
            if (transaccionFound.isPresent()) {
                TransaccionDTO transaccionDTO = MapperUtils.DtoFromEntity(transaccionFound.get(), TransaccionDTO.class);
                return new ResponseEntity<>(transaccionDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{Estado}") 
    @ApiOperation(value = "Obtiene una lista con las Transacciones por medio del estado", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Transaccion>> transaccionFound = transaccionService.findByEstado(estado);
            if (transaccionFound.isPresent()) {
                TransaccionDTO transaccionDTO = MapperUtils.DtoFromEntity(transaccionFound.get(), TransaccionDTO.class);
                return new ResponseEntity<>(transaccionDTO, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear una Transaccion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> create(@RequestBody Transaccion transaccion) {
        try {
            Transaccion transaccionCreated = transaccionService.create(transaccion);
            TransaccionDTO transaccionDto = MapperUtils.DtoFromEntity(transaccionCreated, TransaccionDTO.class);
            return new ResponseEntity<>(transaccionDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite modificar una Transaccion", response = UsuarioDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Transaccion transaccionModified) {
        try {
            Optional<Transaccion> transaccionUpdated = transaccionService.update(transaccionModified, id);
            if (transaccionUpdated.isPresent()) {
                TransaccionDTO transaccionDto = MapperUtils.DtoFromEntity(transaccionUpdated.get(), TransaccionDTO.class);
                return new ResponseEntity<>(transaccionDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
