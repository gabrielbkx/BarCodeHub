package com.gabrielbkx.BarCodeHub.repository;

import com.gabrielbkx.BarCodeHub.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
}
