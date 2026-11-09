
package lanchonete.api.api.controllers;

import lanchonete.api.api.dtos.ProdutoRequest;
import lanchonete.api.api.dtos.ProdutoResponse;
import lanchonete.api.domain.entities.Produto;
import lanchonete.api.infrastructure.repositories.ProdutoRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    // Cadastrar produto
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse criar(
            @Valid @RequestBody ProdutoRequest request) {

        Produto produto = new Produto();

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());

        Produto salvo = repository.save(produto);

        return ProdutoResponse.fromEntity(salvo);
    }

    // Listar produtos
    @GetMapping
    public List<ProdutoResponse> listar() {

        return repository.findAll()
                .stream()
                .map(ProdutoResponse::fromEntity)
                .toList();
    }

    // Consultar produto por ID
    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(@PathVariable Long id) {

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        return ProdutoResponse.fromEntity(produto);
    }

    // Atualizar produto
    @PutMapping("/{id}")
    public ProdutoResponse atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequest request) {

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());

        return ProdutoResponse.fromEntity(
                repository.save(produto)
        );
    }

    // Desativar produto
    @PatchMapping("/{id}/desativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativar(@PathVariable Long id) {

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto não encontrado"
                ));

        produto.setAtivo(false);

        repository.save(produto);
    }
}
