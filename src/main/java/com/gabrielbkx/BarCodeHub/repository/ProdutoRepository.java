package com.gabrielbkx.BarCodeHub.repository;

import com.gabrielbkx.BarCodeHub.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    boolean existsByNome(String nome);
    Optional<Produto> findByNome(String nome);
    Optional<Produto> findByCodigoInterno(String codigoInterno);
    Optional<Produto> findByReferencia(String referencia);

}
