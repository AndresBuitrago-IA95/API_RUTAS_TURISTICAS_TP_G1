package rutasturisticas.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rutasturisticas.api.model.Ciudad;
import rutasturisticas.api.service.CiudadService;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
@Tag(name = "ciudad-controlador", description = "API para gestionar ciudades")
@CrossOrigin(origins = "*")
public class CiudadControlador {
    
    @Autowired
    private CiudadService ciudadService;
    
    @GetMapping
    @Operation(summary = "Obtener todas las ciudades")
    public ResponseEntity<List<Ciudad>> getAllCiudades() {
        return ResponseEntity.ok(ciudadService.findAll());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una ciudad por ID")
    public ResponseEntity<Ciudad> getCiudadById(@PathVariable Long id) {
        return ciudadService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Crear una nueva ciudad")
    public ResponseEntity<Ciudad> createCiudad(@RequestBody Ciudad ciudad) {
        Ciudad savedCiudad = ciudadService.save(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCiudad);
    }
    
    @PutMapping
    @Operation(summary = "Actualizar una ciudad")
    public ResponseEntity<Ciudad> updateCiudad(@RequestBody Ciudad ciudad) {
        if (ciudad.getId() == null || !ciudadService.existsById(ciudad.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ciudadService.save(ciudad));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una ciudad")
    public ResponseEntity<Void> deleteCiudad(@PathVariable Long id) {
        if (!ciudadService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ciudadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
