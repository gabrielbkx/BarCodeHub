package com.gabrielbkx.BarCodeHub.services;

import com.gabrielbkx.BarCodeHub.exceptions.RegistroDuplicadoExeption;
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
    public Categoria criarCategoria(String nome) {

        // Aqui salvamos a categoria no banco de dados apenas em maiuscula
        String nomeUpperCase = nome.toUpperCase();

        if (validador.existeCategoriaCadastrada(nomeUpperCase)) {
            throw new RegistroDuplicadoExeption("Já existe uma categoria com esse nome.");
        }

        Categoria categoria = new Categoria();
        categoria.setNome(nomeUpperCase);
        return repository.save(categoria);   // Salva a categoria e retorna o objeto
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
    public void atualizar(String nome) {

        String nomeUpperCase = nome.toUpperCase();

        Categoria categoria = new Categoria();
        categoria.setNome(nomeUpperCase);

        // Caso o id da categoria nao exista, lança uma exceção
        if (categoria.getId() == null){

            throw new IllegalArgumentException("Para atualizar, é necessário" +
                    " que o autor ja esteja salvo na base de dados");
        }

        repository.save(categoria);
    }

}
