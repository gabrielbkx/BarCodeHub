package com.gabrielbkx.BarCodeHub.validator;

import com.gabrielbkx.BarCodeHub.exceptions.RegistroDuplicadoExeption;
import com.gabrielbkx.BarCodeHub.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// Classe responsavel por validar se ja existe uma categoria com o nome x no banco de dados
@Component
@RequiredArgsConstructor
public class CategoriaValidator {
    private final CategoriaRepository repository;

    public void validar(String nome) {
        //retorna uma excessao caso a categoria ja exista com uma mensagem e código 409
        if(existeCategoriaCadastrada(nome)){
            throw new RegistroDuplicadoExeption(" A categoria "  + nome + " já está cadastrada");
        }
    }

    //Método responsável por ir no banco de dados e verificar se ja existe a categoria pelo nome
    private boolean existeCategoriaCadastrada(String nome) {
        String nomeUpperCase = nome.toUpperCase();
        return repository.existsByNome(nomeUpperCase);
    }
}
