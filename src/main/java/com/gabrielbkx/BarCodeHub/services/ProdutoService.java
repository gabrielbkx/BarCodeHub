package com.gabrielbkx.BarCodeHub.services;

import com.gabrielbkx.BarCodeHub.exceptions.RegistroNaoEncontradoException;
import com.gabrielbkx.BarCodeHub.model.Fornecedor;
import com.gabrielbkx.BarCodeHub.model.Produto;
import com.gabrielbkx.BarCodeHub.repository.ProdutoRepository;
import com.gabrielbkx.BarCodeHub.validator.ProdutoValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ProdutoService {

    private final FornecedorService fornecedorService;
    private final CategoriaService categoriaService;
    private final ProdutoValidator validator;
    private final ProdutoRepository repository;


    public Produto criarProduto(String nome, Integer quantidade,
                                BigDecimal preco, String descricao,
                                String codigoInterno,
                                String referencia, UUID fornecedor,
                                UUID categoria)
    {

        log.info("Validando produto pelo seu codigo");
        validator.validar(codigoInterno); // Valida a existência do produto pelo codigo interno antes de prosseguir

        log.info("Criando novo produto usando o padrão DTO");
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produto.setDescricao(descricao);
        produto.setCodigoInterno(codigoInterno);
        produto.setReferencia(referencia);
        produto.getFornecedores().add(fornecedorService.buscar(fornecedor));
        produto.setCategoria(categoriaService.buscarPorId(categoria));

        log.info("Salvando produto usando repository");
        return repository.save(produto);
    }


    public void atualizar(UUID id, String nome, Integer quantidade,
                          BigDecimal preco, String descricao,
                          String codigoInterno,
                          String referencia, UUID fornecedor,
                          UUID categoria) {

        Produto produtoQueExiste = buscarPorId(id);
        validator.validar(codigoInterno);

        produtoQueExiste.setNome(nome);
        produtoQueExiste.setQuantidade(quantidade);
        produtoQueExiste.setPreco(preco);
        produtoQueExiste.setDescricao(descricao);
        produtoQueExiste.setCodigoInterno(codigoInterno);
        produtoQueExiste.setReferencia(referencia);
        produtoQueExiste.getFornecedores().add(fornecedorService.buscar(fornecedor));
        produtoQueExiste.setCategoria(categoriaService.buscarPorId(categoria));
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
