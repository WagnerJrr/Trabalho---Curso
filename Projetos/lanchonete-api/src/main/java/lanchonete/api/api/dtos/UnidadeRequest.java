
package lanchonete.api.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UnidadeRequest(

    @NotBlank(message = "O nome da unidade é obrigatório")
    @Size(max = 100)
    String nome,

    @NotBlank(message = "O endereço é obrigatório")
    @Size(max = 150)
    String endereco,

    @NotBlank(message = "O telefone é obrigatório")
    @Size(max = 20)
    String telefone

) {
}

