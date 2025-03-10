package com.gabrielbkx.BarCodeHub.repository;


import com.gabrielbkx.BarCodeHub.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {

    Optional<Fornecedor> existsByNome(String nome);
}
