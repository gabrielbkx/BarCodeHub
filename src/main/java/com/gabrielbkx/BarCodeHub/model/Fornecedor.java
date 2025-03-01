package com.gabrielbkx.BarCodeHub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fornecedor")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Fornecedor {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false,length = 50)
    private String nome;

    @Column(name = "cnpj", nullable = false,length = 15)
    private String cnpj;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "endereco")
    private Endereco endereco;

    private List<Produto> produtos;
}
