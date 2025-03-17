package com.gabrielbkx.BarCodeHub.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoDTO(String nome, Integer quantidade,
                         BigDecimal preco, String descricao,
                         String codigoInterno,
                         String referencia, UUID fornecedor,
                         UUID categoria) {
}
