package rutasturisticas.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rutasturisticas.api.model.Pais;
import rutasturisticas.api.repository.PaisRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {
    
    @Autowired
    private PaisRepository paisRepository;
    
    public List<Pais> findAll() {
        return paisRepository.findAll();
    }
    
    public Optional<Pais> findById(Long id) {
        return paisRepository.findById(id);
    }
    
    public Pais save(Pais pais) {
        return paisRepository.save(pais);
    }
    
    public void deleteById(Long id) {
        paisRepository.deleteById(id);
    }
    
    public boolean existsById(Long id) {
        return paisRepository.existsById(id);
    }
}
