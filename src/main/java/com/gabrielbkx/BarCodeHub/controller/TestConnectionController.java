package com.gabrielbkx.BarCodeHub.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@AllArgsConstructor
public class TestConnectionController {

    private DataSource dataSource;

    @GetMapping("/test-connection")
    public String testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "Conexão bem-sucedida com o banco de dados!";
        } catch (SQLException e) {
            return "Erro ao conectar: " + e.getMessage();
        }
    }
}
