package rutasturisticas.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rutasturisticas.api.model.Ruta;
import rutasturisticas.api.service.RutaService;

import java.util.List;

@RestController
@RequestMapping("/api/rutas")
@Tag(name = "ruta-controlador", description = "API para gestionar rutas turísticas")
@CrossOrigin(origins = "*")
public class RutaControlador {
    
    @Autowired
    private RutaService rutaService;
    
    @GetMapping
    @Operation(summary = "Obtener todas las rutas")
    public ResponseEntity<List<Ruta>> getAllRutas() {
        return ResponseEntity.ok(rutaService.findAll());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una ruta por ID")
    public ResponseEntity<Ruta> getRutaById(@PathVariable Long id) {
        return rutaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/ciudad/{idCiudad}")
    @Operation(summary = "Obtener rutas de una ciudad específica")
    public ResponseEntity<List<Ruta>> getRutasByCiudad(@PathVariable Long idCiudad) {
        return ResponseEntity.ok(rutaService.findByCiudadId(idCiudad));
    }
    
    @PostMapping
    @Operation(summary = "Crear una nueva ruta")
    public ResponseEntity<Ruta> createRuta(@RequestBody Ruta ruta) {
        Ruta savedRuta = rutaService.save(ruta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRuta);
    }
    
    @PutMapping
    @Operation(summary = "Actualizar una ruta")
    public ResponseEntity<Ruta> updateRuta(@RequestBody Ruta ruta) {
        if (ruta.getId() == null || !rutaService.existsById(ruta.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rutaService.save(ruta));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una ruta")
    public ResponseEntity<Void> deleteRuta(@PathVariable Long id) {
        if (!rutaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        rutaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
