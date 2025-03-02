package com.gabrielbkx.BarCodeHub.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "produto_fornecedor")
public class ProdutoFornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;
}