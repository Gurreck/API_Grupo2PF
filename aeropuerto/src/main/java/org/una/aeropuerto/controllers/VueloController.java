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
import org.una.aeropuerto.dto.VueloDTO;
import org.una.aeropuerto.entities.Vuelo;
import org.una.aeropuerto.services.IVueloService;
import org.una.aeropuerto.utils.MapperUtils;

@RestController
@RequestMapping("/vuelos") 
@Api(tags = {"Vuelos"})
public class VueloController {

    @Autowired
    private IVueloService vueloService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Vuelos", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Vuelo>> result = vueloService.findAll();
            if (result.isPresent()) {
                List<VueloDTO> vuelosDTO = MapperUtils.DtoListFromEntityList(result.get(), VueloDTO.class);
                return new ResponseEntity<>(vuelosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista con el Vuelo por medio del Id", response = VueloDTO.class, responseContainer = "List", tags = "Vuelos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Vuelo> vueloFound = vueloService.findById(id);
            if (vueloFound.isPresent()) {
                VueloDTO vueloDto = MapperUtils.DtoFromEntity(vueloFound.get(), VueloDTO.class);
                return new ResponseEntity<>(vueloDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Aeropuerto/")
    @ApiOperation(value = "Obtiene una lista con el Vuelo por medio del nombre del aeropuerto", response = VueloDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByAeropuerto(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Vuelo>> result = vueloService.findByAeropuerto(term);
            if (result.isPresent()) {
                List<VueloDTO> vueloDTO = MapperUtils.DtoListFromEntityList(result.get(), VueloDTO.class);
                return new ResponseEntity<>(vueloDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{Estado}") 
    @ApiOperation(value = "Obtiene una lista con la aerolinea por medio del estado", response =  VueloDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Vuelo>> vueloFound = vueloService.findByEstado(estado);
            if (vueloFound.isPresent()) {
                VueloDTO VueloDTO = MapperUtils.DtoFromEntity(vueloFound.get(), VueloDTO.class);
                return new ResponseEntity<>(VueloDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/FechaSalida/")
    @ApiOperation(value = "Obtiene una lista con el Vuelo por medio de la fecha de salida", response = VueloDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByFechaSalida(@PathVariable(value = "term") Date term) {
        try {
            Optional<List<Vuelo>> result = vueloService.findByFechaSalida(term);
            if (result.isPresent()) {
                List<VueloDTO> vueloDTO = MapperUtils.DtoListFromEntityList(result.get(), VueloDTO.class);
                return new ResponseEntity<>(vueloDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/FechaLlegada/")
    @ApiOperation(value = "Obtiene una lista con el Vuelo por medio de la fecha de salida", response = VueloDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByFechaLlegada(@PathVariable(value = "term") Date term) {
        try {
            Optional<List<Vuelo>> result = vueloService.findByFechaLlegada(term);
            if (result.isPresent()) {
                List<VueloDTO> vueloDTO = MapperUtils.DtoListFromEntityList(result.get(), VueloDTO.class);
                return new ResponseEntity<>(vueloDTO, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear un Usuario", response = VueloDTO.class, tags = "Usuarios")
    public ResponseEntity<?> create(@RequestBody Vuelo vuelo) {
        try {
            Vuelo vueloCreated = vueloService.create(vuelo);
            VueloDTO vueloDto = MapperUtils.DtoFromEntity(vueloCreated, VueloDTO.class);
            return new ResponseEntity<>(vueloDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Permite modificar un Usuario a partir de su Id", response = VueloDTO.class, tags = "Usuarios")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Vuelo vueloModified) {
        try {
            Optional<Vuelo> vueloUpdated = vueloService.update(vueloModified, id);
            if (vueloUpdated.isPresent()) {
                VueloDTO vueloDto = MapperUtils.DtoFromEntity(vueloUpdated.get(), VueloDTO.class);
                return new ResponseEntity<>(vueloDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

