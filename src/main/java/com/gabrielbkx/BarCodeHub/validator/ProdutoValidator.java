package com.gabrielbkx.BarCodeHub.validator;

import com.gabrielbkx.BarCodeHub.exceptions.RegistroDuplicadoExeption;
import com.gabrielbkx.BarCodeHub.repository.CategoriaRepository;
import com.gabrielbkx.BarCodeHub.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// Classe responsavel por validar se ja existe uma categoria com o nome x no banco de dados
@Component
@RequiredArgsConstructor
public class ProdutoValidator {
    private final ProdutoRepository repository;

    public void validar(String codigo) {
        //retorna uma excessao caso o produto ja exista com uma mensagem e código 409
        if(existeProdutoCadastrado(codigo)){
            throw new RegistroDuplicadoExeption(" O produto com o código "  + codigo + " já está cadastrado");
        }
    }

    //Método responsável por ir no banco de dados e verificar se ja existe a categoria pelo nome
    private boolean existeProdutoCadastrado(String codigo) {
        String nomeUpperCase = codigo.toUpperCase();
        return repository.existsByNome(nomeUpperCase);
    }
}
