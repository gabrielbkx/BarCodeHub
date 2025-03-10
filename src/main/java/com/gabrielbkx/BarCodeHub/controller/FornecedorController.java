package com.gabrielbkx.BarCodeHub.controller;

import com.gabrielbkx.BarCodeHub.dto.FornecedorDTO;
import com.gabrielbkx.BarCodeHub.model.Categoria;
import com.gabrielbkx.BarCodeHub.model.Fornecedor;
import com.gabrielbkx.BarCodeHub.services.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;


    @PostMapping
    public ResponseEntity<Fornecedor> cadastrarFornecedor(@RequestBody FornecedorDTO dto) {

        String nome = dto.nome();
        String cnpj = dto.cnpj();
        Fornecedor fornecedorSalvo = fornecedorService.cadastrar(nome,cnpj);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(fornecedorSalvo.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @Transactional // Notação que da commits e rollbacks automaticos no banco de dados
    public ResponseEntity<Categoria> excluirFornecedor(@PathVariable("id") UUID id) {
        fornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listarFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.listar();
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarFornecedorPorId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(fornecedorService.buscar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarFornecedor(@PathVariable UUID id,
                                                        @RequestBody FornecedorDTO dto) {
        fornecedorService.atualizar(id, dto.nome(), dto.cnpj());
        return ResponseEntity.ok().build();
    }

}
