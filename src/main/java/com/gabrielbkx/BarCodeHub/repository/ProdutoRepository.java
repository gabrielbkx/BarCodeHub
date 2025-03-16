package com.gabrielbkx.BarCodeHub.repository;

import com.gabrielbkx.BarCodeHub.model.Fornecedor;
import com.gabrielbkx.BarCodeHub.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    Optional<Produto> existsByNome(String nome);

}
