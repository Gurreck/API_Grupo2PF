package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.una.aeropuerto.dto.AreaTrabajoDTO;
import org.una.aeropuerto.entities.AreaTrabajo;
import org.una.aeropuerto.services.IAreaTrabajoService;
import org.una.aeropuerto.utils.MapperUtils;

@RestController
@RequestMapping("/areasTrabajo") 
@Api(tags = {"Areas Trabajo"})
public class AreaTrabajoController {

    @Autowired
    private IAreaTrabajoService areaTrabajoService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las areas de trabajo", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas Trabajo")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<AreaTrabajo>> result = areaTrabajoService.findAll();
            if (result.isPresent()) {
                List<AreaTrabajoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista con el AreaTrabajo por medio del Id", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas Trabajo")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<AreaTrabajo> areaTrabajoFound = areaTrabajoService.findById(id);
            if (areaTrabajoFound.isPresent()) {
                AreaTrabajoDTO areaTrabjoDTo = MapperUtils.DtoFromEntity(areaTrabajoFound.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(areaTrabjoDTo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     @GetMapping("/{area}")
    @ApiOperation(value = "Obtiene el Area Trabajo por medio del nombre", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas Trabajo")
    public ResponseEntity<?> findByNombreArea(@PathVariable(value = "id") String id) {
        try {

            Optional<AreaTrabajo> areaTrabajoFound = areaTrabajoService.findByNombreArea(id);
            if (areaTrabajoFound.isPresent()) {
                AreaTrabajoDTO areaTrabjoDTo = MapperUtils.DtoFromEntity(areaTrabajoFound.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(areaTrabjoDTo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @GetMapping("/nombre/")
    @ApiOperation(value = "Obtiene una lista con el AreaTrabajo por medio del nombre", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas Trabajo")
    public ResponseEntity<?> findByNombreResponsable(@PathVariable(value = "term") String term) {
        try {
            Optional<List<AreaTrabajo>> result = areaTrabajoService.findByNombreResponsable(term);
            if (result.isPresent()) {
                List<AreaTrabajoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear un Area de Trabajo", response = AreaTrabajoDTO.class, tags = "Areas Trabajo")
    public ResponseEntity<?> create(@RequestBody AreaTrabajo usuario) {
        try {
            AreaTrabajo usuarioCreated = areaTrabajoService.create(usuario);
            AreaTrabajoDTO areaTrabjoDTo = MapperUtils.DtoFromEntity(usuarioCreated, AreaTrabajoDTO.class);
            return new ResponseEntity<>(areaTrabjoDTo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Area de Trabajo a partir de su Id", response = AreaTrabajoDTO.class, tags = "Areas Trabajo")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AreaTrabajo areaTrabajoModified) {
        try {
            Optional<AreaTrabajo> usuarioUpdated = areaTrabajoService.update(areaTrabajoModified, id);
            if (usuarioUpdated.isPresent()) {
                AreaTrabajoDTO areaTrabjoDTo = MapperUtils.DtoFromEntity(usuarioUpdated.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(areaTrabjoDTo, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

    
