
package lanchonete.api.infrastructure.repositories;

import lanchonete.api.domain.entities.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    List<Unidade> findByAtivaTrue();

}
