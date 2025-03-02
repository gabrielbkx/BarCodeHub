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
public class Fornecedor {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false,length = 50)
    private String nome;

    @Column(name = "cnpj", nullable = false,length = 25)
    private String cnpj;

    @Column(name = "telefone")
    private String telefone;

    @OneToOne
    @JoinColumn(name = "endereco_id") // chave estrangeira que referencia a tabela Endereco
    private Endereco endereco;

    @ManyToMany
    @JoinTable(
            name = "produto_fornecedor",
            joinColumns = @JoinColumn(name = "fornecedor_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

}
