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
import org.una.aeropuerto.dto.AerolineaDTO;
import org.una.aeropuerto.entities.Aerolinea;
import org.una.aeropuerto.services.IAerolineaService;
import org.una.aeropuerto.utils.MapperUtils;

@RestController
@RequestMapping("/aerolineas") 
@Api(tags = {"Aerolineas"})
public class AerolineaController {
    
    @Autowired
    private IAerolineaService aerolineaService;
    
    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos las aerolineas", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Aerolinea>> result = aerolineaService.findAll();
            if (result.isPresent()) {
                List<AerolineaDTO> aerolineaDTO = MapperUtils.DtoListFromEntityList(result.get(), AerolineaDTO.class);
                return new ResponseEntity<>(aerolineaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene una lista con la aerolinea por medio del Id", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<Aerolinea> AerolineaFound = aerolineaService.findById(id);
            if (AerolineaFound.isPresent()) {
                AerolineaDTO AerolineaDTO = MapperUtils.DtoFromEntity(AerolineaFound.get(), AerolineaDTO.class);
                return new ResponseEntity<>(AerolineaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombreAerolinea/")
    @ApiOperation(value = "Obtiene una lista con la aerolinea por medio del nombre", response =  AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByNombreAerolinea(@PathVariable(value = "term") String term) {
        try {
            Optional<Aerolinea> result = aerolineaService.findByNombreAerolinea(term);
            if (result.isPresent()) {
                 AerolineaDTO AerolineaDTO = MapperUtils.DtoFromEntity(result.get(), AerolineaDTO.class);
                return new ResponseEntity<>(AerolineaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombreResponsable/")
    @ApiOperation(value = "Obtiene una lista con la aerolinea por medio del nombre del responsable", response =  AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByNombreResponsable(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Aerolinea>> result = aerolineaService.findByNombreResponsable(term);
            if (result.isPresent()) {
                List<AerolineaDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(),  AerolineaDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{Estado}") 
    @ApiOperation(value = "Obtiene una lista con la aerolinea por medio del estado", response =  AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Aerolinea>> AerolineaFound = aerolineaService.findByEstado(estado);
            if (AerolineaFound.isPresent()) {
                AerolineaDTO AerolineaDTO = MapperUtils.DtoFromEntity(AerolineaFound.get(), AerolineaDTO.class);
                return new ResponseEntity<>(AerolineaDTO, HttpStatus.OK);
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
    @ApiOperation(value = "Permite crear una aerolinea", response = AerolineaDTO.class, tags = "Aerolineas")
    public ResponseEntity<?> create(@RequestBody Aerolinea Aerolinea) {
        try {
            Aerolinea AerolineaCreated = aerolineaService.create(Aerolinea);
            AerolineaDTO AerolineaDto = MapperUtils.DtoFromEntity(AerolineaCreated, AerolineaDTO.class);
            return new ResponseEntity<>(AerolineaDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar una aerolinea a partir de su Id", response = AerolineaDTO.class, tags = "Aerolinea")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Aerolinea AerolineaModified) {
        try {
            Optional<Aerolinea> AerolineaUpdated = aerolineaService.update(AerolineaModified, id);
            if (AerolineaUpdated.isPresent()) {
                AerolineaDTO AerolineaDto = MapperUtils.DtoFromEntity(AerolineaUpdated.get(), AerolineaDTO.class);
                return new ResponseEntity<>(AerolineaDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

