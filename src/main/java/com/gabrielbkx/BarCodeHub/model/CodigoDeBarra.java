package com.gabrielbkx.BarCodeHub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "codigo_de_barras")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CodigoDeBarra {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "codigo", nullable = false,length = 13)
    private String codigo;

    @Column(name = )
    private Produto produto;
}
