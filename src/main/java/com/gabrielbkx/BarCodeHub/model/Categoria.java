package com.gabrielbkx.BarCodeHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome",nullable = false,length = 50,unique = true)
    private String nome;

    @OneToMany(mappedBy = "categoria",cascade = CascadeType.ALL,orphanRemoval = false)
    private List<Produto> produtos = new ArrayList<Produto>();

    public void setNome(String nome) {
        this.nome = nome.toUpperCase(); // Converte para uppercase antes de salvar
    }
}