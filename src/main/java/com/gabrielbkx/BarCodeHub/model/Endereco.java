package com.gabrielbkx.BarCodeHub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "endereco")
@Entity
@Data
@NoArgsConstructor
public class Endereco {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "cep",nullable = false,length = 20)
    private String cep;

    @Column(name = "logradouro",nullable = false,length = 100)
    private String logradouro;

    @Column(name = "complemento",length = 50)
    private String complemento;

    @Column(name = "bairro",nullable = false,length = 30)
    private String bairro;

    @Column(name = "localidade",nullable = false,length = 100)
    private String localidade;

    @Column(name = "uf",nullable = false,length = 10)
    private String uf;

    @Column(name = "estado",length = 50)
    private String estado;

    @OneToOne(mappedBy = "endereco") // Relacionamento bidirecional
    private Fornecedor fornecedor;
}
