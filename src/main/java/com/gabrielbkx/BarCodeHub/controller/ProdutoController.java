package com.gabrielbkx.BarCodeHub.controller;


import com.gabrielbkx.BarCodeHub.dto.ProdutoDTO;
import com.gabrielbkx.BarCodeHub.model.Produto;
import com.gabrielbkx.BarCodeHub.services.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("produtos")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> salvarProduto(@RequestBody ProdutoDTO dto) {

        Produto produtoSalvo = produtoService.criarProduto(dto.nome().toUpperCase(), dto.quantidade(), dto.preco(), dto.descricao(),
                dto.codigoInterno(), dto.referencia(), dto.fornecedor(), dto.categoria());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(produtoSalvo.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @GetMapping
    public ResponseEntity<List<Produto>> exibirTodosOsProdutos() {
        List<Produto> produtos = produtoService.listar();
        return ResponseEntity.ok(produtos);
    }

}
