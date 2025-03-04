package com.gabrielbkx.BarCodeHub.controller;

import com.gabrielbkx.BarCodeHub.exceptions.RegistroDuplicadoExeption;
import com.gabrielbkx.BarCodeHub.model.Categoria;
import com.gabrielbkx.BarCodeHub.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        try {

            Categoria categoriaSalva = categoriaService.criarCategoria(categoria);

            URI location = ServletUriComponentsBuilder.
                    fromCurrentRequest().path("/{id}").buildAndExpand(categoriaSalva.getId()).toUri();

            return ResponseEntity.created(location).build();

        }catch (RegistroDuplicadoExeption e){

            var conflito = HttpStatus.CONFLICT.value();
            return ResponseEntity.status(conflito).build();
        }
    }

    @DeleteMapping("/{id}")
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
