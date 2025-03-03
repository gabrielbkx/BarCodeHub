package com.gabrielbkx.BarCodeHub.services;

import com.gabrielbkx.BarCodeHub.model.Categoria;
import com.gabrielbkx.BarCodeHub.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria criarCategoria(Categoria categoria) {
        // Salva a categoria e retorna o objeto persistido
        return repository.save(categoria);
    }

}
