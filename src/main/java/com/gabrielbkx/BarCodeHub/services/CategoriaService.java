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
    public Categoria criarCategoria(String nome) {
        String nomeUpperCase = nome.toUpperCase(); // Respeita a regra de negocio do nome ser salvo em maiuscula

        // Valida a existência antes de prosseguir
        validador.validar(nomeUpperCase);

        Categoria categoria = new Categoria();
        categoria.setNome(nomeUpperCase);
        return repository.save(categoria);
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
    public void deletarCategoriaPorId(UUID id){

        var categoriaParaDeletar = repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Categoria não encontrada"));

        repository.deleteById(categoriaParaDeletar.getId());
    }

    public void atualizar(UUID id, String nome) throws RegistroNaoEncontradoException {
        Categoria categoriaQueExiste = buscarPorId(id);
        validador.validar(nome);
        categoriaQueExiste.setNome(nome);
        repository.save(categoriaQueExiste);
    }
}
