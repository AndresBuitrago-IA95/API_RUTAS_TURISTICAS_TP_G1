package rutasturisticas.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rutasturisticas.api.model.Pais;
import rutasturisticas.api.service.PaisService;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
@Tag(name = "pais-controlador", description = "API para gestionar países")
@CrossOrigin(origins = "*")
public class PaisControlador {
    
    @Autowired
    private PaisService paisService;
    
    @GetMapping
    @Operation(summary = "Obtener todos los países")
    public ResponseEntity<List<Pais>> getAllPaises() {
        return ResponseEntity.ok(paisService.findAll());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un país por ID")
    public ResponseEntity<Pais> getPaisById(@PathVariable Long id) {
        return paisService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Crear un nuevo país")
    public ResponseEntity<Pais> createPais(@RequestBody Pais pais) {
        Pais savedPais = paisService.save(pais);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPais);
    }
    
    @PutMapping
    @Operation(summary = "Actualizar un país")
    public ResponseEntity<Pais> updatePais(@RequestBody Pais pais) {
        if (pais.getId() == null || !paisService.existsById(pais.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paisService.save(pais));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un país")
    public ResponseEntity<Void> deletePais(@PathVariable Long id) {
        if (!paisService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        paisService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
