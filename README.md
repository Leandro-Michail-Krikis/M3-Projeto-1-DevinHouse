# M3-Projeto-1-DevinHouse

Essa API é um sistema de gestão de médico desenvolvido para gestão hospitalar. Este software tem como objetivo otimizar o processo de gerenciamento

- [Tecnologias](#tech)
- [Como Iniciar](#start)
- [Qual o problema ele resolve](#why)
- [Melhorias](#melhorias)
- [Como usar](#usar)
  <a id="tech"></a>

Este projeto faz parte dos projetos de avaliação do curso DEVInHouse.


# Tecn

O projeto desenvolvido utiliza as seguintes tecnologias:
- [Java](https://www.java.com/en/)
- [Maven](https://maven.apache.org/)
- [Spring](https://spring.io/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring WEB](https://spring.io/guides/gs/serving-web-content/)
- [MapStruct](https://github.com/mapstruct/mapstruct)
- [Lombok](https://projectlombok.org/)
- [SpringDoc](https://springdoc.org/)
- [Git e GitHub](https://github.com/)
- [H2-Embedded](https://www.h2database.com/html/main.html)

<a id="start"></a>

# Como Iniciar

#### **Pré-requisitos**

- Possuir o JDK17 e Maven instalados na sua máquina.

```bash
# Clone o Repositório
$ git clone https://github.com/Leandro-Michail-Krikis/M3-Projeto-1-DevinHouse
```

```bash
# Entre na pasta do projeto
$ cd M3-Projeto-1-DevinHouse
```

```bash
# Na pasta raiz execute esse comando para comilar o projeto.
$ mvn clean install
```

```bash
# Entre na pasta target
$ cd target
```

```bash
# Execute o seguinte comando para iniciar o projeto localmente
$ java -jar .\m3p1-0.0.1-SNAPSHOT.jar
```
- Usando Intellij IDEA.
```bash
# Instala lombok no intelliJ
# Abre a pasta como projeto com o intelliJ
# Depois de alguns segundos vai parecer de carregar como projeto Maven. Aceita
# Agora so clicar no canto direito superior no botao de iniciar
```

<a id="usar"></a>
# Como usar
#### Poderia utilizar o swagger acessando esse link.
http://localhost:8080/api/swagger
<a id="why"></a>
# Melhorias Futuras
- Implementação de autenticação e autorização com JWT.
- Adicionar logs e monitoramento da aplicação.

