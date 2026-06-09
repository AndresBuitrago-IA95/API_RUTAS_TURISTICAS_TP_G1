package rutasturisticas.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rutasturisticas.api.model.Parada;
import rutasturisticas.api.service.ParadaService;

import java.util.List;

@RestController
@RequestMapping("/api/paradas")
@Tag(name = "parada-controlador", description = "API para gestionar paradas de rutas")
@CrossOrigin(origins = "*")
public class ParadaControlador {
    
    @Autowired
    private ParadaService paradaService;
    
    @GetMapping
    @Operation(summary = "Obtener todas las paradas")
    public ResponseEntity<List<Parada>> getAllParadas() {
        return ResponseEntity.ok(paradaService.findAll());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una parada por ID")
    public ResponseEntity<Parada> getParadaById(@PathVariable Long id) {
        return paradaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/ruta/{idRuta}")
    @Operation(summary = "Obtener paradas de una ruta específica ordenadas por orden")
    public ResponseEntity<List<Parada>> getParadasByRuta(@PathVariable Long idRuta) {
        return ResponseEntity.ok(paradaService.findByRutaId(idRuta));
    }
    
    @PostMapping
    @Operation(summary = "Crear una nueva parada")
    public ResponseEntity<Parada> createParada(@RequestBody Parada parada) {
        Parada savedParada = paradaService.save(parada);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParada);
    }
    
    @PutMapping
    @Operation(summary = "Actualizar una parada")
    public ResponseEntity<Parada> updateParada(@RequestBody Parada parada) {
        if (parada.getId() == null || !paradaService.existsById(parada.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paradaService.save(parada));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una parada")
    public ResponseEntity<Void> deleteParada(@PathVariable Long id) {
        if (!paradaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        paradaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
