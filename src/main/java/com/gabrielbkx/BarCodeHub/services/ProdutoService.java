package com.gabrielbkx.BarCodeHub.services;

import com.gabrielbkx.BarCodeHub.exceptions.RegistroNaoEncontradoException;
import com.gabrielbkx.BarCodeHub.model.Categoria;
import com.gabrielbkx.BarCodeHub.model.Fornecedor;
import com.gabrielbkx.BarCodeHub.model.Produto;
import com.gabrielbkx.BarCodeHub.repository.ProdutoRepository;
import com.gabrielbkx.BarCodeHub.validator.ProdutoValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ProdutoService {

    private FornecedorService fornecedorService;
    private final ProdutoValidator validator;
    private final ProdutoRepository repository;


    public Produto criarProduto(String nome, Integer quantidade,
                                BigDecimal preco, String descricao,
                                String codigoInterno,
                                String referencia, Fornecedor fornecedor,
                                Categoria categoria)
    {
        String nomeUpperCase = nome.toUpperCase();

        validator.validar(codigoInterno); // Valida a existência do produto pelo codigo interno antes de prosseguir

        Produto produto = new Produto();
        produto.setNome(nomeUpperCase);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produto.setDescricao(descricao);
        produto.setCodigoInterno(codigoInterno);
        produto.setReferencia(referencia);
        produto.getFornecedores().add(fornecedorService.buscarPeloNome(fornecedor.getNome()));
        produto.setCategoria(categoria);
        return repository.save(produto);
    }


    public void atualizar(UUID id, String nome, Integer quantidade,
                          BigDecimal preco, String descricao,
                          String codigoInterno,
                          String referencia, Fornecedor fornecedor,
                          Categoria categoria) {

        Produto produtoQueExiste = buscarPorId(id);
        validator.validar(codigoInterno);
        produtoQueExiste.setNome(nome);
        produtoQueExiste.setQuantidade(quantidade);
        produtoQueExiste.setPreco(preco);
        produtoQueExiste.setDescricao(descricao);
        produtoQueExiste.setCodigoInterno(codigoInterno);
        produtoQueExiste.setReferencia(referencia);
        produtoQueExiste.getFornecedores().add(fornecedor);
        produtoQueExiste.setCategoria(categoria);
        repository.save(produtoQueExiste);;
    }

    // Deleta um produto pelo id com um Optional
    public void deletarProduto(UUID id){

        var produto = repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Produto não encontrado"));
        repository.deleteById(produto.getId());
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    // O óbvio
    public Produto buscarPorId(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException(id.toString()));
    }

    // Busca os produtos pelo nome
    public Produto buscarPorNome(String nome) {
        return repository.findByNome(nome).orElseThrow(
                () -> new RegistroNaoEncontradoException("Produto não encontrado"));
    }

    // Busca os produtos pelo codigo interno
    public Produto buscarPorcodigo(String codigoInterno) {
        return repository.findByCodigoInterno(codigoInterno).orElseThrow(
                () -> new RegistroNaoEncontradoException("Produto não encontrado"));
    }

    // Busca os produtos pela referencias do fornecedor
    public Produto buscarPorReferencia(String referencia) {
        return repository.findByCodigoInterno(referencia).orElseThrow(
                () -> new RegistroNaoEncontradoException("Produto não encontrado"));
    }
}
