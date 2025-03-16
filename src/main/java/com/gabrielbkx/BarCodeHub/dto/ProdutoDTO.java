package com.gabrielbkx.BarCodeHub.dto;

import com.gabrielbkx.BarCodeHub.model.Categoria;
import com.gabrielbkx.BarCodeHub.model.Fornecedor;
import java.math.BigDecimal;

public record ProdutoDTO(String nome, Integer quantidade,
                         BigDecimal preco, String descricao,
                         String codigoInterno,
                         String referencia, Fornecedor fornecedor,
                         Categoria categoria) {
}
