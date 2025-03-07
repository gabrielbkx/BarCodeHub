package com.gabrielbkx.BarCodeHub.controller;

import com.gabrielbkx.BarCodeHub.dto.CategoriaDto;
import com.gabrielbkx.BarCodeHub.exceptions.RegistroDuplicadoExeption;
import com.gabrielbkx.BarCodeHub.model.Categoria;
import com.gabrielbkx.BarCodeHub.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Object> criarCategoria(@RequestBody CategoriaDto categoriaDto) {

            String nome = categoriaDto.nome();
            Categoria categoriaSalva = categoriaService.criarCategoria(nome);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(categoriaSalva.getId()).toUri();
            return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/{id}")
    @Transactional // Notação que da commits e rollbacks automaticos no banco de dados
    public ResponseEntity<Categoria> excluirCategoria(@PathVariable("id") UUID id) {
        categoriaService.deletarCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias =  categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }


}
