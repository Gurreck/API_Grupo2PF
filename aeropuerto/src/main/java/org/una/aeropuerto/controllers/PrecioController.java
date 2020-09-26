package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
import org.una.aeropuerto.dto.PrecioDTO;
import org.una.aeropuerto.entities.Precio;
import org.una.aeropuerto.services.IPrecioService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Esteban Vargas
 */
@RestController
@RequestMapping("/precios")
@Api(tags = {"Precios"})
public class PrecioController {
    
    @Autowired
    private IPrecioService precioService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los Precios", response = PrecioDTO.class, responseContainer = "List", tags = "Precios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Precio>> result = precioService.findAll();
            if (result.isPresent()) {
                List<PrecioDTO> precioDTO = MapperUtils.DtoListFromEntityList(result.get(), PrecioDTO.class);
                return new ResponseEntity<>(precioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene Precio por medio del Id", response = PrecioDTO.class, responseContainer = "List", tags = "Precios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Precio> precioFound = precioService.findById(id);
            if (precioFound.isPresent()) {
                PrecioDTO precioDTO = MapperUtils.DtoFromEntity(precioFound.get(), PrecioDTO.class);
                return new ResponseEntity<>(precioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista de Precios entre la fecha especificada", response = PrecioDTO.class, responseContainer = "List", tags = "Precios")
    public @ResponseBody
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            Optional<List<Precio>> result = precioService.findByFechaRegistroBetween(startDate,endDate);
            if (result.isPresent()) {
                List<PrecioDTO> servicioDTO = MapperUtils.DtoListFromEntityList(result.get(), PrecioDTO.class);
                return new ResponseEntity<>(servicioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/avion/{id}")
    @ApiOperation(value = "Obtiene una lista de Precios por Avion", response = PrecioDTO.class, responseContainer = "List", tags = "Precios")
    public ResponseEntity<?> findByTipoServicioId(@PathVariable(value = "term") long term) {
        try {
            Optional<List<Precio>> result = precioService.findByTipoServicioId(term);
            if (result.isPresent()) {
                List<PrecioDTO> precioDto = MapperUtils.DtoListFromEntityList(result.get(), PrecioDTO.class);
                return new ResponseEntity<>(precioDto, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear un Precio", response = PrecioDTO.class, responseContainer = "List", tags = "Precios")
    public ResponseEntity<?> create(@RequestBody Precio precio) {
        try {
            Precio precioCreated = precioService.create(precio);
            PrecioDTO precioDto = MapperUtils.DtoFromEntity(precioCreated, PrecioDTO.class);
            return new ResponseEntity<>(precioDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    @ApiOperation(value = "Permite actualizar un Precio", response = PrecioDTO.class, responseContainer = "List", tags = "Precios")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Precio precioModified) {
        try {
            Optional<Precio> precioUpdated = precioService.update(precioModified, id);
            if (precioUpdated.isPresent()) {
                PrecioDTO precioDto = MapperUtils.DtoFromEntity(precioUpdated.get(), PrecioDTO.class);
                return new ResponseEntity<>(precioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
