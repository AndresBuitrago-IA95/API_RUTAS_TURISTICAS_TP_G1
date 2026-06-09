package rutasturisticas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rutasturisticas.api.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
}
