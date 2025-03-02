package com.gabrielbkx.BarCodeHub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "endereco")
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Endereco {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "logradouro",nullable = false,length = 100)
    private String logradouro;

    @Column(name = "bairro",nullable = false,length = 30)
    private String bairro;

    @Column(name = "localidade",nullable = false,length = 100)
    private String localidade;

    @Column(name = "uf",nullable = false,length = 10)
    private String uf;

    @Column(name = "cep",nullable = false,length = 20)
    private String cep;

    @OneToOne(mappedBy = "endereco") // Relacionamento bidirecional
    private Fornecedor fornecedor;
}
