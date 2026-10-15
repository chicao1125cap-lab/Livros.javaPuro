# 📚 Sistema de Gerenciamento de Livros — Spring Boot

Uma aplicação desenvolvida em Java para praticar o desenvolvimento de APIs REST, a organização em camadas e a integração com banco de dados utilizando Spring Boot.

Este projeto nasceu da vontade de entender melhor como uma aplicação backend funciona na prática, desde uma requisição HTTP até a persistência de informações no banco de dados.

## 💡 Sobre o projeto

Este projeto faz parte de um processo de aprendizado.

Antes de trabalhar com Spring Boot, desenvolvi uma versão do Sistema de Livros utilizando Java puro, `HttpServer`, Jackson e JDBC. Naquela implementação, boa parte da infraestrutura precisava ser configurada e executada manualmente.

A experiência levantou uma questão interessante: **o que muda quando utilizamos um framework para desenvolver a mesma aplicação?**

Foi a partir dessa pergunta que surgiu a ideia de desenvolver esta versão com Spring Boot.

O objetivo não era apenas aprender a utilizar um framework popular no mercado, mas comparar as duas abordagens, entender quais responsabilidades passam a ser gerenciadas pelo Spring e perceber como essas abstrações facilitam o desenvolvimento.

Em vez de enxergar o Spring Boot apenas como uma ferramenta que reduz a quantidade de código, a proposta foi compreender como ele ajuda a organizar e conectar os diferentes componentes de uma aplicação.

## 🎯 Objetivos de aprendizado

Durante o desenvolvimento, o foco está em colocar em prática conceitos importantes do desenvolvimento backend:

- **Spring Boot:** configuração e inicialização da aplicação.
- **API REST:** comunicação entre o cliente e o servidor por meio de requisições HTTP.
- **Controllers:** recebimento das requisições e retorno das respostas HTTP.
- **Injeção de dependências:** integração entre os componentes da aplicação sem precisar instanciar manualmente cada dependência.
- **DTOs e JSON:** representação e transferência de dados entre o cliente e a API, conforme implementados no projeto.
- **Camada de serviço:** organização das regras de negócio.
- **Persistência de dados:** integração com o banco de dados por meio da abordagem de acesso a dados utilizada no projeto.
- **Organização em camadas:** separação de responsabilidades para facilitar a manutenção e a evolução do sistema.

A intenção é compreender não apenas como implementar cada recurso, mas também por que ele existe e qual problema resolve.

## ⚙️ Como a aplicação funciona?

De forma simplificada, o fluxo de uma operação segue esta lógica:

```text
USUÁRIO
   |
   | preenche o formulário
   v
FRONT-END
HTML + JavaScript
   |
   | requisição HTTP + JSON
   v
CONTROLLER
   |
   | recebe e interpreta os dados
   v
SERVICE
   |
   | executa as regras de negócio
   v
REPOSITORY
   |
   | acesso aos dados
   v
BANCO DE DADOS
```

Cada camada possui uma responsabilidade específica. Isso ajuda a evitar que toda a lógica da aplicação fique concentrada em uma única classe.

Por exemplo, ao cadastrar um livro, o cliente envia os dados para a API. O Controller recebe a requisição, o Service coordena a operação e o Repository realiza o acesso aos dados.

O Spring Boot, junto com os recursos do ecossistema Spring utilizados na aplicação, facilita a integração entre essas partes.

## 🔍 O que muda em relação à versão com Java puro?

A principal diferença está na forma como a infraestrutura da aplicação é construída.

Na versão sem Spring Boot, foi necessário trabalhar diretamente com recursos como:

- `HttpServer` para receber requisições HTTP;
- leitura manual do corpo da requisição;
- conversão de JSON para objetos Java;
- configuração manual de respostas HTTP;
- `DriverManager`, `PreparedStatement` e `ResultSet` para operações com JDBC.

Nesta versão, o objetivo é utilizar os recursos do Spring para reduzir esse trabalho manual.

| Java puro | Spring Boot |
|---|---|
| Registro manual de rotas HTTP | Mapeamento de rotas com anotações como `@GetMapping` e `@PostMapping` |
| Leitura manual do corpo HTTP | Desserialização automática com `@RequestBody`, quando aplicável |
| Integração manual entre componentes | Injeção de dependências |
| Tratamento manual de respostas HTTP | Recursos do Spring MVC para construir respostas |
| Acesso a dados implementado diretamente | Abstrações de persistência, conforme a tecnologia adotada |

Essas diferenças não significam que o Spring elimina o funcionamento interno dessas operações. Ele oferece abstrações que permitem trabalhar em um nível mais alto, deixando boa parte da infraestrutura sob responsabilidade do framework.

## 🧠 Por que desenvolver as duas versões?

Porque aprender uma ferramenta é diferente de compreender o problema que ela resolve.

A versão com Java puro ajudou a enxergar as etapas envolvidas no processamento de uma requisição, na conversão de dados e na comunicação com o banco.

A versão com Spring Boot permite trabalhar com abstrações que tornam esse processo mais organizado e produtivo.

Comparar as duas abordagens ajuda a entender melhor as decisões tomadas durante o desenvolvimento e a utilizar o framework com mais consciência.

A proposta não é escolher uma abordagem como universalmente melhor, mas compreender quando cada recurso faz sentido e como os fundamentos continuam presentes mesmo quando parte da complexidade fica escondida.

## 🛠️ Tecnologias

A aplicação utiliza Java e Spring Boot, além das tecnologias de comunicação, persistência e interface que fazem parte da implementação.

- Java
- Spring Boot
- Maven
- API REST
- HTTP e JSON
- HTML e JavaScript
- Banco de dados e tecnologia de persistência utilizados no projeto

> As tecnologias específicas de persistência, como H2, JDBC ou Spring Data JPA, devem ser detalhadas conforme a configuração real desta versão.

## 🚀 Como executar

### Pré-requisitos

- JDK compatível com a versão do projeto;
- Maven, caso não esteja utilizando o Maven Wrapper;
- IDE de sua preferência, como IntelliJ IDEA.

### Executando pelo terminal

Na pasta raiz do projeto, execute:

```bash
mvn spring-boot:run
```

Se o projeto possuir o Maven Wrapper, também é possível executar:

**Windows:**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux/macOS:**

```bash
./mvnw spring-boot:run
```

Após a inicialização, verifique no console a porta configurada pela aplicação. Se estiver utilizando a porta padrão `8080`, acesse:

```text
http://localhost:8080
```

A disponibilidade da interface e das rotas depende da configuração atual do projeto.

## 📈 Próximos passos

Este projeto representa mais uma etapa do meu aprendizado em Java e desenvolvimento backend.

A partir dele, a ideia é continuar aprofundando os conhecimentos em APIs REST, persistência de dados, validação, tratamento de erros, testes automatizados e boas práticas de arquitetura.

Também pretendo utilizar essa base para desenvolver aplicações mais completas, aproximando cada vez mais os estudos de situações encontradas no desenvolvimento profissional.

## 👨‍💻 Sobre mim


Procuro entender não apenas como construir uma aplicação, mas também como suas partes se comunicam, quais responsabilidades cada componente deve assumir e por que determinadas decisões técnicas são tomadas.

Este projeto faz parte dessa evolução: sair da implementação manual dos fundamentos e avançar para o uso consciente de ferramentas utilizadas no mercado.

---

**Aprender a usar a ferramenta é importante. Entender o que ela faz por nós é o próximo passo.** 🚀
