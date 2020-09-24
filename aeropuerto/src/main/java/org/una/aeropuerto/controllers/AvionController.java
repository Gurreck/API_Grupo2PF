package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
import org.una.aeropuerto.dto.AvionDTO;
import org.una.aeropuerto.dto.UsuarioDTO;
import org.una.aeropuerto.entities.Avion;
import org.una.aeropuerto.entities.Usuario;
import org.una.aeropuerto.services.IAvionService;
import org.una.aeropuerto.utils.MapperUtils;

@RestController
@RequestMapping("/aviones") 
@Api(tags = {"Aviones"})
public class AvionController {

    @Autowired
    private IAvionService avionService;
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Aviones", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Avion>> result = avionService.findAll();
            if (result.isPresent()) {
                List<AvionDTO> avionDTO = MapperUtils.DtoListFromEntityList(result.get(), AvionDTO.class);
                return new ResponseEntity<>(avionDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Avion por medio del Id", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Avion> avionFound = avionService.findById(id);
            if (avionFound.isPresent()) {
                AvionDTO avionDto = MapperUtils.DtoFromEntity(avionFound.get(), AvionDTO.class);
                return new ResponseEntity<>(avionDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/matricula/") 
    @ApiOperation(value = "Obtiene un Avion por medio de la matricula", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByMatricula(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Avion>> result = avionService.findByMatricula(term);
            if (result.isPresent()) {
                List<AvionDTO> avionesDTO = MapperUtils.DtoListFromEntityList(result.get(), AvionDTO.class);
                return new ResponseEntity<>(avionesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/TipoAvion/") 
    @ApiOperation(value = "Obtiene una lista de Aviones por medio del tipo de avion", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByTipoAvion(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Avion>> result = avionService.findByTipoAvion(term);
            if (result.isPresent()) {
                List<AvionDTO> avionesDTO = MapperUtils.DtoListFromEntityList(result.get(), AvionDTO.class);
                return new ResponseEntity<>(avionesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/FechaRegistro/") 
    @ApiOperation(value = "Obtiene una lista de Aviones por medio de la fecha de registro", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByFechaRegistro(@PathVariable(value = "fechaRegistro") Date fechaRegistro) {
        try {
            Optional<List<Avion>> result = avionService.findByFechaRegistro(fechaRegistro);
            if (result.isPresent()) {
                List<AvionDTO> avionesDTO = MapperUtils.DtoListFromEntityList(result.get(), AvionDTO.class);
                return new ResponseEntity<>(avionesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/Estado/") 
    @ApiOperation(value = "Obtiene una lista de Aviones por medio del estado", response = AvionDTO.class, responseContainer = "List", tags = "Aviones")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Avion>> result = avionService.findByEstado(estado);
            if (result.isPresent()) {
                List<AvionDTO> avionesDTO = MapperUtils.DtoListFromEntityList(result.get(), AvionDTO.class);
                return new ResponseEntity<>(avionesDTO, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear un Avion", response = AvionDTO.class, tags = "Aviones")
    public ResponseEntity<?> create(@RequestBody Avion avion) {
        try {
            Avion avionCreated = avionService.create(avion);
            AvionDTO avionDto = MapperUtils.DtoFromEntity(avionCreated, AvionDTO.class);
            return new ResponseEntity<>(avionDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Avion a partir de su Id", response = AvionDTO.class, tags = "Aviones")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Avion avionModified) {
        try {
            Optional<Avion> avionUpdated = avionService.update(avionModified, id);
            if (avionUpdated.isPresent()) {
                AvionDTO avionDto = MapperUtils.DtoFromEntity(avionUpdated.get(), AvionDTO.class);
                return new ResponseEntity<>(avionDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
