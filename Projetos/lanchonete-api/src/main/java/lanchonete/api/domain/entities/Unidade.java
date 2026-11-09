
package lanchonete.api.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "unidades")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da unidade é obrigatório")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O endereço é obrigatório")
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String endereco;

    @NotBlank(message = "O telefone é obrigatório")
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false)
    private Boolean ativa = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadaEm = LocalDateTime.now();

    public Unidade() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }
}
