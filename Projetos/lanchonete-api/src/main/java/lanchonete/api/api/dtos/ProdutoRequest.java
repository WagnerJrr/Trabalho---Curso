
package lanchonete.api.api.dtos;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProdutoRequest(

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100)
    String nome,

    @Size(max = 500)
    String descricao,

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    BigDecimal preco

) {}
