package com.gabrielbkx.BarCodeHub.validator;

import com.gabrielbkx.BarCodeHub.exceptions.RegistroDuplicadoExeption;
import com.gabrielbkx.BarCodeHub.model.Categoria;
import com.gabrielbkx.BarCodeHub.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// Classe responsavel por validar se ja existe uma categoria com o nome x no banco de dados

@Component
@RequiredArgsConstructor
public class CategoriaValidator {

    private final CategoriaRepository repository;

    // Método responsável por lançar a exeçao RegistroDuplicadoExeption caso a categoria ja exista

    public void validarCategoria(String nome) {
        if(existeCategoriaCadastrada(nome)){
            throw new RegistroDuplicadoExeption(" Essa categoria ja esta cadastrada!");
        }
    }

    //Método responsável por ir no banco de dados e verificar se ja existe a categoria pelo nome

    public boolean existeCategoriaCadastrada(String nome) {
        String nomeUpperCase = nome.toUpperCase();

        Categoria categoria = new Categoria();
        categoria.setNome(nomeUpperCase);
        return repository.existsByNome(categoria.getNome());
    }
}
