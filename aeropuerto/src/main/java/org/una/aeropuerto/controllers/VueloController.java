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
import org.una.aeropuerto.dto.VueloDTO;
import org.una.aeropuerto.services.IVueloService;
import javax.validation.Valid;

@RestController
@RequestMapping("/vuelos") 
@Api(tags = {"Vuelos"})
public class VueloController {

    @Autowired
    private IVueloService vueloService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene el Vuelo por medio del Id", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(vueloService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByAeropuerto/{aeropuerto}")
    @ApiOperation(value = "Obtiene una lista de Vuelos por medio del Nombre del Aeropuerto", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public ResponseEntity<?> findByAeropuerto(@PathVariable(value = "aeropuerto") String term) {
        try {
            return new ResponseEntity(vueloService.findByAeropuerto(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de Vuelos por medio del estado", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(vueloService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFechaSalidaBetween/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene una lista de Vuelos entre la Fecha de Salida Especificada", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public ResponseEntity<?> findByFechaSalidaBetween(@PathVariable(value = "fechaInicial") Date startDate, @PathVariable(value = "fechaFinal") Date endDate) {
        try {
            return new ResponseEntity(vueloService.findByFechaSalidaBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByFechaLlegadaBetween/{fechaInicial}/{fechaFinal}")
    @ApiOperation(value = "Obtiene una lista de Vuelos entre la Fecha de Llegada Especificada", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public ResponseEntity<?> findByFechaLlegadaBetween(@PathVariable(value = "fechaInicial") Date startDate, @PathVariable(value = "fechaFinal") Date endDate) {
        try {
            return new ResponseEntity(vueloService.findByFechaLlegadaBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByAvionId/{id}")
    @ApiOperation(value = "Obtiene una lista de Vuelos por medio del Avion", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public ResponseEntity<?> findByAerolineaId(@PathVariable(value = "id") Long term) {
        try {
            return new ResponseEntity(vueloService.findByAvionId(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Vuelo", response = VueloDTO.class, tags = "Vuelos")
    public ResponseEntity<?> create(@Valid @RequestBody VueloDTO vueloDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity<>(vueloService.create(vueloDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Vuelo a partir de su Id", response = VueloDTO.class, tags = "Vuelos")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody VueloDTO vueloDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<VueloDTO> vueloUpdated = vueloService.update(vueloDTO, id);
                if (vueloUpdated.isPresent()) {
                    return new ResponseEntity<>(vueloUpdated, HttpStatus.OK);

                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

                }
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
}

