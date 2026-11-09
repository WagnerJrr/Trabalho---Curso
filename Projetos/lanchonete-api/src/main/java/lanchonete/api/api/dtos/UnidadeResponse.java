
package lanchonete.api.api.dtos;

import lanchonete.api.domain.entities.Unidade;
import java.time.LocalDateTime;

public record UnidadeResponse(
    Long id,
    String nome,
    String endereco,
    String telefone,
    Boolean ativa,
    LocalDateTime criadaEm
) {

    public static UnidadeResponse fromEntity(Unidade unidade) {
        return new UnidadeResponse(
            unidade.getId(),
            unidade.getNome(),
            unidade.getEndereco(),
            unidade.getTelefone(),
            unidade.getAtiva(),
            unidade.getCriadaEm()
        );
    }
}
