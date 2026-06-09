package rutasturisticas.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rutasturisticas.api.model.Ruta;
import rutasturisticas.api.repository.RutaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RutaService {
    
    @Autowired
    private RutaRepository rutaRepository;
    
    public List<Ruta> findAll() {
        return rutaRepository.findAll();
    }
    
    public Optional<Ruta> findById(Long id) {
        return rutaRepository.findById(id);
    }
    
    public List<Ruta> findByCiudadId(Long idCiudad) {
        return rutaRepository.findByCiudadId(idCiudad);
    }
    
    public Ruta save(Ruta ruta) {
        return rutaRepository.save(ruta);
    }
    
    public void deleteById(Long id) {
        rutaRepository.deleteById(id);
    }
    
    public boolean existsById(Long id) {
        return rutaRepository.existsById(id);
    }
}
