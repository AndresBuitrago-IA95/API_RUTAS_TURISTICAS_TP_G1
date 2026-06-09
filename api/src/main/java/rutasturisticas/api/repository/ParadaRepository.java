package rutasturisticas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rutasturisticas.api.model.Parada;
import java.util.List;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {
    List<Parada> findByRutaIdOrderByOrdenAsc(Long idRuta);
}
