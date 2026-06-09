package rutasturisticas.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rutasturisticas.api.model.Parada;
import rutasturisticas.api.repository.ParadaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParadaService {
    
    @Autowired
    private ParadaRepository paradaRepository;
    
    public List<Parada> findAll() {
        return paradaRepository.findAll();
    }
    
    public Optional<Parada> findById(Long id) {
        return paradaRepository.findById(id);
    }
    
    public List<Parada> findByRutaId(Long idRuta) {
        return paradaRepository.findByRutaIdOrderByOrdenAsc(idRuta);
    }
    
    public Parada save(Parada parada) {
        return paradaRepository.save(parada);
    }
    
    public void deleteById(Long id) {
        paradaRepository.deleteById(id);
    }
    
    public boolean existsById(Long id) {
        return paradaRepository.existsById(id);
    }
}
