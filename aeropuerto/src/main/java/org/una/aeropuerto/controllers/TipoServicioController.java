package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.TipoServicioDTO;
import org.una.aeropuerto.services.ITipoServicioService;

import javax.validation.Valid;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/tiposServicios") 
@Api(tags = {"Tipos Servicios"})
public class TipoServicioController {
    
     @Autowired
    private ITipoServicioService tipoServicioService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findAll")
    @ApiOperation(value = "Obtiene una lista de todos los Tipos de Servicios", response = TipoServicioDTO.class, responseContainer = "List", tags = "Tipos Servicios")
    public ResponseEntity<?> findAll() {
        try {
                return new ResponseEntity(tipoServicioService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene  el Tipo de Servicio por medio del Id", response = TipoServicioDTO.class, responseContainer = "List", tags = "Tipos Servicios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(tipoServicioService.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByNombre/{nombre}")
    @ApiOperation(value = "Obtiene una lista con el Tipo de Servicio por medio del nombre", response = TipoServicioDTO.class, responseContainer = "List", tags = "Tipos Servicios")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value = "nombre") String term) {
        try {
                return new ResponseEntity(tipoServicioService.findByNombreAproximateIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Tipo de Servicio", response = TipoServicioDTO.class, tags = "Tipos Servicios")
    public ResponseEntity<?> create(@Valid @RequestBody  TipoServicioDTO tipoServicioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(tipoServicioService.create(tipoServicioDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Tipo de Servicio a partir de su Id", response = TipoServicioDTO.class, tags = "Tipos Servicios")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TipoServicioDTO tipoServicioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
            Optional<TipoServicioDTO> tipoServicioUpdated = tipoServicioService.update(tipoServicioDTO, id);
            if (tipoServicioUpdated.isPresent()) {
                return new ResponseEntity(tipoServicioUpdated, HttpStatus.OK);
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