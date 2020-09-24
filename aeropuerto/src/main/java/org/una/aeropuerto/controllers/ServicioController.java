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
import org.una.aeropuerto.dto.ServicioDTO;
import org.una.aeropuerto.entities.Servicio;
import org.una.aeropuerto.services.IServicioService;
import org.una.aeropuerto.utils.MapperUtils;

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

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Servicios", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Servicio>> result = servicioService.findAll();
            if (result.isPresent()) {
                List<ServicioDTO> serviciosDTO = MapperUtils.DtoListFromEntityList(result.get(), ServicioDTO.class);
                return new ResponseEntity<>(serviciosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene el Servicio por medio del Id", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Servicio> servicioFound = servicioService.findById(id);
            if (servicioFound.isPresent()) {
                ServicioDTO ServicioDto = MapperUtils.DtoFromEntity(servicioFound.get(), ServicioDTO.class);
                return new ResponseEntity<>(ServicioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @GetMapping("/{Estado}") 
    @ApiOperation(value = "Obtiene una lista de Servicios por medio del estado", response =  ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Servicio>> servicioFound = servicioService.findByEstado(estado);
            if (servicioFound.isPresent()) {
                ServicioDTO ServicioDTO = MapperUtils.DtoFromEntity(servicioFound.get(), ServicioDTO.class);
                return new ResponseEntity<>(ServicioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{EstadoCobro}") 
    @ApiOperation(value = "Obtiene una lista de Servicios por medio del estado del cobro", response =  ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByEstadoCobro(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Servicio>> servicioFound = servicioService.findByEstadoCobro(estado);
            if (servicioFound.isPresent()) {
                ServicioDTO ServicioDTO = MapperUtils.DtoFromEntity(servicioFound.get(), ServicioDTO.class);
                return new ResponseEntity<>(ServicioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}") 
    @ApiOperation(value = "Obtiene una lista de Servicios entre la fecha especificada", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public @ResponseBody
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "Fecha inicial") Date startDate, @PathVariable(value = "Fecha final") Date endDate) {
        try {
            Optional<List<Servicio>> result = servicioService.findByFechaRegistroBetween(startDate,endDate);
            if (result.isPresent()) {
                List<ServicioDTO> servicioDTO = MapperUtils.DtoListFromEntityList(result.get(), ServicioDTO.class);
                return new ResponseEntity<>(servicioDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/avion/{id}")
    @ApiOperation(value = "Obtiene una lista de Servicios por Avion", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByAvionId(@PathVariable(value = "term") long term) {
        try {
            Optional<List<Servicio>> result = servicioService.findByAvionId(term);
            if (result.isPresent()) {
                List<ServicioDTO> servicioDto = MapperUtils.DtoListFromEntityList(result.get(), ServicioDTO.class);
                return new ResponseEntity<>(servicioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tiposervicio/{id}")
    @ApiOperation(value = "Obtiene una lista de Servicios por tipo de Servicio", response = ServicioDTO.class, responseContainer = "List", tags = "Servicios")
    public ResponseEntity<?> findByTipoServicioId(@PathVariable(value = "term") long term) {
        try {
            Optional<List<Servicio>> result = servicioService.findByTipoServicioId(term);
            if (result.isPresent()) {
                List<ServicioDTO> servicioDto = MapperUtils.DtoListFromEntityList(result.get(), ServicioDTO.class);
                return new ResponseEntity<>(servicioDto, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear un Servicio", response = ServicioDTO.class, tags = "Servicios")
    public ResponseEntity<?> create(@RequestBody Servicio servicio) {
        try {
            Servicio servicioCreated = servicioService.create(servicio);
            ServicioDTO ServicioDto = MapperUtils.DtoFromEntity(servicioCreated, ServicioDTO.class);
            return new ResponseEntity<>(ServicioDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Servicio a partir de su Id", response = ServicioDTO.class, tags = "Servicios")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Servicio servicioModified) {
        try {
            Optional<Servicio> servicioUpdated = servicioService.update(servicioModified, id);
            if (servicioUpdated.isPresent()) {
                ServicioDTO ServicioDto = MapperUtils.DtoFromEntity(servicioUpdated.get(), ServicioDTO.class);
                return new ResponseEntity<>(ServicioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

