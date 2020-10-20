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

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene el Servicio por medio del Id", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(servicioService.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de Servicios por medio del Estado", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(servicioService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByestadoCobro/{estadoCobro}")
    @ApiOperation(value = "Obtiene una lista de Servicios por medio del Estado del Cobro", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByEstadoCobro(@PathVariable(value = "estadoCobro") boolean estadoCobro) {
        try {
            return new ResponseEntity(servicioService.findByEstadoCobro(estadoCobro), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFechaRegistroBetween/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene una lista de Servicios entre la Fecha Especificada", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fechaInicial") Date startDate, @PathVariable(value = "fechaFinal") Date endDate) {
        try {
            return new ResponseEntity(servicioService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByAvionId/{id}")
    @ApiOperation(value = "Obtiene una lista de Servicios por Avion", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByAvionId(@PathVariable(value = "id") Long term) {
        try {
            return new ResponseEntity(servicioService.findByAvionId(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByTipoServicioId/{id}")
    @ApiOperation(value = "Obtiene una lista de Servicios por Tipo de Servicio", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByTipoServicioId(@PathVariable(value = "id") Long term) {
        try {
            return new ResponseEntity(servicioService.findByTipoServicioId(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByTipoServicioIdAndAvionId/{TipoServicioId}/{AvionId}")
    @ApiOperation(value = "Obtiene una lista de Servicios por Tipo de Servicio y Avion", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByTipoServicioIdAndAvionId(@PathVariable(value = "TipoServicioId") Long term, @PathVariable(value = "AvionId") Long term2) {
        try {
            return new ResponseEntity(servicioService.findByTipoServicioIdAndAvionId(term, term2), HttpStatus.OK);
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

