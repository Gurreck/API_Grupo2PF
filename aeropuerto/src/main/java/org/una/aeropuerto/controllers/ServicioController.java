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
import org.una.aeropuerto.dto.ServicioDTO;
import org.una.aeropuerto.services.IServicioService;
import javax.validation.Valid;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/servicios") 
@Api(tags = {"Servicios"})
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene el Servicio por medio del Id", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(servicioService.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{Estado}")
    @ApiOperation(value = "Obtiene una lista de Servicios por medio del estado", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(servicioService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{estadoCobro}")
    @ApiOperation(value = "Obtiene una lista de Servicios por medio del estado del cobro", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByEstadoCobro(@PathVariable(value = "estado") boolean estadoCobro) {
        try {
            return new ResponseEntity(servicioService.findByEstadoCobro(estadoCobro), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{fecha}")
    @ApiOperation(value = "Obtiene una lista de Servicios entre la fecha especificada", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            return new ResponseEntity(servicioService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/avion/{term}")
    @ApiOperation(value = "Obtiene una lista de Servicios por Avion", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByAvionId(@PathVariable(value = "term") long term) {
        try {
            return new ResponseEntity(servicioService.findByAvionId(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tiposervicio/{term}")
    @ApiOperation(value = "Obtiene una lista de Servicios por tipo de Servicio", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByTipoServicioId(@PathVariable(value = "term") long term) {
        try {
            return new ResponseEntity(servicioService.findByTipoServicioId(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Servicio", response = ServicioDTO.class, tags = "Servicios")
    public ResponseEntity<?> create(@Valid @RequestBody ServicioDTO servicioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            {
                try {
                    return new ResponseEntity(servicioService.create(servicioDTO), HttpStatus.CREATED);
                } catch (Exception e) {
                    return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Servicio a partir de su Id", response = ServicioDTO.class, tags = "Servicios")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ServicioDTO servicioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ServicioDTO> servicioUpdated = servicioService.update(servicioDTO, id);
                if (servicioUpdated.isPresent()) {
                    return new ResponseEntity(servicioUpdated, HttpStatus.OK);
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

