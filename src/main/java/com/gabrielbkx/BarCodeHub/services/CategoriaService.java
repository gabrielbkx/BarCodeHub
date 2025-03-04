package com.gabrielbkx.BarCodeHub.services;

import com.gabrielbkx.BarCodeHub.exceptions.RegistroNaoEncontradoException;
import com.gabrielbkx.BarCodeHub.model.Categoria;
import com.gabrielbkx.BarCodeHub.repository.CategoriaRepository;
import com.gabrielbkx.BarCodeHub.validator.CategoriaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaValidator validador;

    //Salva uma nova categoria nobanco de dados
    public Categoria criarCategoria(Categoria categoria) {
      validador.validarCategoria(categoria);
        return repository.save(categoria);   // Salva a categoria e retorna o objeto persistido
    }

    //Retorna uma lista de todas as categorias que existem no banco de dados
    public List<Categoria> listarCategorias() {
        return repository.findAll();
    }

    public Categoria buscarPorId(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException(id.toString()));
    }

    // Deleta uma categoria pelo id dela usando um Optional
    @Transactional // Notação que da commits e rollbacks automaticos no banco de dados
    public void deletarCategoriaPorId(UUID id){
        var categoriaParaDeletar = repository.findById(id).orElseThrow( // Caso op id nao exista nos retorna uma exceção
                () -> new RegistroNaoEncontradoException("Categoria não encontrada"));
        repository.deleteById(categoriaParaDeletar.getId());
    }

    @Transactional // Notação que da commits e rollbacks automaticos no banco de dados
    public void atualizar(Categoria categoria) {

        // Caso o id da categoria nao exista, lança uma exceção
        if (categoria.getId() == null){

            throw new IllegalArgumentException("Para atualizar, é necessário" +
                    " que o autor ja esteja salvo na base de dados");
        }

        validador.validarCategoria(categoria);
        repository.save(categoria);
    }

}
