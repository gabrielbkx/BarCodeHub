package com.gabrielbkx.BarCodeHub.services;

import com.gabrielbkx.BarCodeHub.exceptions.RegistroDuplicadoExeption;
import com.gabrielbkx.BarCodeHub.exceptions.RegistroNaoEncontradoException;
import com.gabrielbkx.BarCodeHub.model.Fornecedor;
import com.gabrielbkx.BarCodeHub.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository repository;


    public Fornecedor cadastrar(String nome,String cnpj) {

        // Não permite registra um fornecedor cujo nome ja existe no banco de dados
        repository.existsByNome(nome.toUpperCase()).orElseThrow( () -> new
                RegistroDuplicadoExeption("Esse fornecedor ja esta cadastrado"));

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(nome.toUpperCase());
        fornecedor.setCnpj(cnpj);
       return repository.save(fornecedor);
    }

    public List<Fornecedor> listar() {
        return repository.findAll();
    }

    public Fornecedor buscar(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException(id.toString()));
    }

    public Fornecedor buscarPeloNome(String nome) {
            return repository.findByNome(nome).orElseThrow(
                    ( () -> new RegistroNaoEncontradoException("Fornecedor nao encontrado"))
            );
    }


    public void deletar(UUID id){

        var fornecedorParaDeletar = repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Fornecedor não encontrado"));

        repository.deleteById(fornecedorParaDeletar.getId());
    }

    public void atualizar(UUID id, String nome,String cnpj)  {
        Fornecedor fornecedorJaExiste = buscar(id);
        fornecedorJaExiste.setNome(nome);
        fornecedorJaExiste.setCnpj(cnpj);
        repository.save(fornecedorJaExiste);
    }
}
