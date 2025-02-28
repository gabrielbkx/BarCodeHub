# BarCodeHub

**BarCodeHub** é um sistema que permite associar múltiplos códigos de barras a um único produto, cada um de diferentes fornecedores. Ele resolve o problema de duplicação de produtos, otimizando o uso de memória e facilitando a consulta de dados no gerenciamento de estoque.

## 🛠 Tecnologias Utilizadas

- **Spring Boot** - Framework principal.
- **MySQL** - Banco de dados.
- **Docker** - Para criação de containers.

## 📋 Pré-requisitos

- Docker Desktop instalado.
- JDK 11 ou superior.
- Maven ou Gradle.

## 🚀 Como rodar o projeto com Docker

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/BarCodeHub.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd BarCodeHub
    ```

3. Crie e inicie o container MySQL usando Docker Desktop.

4. Configure a conexão com o banco no arquivo `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/barcodehub
    spring.datasource.username=root
    spring.datasource.password=root
    ```

5. Compile e inicie a aplicação:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

6. Acesse a aplicação na URL:
    ```text
    http://localhost:8080
    ```

## 🧑‍💻 Como Contribuir

1. Faça um fork deste repositório.
2. Crie uma branch (`git checkout -b minha-modificacao`).
3. Commit as alterações (`git commit -am 'Adicionando minha modificação'`).
4. Envie para o repositório (`git push origin minha-modificacao`).
5. Abra um Pull Request.

## 📚 Licença

Distribuído sob a licença MIT. Veja [LICENSE](LICENSE) para mais informações.
