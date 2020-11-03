package org.una.aeropuerto.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
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
import org.una.aeropuerto.dto.AerolineaDTO;
import org.una.aeropuerto.services.IAerolineaService;

@RestController
@RequestMapping("/aerolineas") 
@Api(tags = {"Aerolineas"})
public class AerolineaController {
    
    @Autowired
    private IAerolineaService aerolineaService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/findAll")
    @ApiOperation(value = "Obtiene una lista de todas las Aerolineas", response = AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(aerolineaService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene la Aerolinea por medio del Id", response = AerolineaDTO.class, tags = "Aerolineas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(aerolineaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByNombreAerolinea/{nombre}")
    @ApiOperation(value = "Obtiene una lista de Aerolinea por medio del nombre", response =  AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByNombreAerolineaAproximateIgnoreCase(@PathVariable(value = "nombre") String term) {
        try {
            return new ResponseEntity(aerolineaService.findByNombreAerolineaAproximateIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByNombreResponsable/{nombre}")
    @ApiOperation(value = "Obtiene una lista de Aerolinea por medio del nombre del responsable", response =  AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByNombreResponsableAproximateIgnoreCase(@PathVariable(value = "nombre") String term) {
        try {
            return new ResponseEntity(aerolineaService.findByNombreResponsableAproximateIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByEstado/{estado}")
    @ApiOperation(value = "Obtiene una lista de Aerolinea por medio del estado", response =  AerolineaDTO.class, responseContainer = "List", tags = "Aerolineas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(aerolineaService.findByEstado(estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/") 
    @ApiOperation(value = "Permite crear una Aerolinea", response = AerolineaDTO.class, tags = "Aerolineas")
    public ResponseEntity<?> create(@Valid @RequestBody AerolineaDTO aerolineaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(aerolineaService.create(aerolineaDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar una Aerolinea a partir de su Id", response = AerolineaDTO.class, tags = "Aerolineas")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody AerolineaDTO aerolineaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<AerolineaDTO> aerolineaUpdated = aerolineaService.update(aerolineaDTO, id);
                if (aerolineaUpdated.isPresent()) {
                    return new ResponseEntity(aerolineaUpdated, HttpStatus.OK);
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

