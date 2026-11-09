
package lanchonete.api.infrastructure.repositories;

import lanchonete.api.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository
        extends JpaRepository<Produto, Long> {
}
