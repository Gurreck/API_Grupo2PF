package org.una.aeropuerto.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.Optional;
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
import org.una.aeropuerto.dto.TransaccionDTO;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.services.ITransaccionService;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionController {
    
    @Autowired
    private ITransaccionService transaccionService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findById/{id}") 
    @ApiOperation(value = "Obtiene una Transaccion por medio del Id", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
                return new ResponseEntity<>(transaccionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByEstadoAndTipo/{estado}/{tipo}")
    @ApiOperation(value = "Obtiene una lista con las Transacciones por medio del estado", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByEstadoAndTipo(@PathVariable(value = "estado") boolean estado, @PathVariable(value = "tipo") String tipo) {
        try {
                return new ResponseEntity<>(transaccionService.findByEstadoAndTipo(estado, tipo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByFechaRegistroBetweenAndTipo/{fechaInicial}/{fechaFinal}/{tipo}")
    @ApiOperation(value = "Obtiene una lista con las Transacciones entre las fechas de registro especificadas", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
     public ResponseEntity<?> findByFechaRegistroBetweenAndTipo(@PathVariable(value = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
     @PathVariable(value = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, @PathVariable(value = "tipo") String tipo) {
        try {
                return new ResponseEntity<>(transaccionService.findByFechaRegistroBetweenAndTipo(startDate, endDate, tipo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     
    @GetMapping("/findByFechaRegistroBetweenAndTipoAndUsuarioUsuarioJefeId/{fechaInicial}/{fechaFinal}/{tipo}/{idJefe}")
    @ApiOperation(value = "Obtiene una lista con las Transacciones entre las fechas de registro especificadas", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
     public ResponseEntity<?> findByFechaRegistroBetweenAndTipoAndUsuarioUsuarioJefeId(@PathVariable(value = "fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
     @PathVariable(value = "fechaFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, @PathVariable(value = "tipo") String tipo, @PathVariable(value = "idJefe") Long idJefe) {
        try {
                return new ResponseEntity<>(transaccionService.findByFechaRegistroBetweenAndTipoAndUsuarioUsuarioJefeId(startDate, endDate, tipo, idJefe), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     
    @GetMapping("/findByUsuarioIdAndTipo/{id}/{tipo}")
    @ApiOperation(value = "Obtiene una lista de transacciones por medio del id del usuario", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByUsuarioIdAndTipo(@PathVariable(value = "id") Long id, @PathVariable(value = "tipo") String tipo) {
        try {
            return new ResponseEntity(transaccionService.findByUsuarioIdAndTipo(id, tipo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByUsuarioIdAndTipoAndUsuarioUsuarioJefeId/{id}/{tipo}/{idJefe}")
    @ApiOperation(value = "Obtiene una lista de transacciones por medio del id del usuario", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByUsuarioIdAndTipoAndUsuarioUsuarioJefeId(@PathVariable(value = "id") Long id, @PathVariable(value = "tipo") String tipo, @PathVariable(value = "idJefe") Long idJefe) {
        try {
            return new ResponseEntity(transaccionService.findByUsuarioIdAndTipoAndUsuarioUsuarioJefeId(id, tipo, idJefe), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear una Transaccion", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> create(@Valid @RequestBody TransaccionDTO transaccionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(transaccionService.create(transaccionDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar una Transaccion", response = UsuarioDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id,@Valid @RequestBody TransaccionDTO transaccionDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
            Optional<TransaccionDTO> transaccionUpdated = transaccionService.update(transaccionDTO, id);
            if (transaccionUpdated.isPresent()) {
                return new ResponseEntity(transaccionUpdated, HttpStatus.OK);

            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }else{
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }

    }
}
