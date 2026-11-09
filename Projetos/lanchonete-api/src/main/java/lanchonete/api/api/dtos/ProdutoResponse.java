
package lanchonete.api.api.dtos;

import lanchonete.api.domain.entities.Produto;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponse(
    Long id,
    String nome,
    String descricao,
    BigDecimal preco,
    Boolean ativo,
    LocalDateTime criadoEm
) {
    public static ProdutoResponse fromEntity(Produto produto) {
        return new ProdutoResponse(
            produto.getId(),
            produto.getNome(),
            produto.getDescricao(),
            produto.getPreco(),
            produto.getAtivo(),
            produto.getCriadoEm()
        );
    }
}
