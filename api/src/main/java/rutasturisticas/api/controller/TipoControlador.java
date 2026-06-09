package rutasturisticas.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rutasturisticas.api.model.Tipo;
import rutasturisticas.api.service.TipoService;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@Tag(name = "tipo-controlador", description = "API para gestionar tipos de transporte")
@CrossOrigin(origins = "*")
public class TipoControlador {
    
    @Autowired
    private TipoService tipoService;
    
    @GetMapping
    @Operation(summary = "Obtener todos los tipos de transporte")
    public ResponseEntity<List<Tipo>> getAllTipos() {
        return ResponseEntity.ok(tipoService.findAll());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un tipo de transporte por ID")
    public ResponseEntity<Tipo> getTipoById(@PathVariable Long id) {
        return tipoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Crear un nuevo tipo de transporte")
    public ResponseEntity<Tipo> createTipo(@RequestBody Tipo tipo) {
        Tipo savedTipo = tipoService.save(tipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTipo);
    }
    
    @PutMapping
    @Operation(summary = "Actualizar un tipo de transporte")
    public ResponseEntity<Tipo> updateTipo(@RequestBody Tipo tipo) {
        if (tipo.getId() == null || !tipoService.existsById(tipo.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tipoService.save(tipo));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un tipo de transporte")
    public ResponseEntity<Void> deleteTipo(@PathVariable Long id) {
        if (!tipoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
