# Micro Service - Cadastro de Pedidos


### Sobre o Projeto
API Rest de exemplo para cadastrar e listar pedidos

## Tecnologias

- Java JDK 22     -> https://www.oracle.com/java/technologies/javase/jdk22-archive-downloads.html
- Maven           -> https://maven.apache.org/
- SpringBoot      -> https://spring.io/projects/spring-boot
- SpringData      -> https://spring.io/projects/spring-data
- Lombok          -> https://projectlombok.org/
- Hibernate       -> https://hibernate.org/
- MySql           -> https://www.mysql.com/


## API


```http
  POST api/v1/pedidos/json
```

| Body                                                                                                                                        | Tipo    | Descrição                             |
|:--------------------------------------------------------------------------------------------------------------------------------------------|:--------|:--------------------------------------|
| `[{ "numeroControle": 17486, "dataCadastro": "2024-06-15", "nome": "Produto Exemplo", "valor": 1000,"quantidade": 10,"codigoCliente": 5}] ` | `JSON` | Realiza o cadastro do pedido via JSON |


```bash
curl --location 'http://localhost:8080/api/v1/pedidos/json' \
--header 'Content-Type: application/json' \
--data '[
    {
        "numeroControle": 17486,
        "dataCadastro": null,
        "nome": "Produto Exemplo",
        "valor": 1178.75,
        "quantidade": 5,
        "codigoCliente": 5
    },
    {
        "numeroControle": 121,
        "dataCadastro": "2024-06-15",
        "nome": "Produto Exemplo",
        "valor": 1000,
        "quantidade": 10,
        "codigoCliente": 5
    },
  
]'
```
<br>

```http
  POST api/v1/pedidos/xml
```

| Body                                                                                                                                                                                                                               | Tipo  | Descrição                            |
|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:------|:-------------------------------------|
| `<List><pedido>  <numeroControle>9787</numeroControle> <dataCadastro>2024-06-15</dataCadastro>  <nome>Produto Exemplo</nome>  <valor>45.75</valor> <quantidade>10</quantidade> <codigoCliente>9</codigoCliente></pedido> </List> ` | `XML` | Realiza o cadastro do pedido via XML |

```bash
curl --location 'http://localhost:8080/api/v1/pedidos/xml' \
--header 'Content-Type: application/xml' \
--data '<List>
    <pedido>
        <numeroControle>89</numeroControle>
        <dataCadastro></dataCadastro>
        <nome>Produto Exemplo</nome>
        <valor>978.21</valor>
        <quantidade>2</quantidade>
        <codigoCliente>7</codigoCliente>
    </pedido>
     <pedido>
        <numeroControle>9787</numeroControle>
        <dataCadastro>2024-06-15</dataCadastro>
        <nome>Produto Exemplo</nome>
        <valor>45.75</valor>
        <quantidade>10</quantidade>
        <codigoCliente>9</codigoCliente>
    </pedido>

</List>'
```
<br>

```http
  GET /api/v1/pedidos/listar
```

| Parametro            | Tipo    | Descrição                        |
|:---------------------|:--------|:---------------------------------|
| `numeroControle`        | `int `  | Pesquisa pelo Número de Controle |
| `dataCadastro`          | `date ` | Pesquisa pela data de Cadastro   |

``` bash
curl --location 'http://localhost:8080/api/v1/pedidos/listar?numeroControle=188&dataCadastro=2024-06-18'
```
<br>

### / Script BD

``` bash
CREATE DATABASE `cadpedidos` 

CREATE TABLE `pedido` (
  `ID_PEDIDOS` int NOT NULL AUTO_INCREMENT,
  `NUMERO_CONTROLE` int NOT NULL,
  `NOME` varchar(255) NOT NULL,
  `DATA_CADASTRO` date DEFAULT NULL,
  `VALOR` decimal(8,2) NOT NULL,
  `QUANTIDADE` int DEFAULT NULL,
  `COD_CLIENTE` int NOT NULL,
  `VALOR_TOTAL` decimal(8,2) NOT NULL,
  PRIMARY KEY (`ID_PEDIDOS`),
  UNIQUE KEY `NUMERO_CONTROLE_UNIQUE` (`NUMERO_CONTROLE`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


```


## Author


[@leokashmir](https://www.github.com/leokashmir)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/leokashmir/)
