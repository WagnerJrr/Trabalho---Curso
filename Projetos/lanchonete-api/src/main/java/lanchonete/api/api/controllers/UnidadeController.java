
package lanchonete.api.api.controllers;

import lanchonete.api.api.dtos.UnidadeRequest;
import lanchonete.api.api.dtos.UnidadeResponse;
import lanchonete.api.domain.entities.Unidade;
import lanchonete.api.infrastructure.repositories.UnidadeRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private final UnidadeRepository repository;

    public UnidadeController(UnidadeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnidadeResponse criar(
            @Valid @RequestBody UnidadeRequest request) {

        Unidade unidade = new Unidade();

        unidade.setNome(request.nome());
        unidade.setEndereco(request.endereco());
        unidade.setTelefone(request.telefone());

        Unidade salva = repository.save(unidade);

        return UnidadeResponse.fromEntity(salva);
    }

    @GetMapping
    public List<UnidadeResponse> listar() {

        return repository.findByAtivaTrue()
                .stream()
                .map(UnidadeResponse::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public UnidadeResponse buscarPorId(@PathVariable Long id) {

        Unidade unidade = buscarUnidadeAtiva(id);

        return UnidadeResponse.fromEntity(unidade);
    }

    @PutMapping("/{id}")
    public UnidadeResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UnidadeRequest request) {

        Unidade unidade = buscarUnidadeAtiva(id);

        unidade.setNome(request.nome());
        unidade.setEndereco(request.endereco());
        unidade.setTelefone(request.telefone());

        return UnidadeResponse.fromEntity(repository.save(unidade));
    }

    @PatchMapping("/{id}/desativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativar(@PathVariable Long id) {

        Unidade unidade = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unidade não encontrada"
                ));

        unidade.setAtiva(false);

        repository.save(unidade);
    }

    private Unidade buscarUnidadeAtiva(Long id) {

        Unidade unidade = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unidade não encontrada"
                ));

        if (!Boolean.TRUE.equals(unidade.getAtiva())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A unidade está desativada"
            );
        }

        return unidade;
    }
}
