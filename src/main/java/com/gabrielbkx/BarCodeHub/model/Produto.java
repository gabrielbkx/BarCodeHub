package com.gabrielbkx.BarCodeHub.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "produto")
@Data
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Produto {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome",nullable = false,length = 100)
    private String nome;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco",nullable = false,precision = 18,scale = 2)
    private BigDecimal preco;

    @Column(name = "descricao",length = 300)
    private String descricao;

    @Column(name = "codigo_interno",nullable = false,length = 15)
    private String codigoInterno;

    @Column(name = "referencia",length = 10)
    private String referencia;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime data_cadastro;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime data_atualizacao;

    @ManyToMany
    @JoinTable(
            name = "produto_fornecedor",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    private List<Fornecedor> fornecedores = new ArrayList<>();

    @OneToMany(mappedBy = "produto")
    private List<CodigoDeBarra> codigosDeBarras = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "categoria_id",nullable = true)
    private Categoria categoria;
}
