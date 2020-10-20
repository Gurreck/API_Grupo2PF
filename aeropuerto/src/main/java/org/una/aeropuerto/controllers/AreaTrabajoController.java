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
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.AreaTrabajoDTO;
import org.una.aeropuerto.services.IAreaTrabajoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/areasTrabajo") 
@Api(tags = {"Areas Trabajo"})
public class AreaTrabajoController {

    @Autowired
    private IAreaTrabajoService areaTrabajoService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findAll")
    @ApiOperation(value = "Obtiene una lista de todas las Areas de Trabajo", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas Trabajo")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(areaTrabajoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene el Area de Trabajo por medio del Id", response = AreaTrabajoDTO.class, tags = "Areas Trabajo")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(areaTrabajoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNombreArea/{nombre}")
    @ApiOperation(value = "Obtiene el Area de Trabajo por medio del nombre del Area", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas Trabajo")
    public ResponseEntity<?> findByNombreArea(@PathVariable(value = "nombre") String term) {
         try {
             return new ResponseEntity(areaTrabajoService.findByNombreArea(term), HttpStatus.OK);
         } catch (Exception e) {
             return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
    

    @GetMapping("/findByNombreResponsable/{nombre}")
    @ApiOperation(value = "Obtiene una lista con el Area de Trabajo por medio del nombre del Responsable", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas Trabajo")
    public ResponseEntity<?> findByNombreResponsable(@PathVariable(value = "nombre") String term) {
        try {
            return new ResponseEntity(areaTrabajoService.findByNombreResponsable(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Area de Trabajo", response = AreaTrabajoDTO.class, tags = "Areas Trabajo")
    public ResponseEntity<?> create(@Valid @RequestBody AreaTrabajoDTO areaTrabajoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(areaTrabajoService.create(areaTrabajoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Area de Trabajo a partir de su Id", response = AreaTrabajoDTO.class, tags = "Areas Trabajo")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AreaTrabajoDTO areaTrabajoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AreaTrabajoDTO> areaTrabajoUpdated = areaTrabajoService.update(areaTrabajoDTO, id);
                if (areaTrabajoUpdated.isPresent()) {
                    return new ResponseEntity(areaTrabajoUpdated, HttpStatus.OK);
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

    
