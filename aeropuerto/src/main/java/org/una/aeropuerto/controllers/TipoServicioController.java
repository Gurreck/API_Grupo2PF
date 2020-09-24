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
import org.una.aeropuerto.dto.TipoServicioDTO;
import org.una.aeropuerto.entities.TipoServicio;
import org.una.aeropuerto.services.ITipoServicioService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/tiposServicios") 
@Api(tags = {"tipos Servicios"})
public class TipoServicioController {
    
     @Autowired
    private ITipoServicioService tipoServicioService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tipos de Servicios", response = TipoServicioDTO.class, responseContainer = "List", tags = "tipos Servicios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TipoServicio>> result = tipoServicioService.findAll();
            if (result.isPresent()) {
                List<TipoServicioDTO> tiposervicioDTO = MapperUtils.DtoListFromEntityList(result.get(), TipoServicioDTO.class);
                return new ResponseEntity<>(tiposervicioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene  el tipo de Servicio por medio del Id", response = TipoServicioDTO.class, responseContainer = "List", tags = "tipos Servicios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TipoServicio> tiposervicioFound = tipoServicioService.findById(id);
            if (tiposervicioFound.isPresent()) {
                TipoServicioDTO tipoServicioDto = MapperUtils.DtoFromEntity(tiposervicioFound.get(), TipoServicioDTO.class);
                return new ResponseEntity<>(tipoServicioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombre/")
    @ApiOperation(value = "Obtiene una lista con el tipo de Servicio por medio del nombre", response = TipoServicioDTO.class, responseContainer = "List", tags = "tipos Servicios")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        try {
            Optional<List<TipoServicio>> result = tipoServicioService.findByNombre(term);
            if (result.isPresent()) {
                List<TipoServicioDTO> tipoServicioDTO = MapperUtils.DtoListFromEntityList(result.get(), TipoServicioDTO.class);
                return new ResponseEntity<>(tipoServicioDTO, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear un tipo de Servicio", response = TipoServicioDTO.class, tags = "tipos Servicios")
    public ResponseEntity<?> create(@RequestBody TipoServicio tipoServicio) {
        try {
            TipoServicio tipoServicioCreated = tipoServicioService.create(tipoServicio);
            TipoServicioDTO tipoServicioDto = MapperUtils.DtoFromEntity(tipoServicioCreated, TipoServicioDTO.class);
            return new ResponseEntity<>(tipoServicioDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un tipo de Servicio a partir de su Id", response = TipoServicioDTO.class, tags = "tipos Servicios")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TipoServicio tipoServicioModified) {
        try {
            Optional<TipoServicio> tipoServicioUpdated = tipoServicioService.update(tipoServicioModified, id);
            if (tipoServicioUpdated.isPresent()) {
                TipoServicioDTO tipoServicioDto = MapperUtils.DtoFromEntity(tipoServicioUpdated.get(), TipoServicioDTO.class);
                return new ResponseEntity<>(tipoServicioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}