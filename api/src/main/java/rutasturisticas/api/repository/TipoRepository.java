package rutasturisticas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rutasturisticas.api.model.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {
}
