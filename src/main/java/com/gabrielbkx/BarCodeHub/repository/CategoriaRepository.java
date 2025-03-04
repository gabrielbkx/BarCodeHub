package com.gabrielbkx.BarCodeHub.repository;

import com.gabrielbkx.BarCodeHub.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

        boolean existsByNome(String nome);

}