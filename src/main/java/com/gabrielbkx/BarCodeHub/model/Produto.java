package com.gabrielbkx.BarCodeHub.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome",nullable = false,length = 100)
    private String nome;

    @Column(name = "descricao",length = 300)
    private String descricao;

    @Column(name = "codigo-interno",nullable = false,length = 15)
    private String codigoInterno;

    @Column(name = "referencia",nullable = true,length = 10)
    private String referencia;

    private List<Fornecedor> fornecedores;

    private List<CodigoDeBarra> codigosDeBarras;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime data_cadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime data_atualizacao;
}
