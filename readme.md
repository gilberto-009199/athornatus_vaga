
[![Testar em Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy) [![Testar no Browser](https://raw.githubusercontent.com/gilberto-009199/JAgendaWeb/master/gitpod.svg)](https://gitpod.io#https://github.com/gilberto-009199/athornatus_vaga)

# Desafio Java

[![Java Tests](https://github.com/gilberto-009199/athornatus_vaga/actions/workflows/test.yml/badge.svg)](https://github.com/gilberto-009199/athornatus_vaga/actions/workflows/test.yml)

Usando Spring boot, crie uma API simples para gerenciar Pessoas. Esta API deve permitir:
+ Criar uma pessoa
+ Editar uma pessoa
+ Consultar uma pessoa
+ Listar pessoas
+ Criar endereço para pessoa
+ Listar endereços da pessoa
+ Poder informar qual endereço é o principal da pessoa  

Uma Pessoa deve ter os seguintes campos:
+ Nome
+ Data de nascimento
+ Endereço:
    + Logradouro
    + CEP
    + Número
    + Cidade

## Execução
 Há duas formas de subir o projeto: instalando direto em sua máquina ou subindo em containeres Docker.
 
 ### Maven
```
 mvn install
 cd target
 java -jar clientes-0.0.1-SNAPSHOT.jar
```

### Docker
```
 docker build --no-cache --tag=myserver:latest .
 docker run --rm -it -p 8080:8080 myserver:latest
```

## Bibliotecas

* [SpringBoot](https://spring.io) - Plataforma para desenvolvimento web em java
* [Swagger](https://swagger.io/) - Biblioteca para documentação de API
* [Lombok](https://projectlombok.org) - Biblioteca para agilizar a codificação em Java
* [H2](https://www.h2database.com) - Sistema de gerenciamento de banco de dados relacional escrito em Java.
* [ModelMapper](https://modelmapper.org/) - Biblioteca para agilizar o mapeamento de objetos(DTO, Entity, Request, Response)

## Links

* [Documentacao swagger JSON](https://raw.githubusercontent.com/gilberto-009199/athornatus_vaga/main/api-docs.json) - Arquivo de Documentacao em JSON
* [Documentacao swagger YAML](https://raw.githubusercontent.com/gilberto-009199/athornatus_vaga/main/api-docs.yaml) - Arquivo de Documentacao em YAML
 
![Documentacao swagger](./swagger-ui-index-html.png)

