# Comparação direta — com e sem Spring Boot

## Receber JSON

### Sem Spring

```java
String jsonRecebido = lerCorpo(exchange);

Livro livro =
    JsonUtil.fromJson(jsonRecebido, Livro.class);
```

### Com Spring

```java
@PostMapping
public Livro cadastrar(@RequestBody Livro livro) {
    ...
}
```

O Spring assume uma grande parte do trabalho.

---

## Servidor HTTP

### Sem Spring

```java
HttpServer server =
    HttpServer.create(
        new InetSocketAddress(8080),
        0
    );
```

### Com Spring

```java
@SpringBootApplication
```

e o Spring Boot inicializa o servidor embutido.

---

## Banco

### Sem Spring

```java
Connection connection =
    DriverManager.getConnection(...);

PreparedStatement statement =
    connection.prepareStatement(sql);
```

### Com Spring Data JPA

```java
livroRepository.save(livro);
```

---

# A conclusão

O sistema sem Spring é perfeitamente possível.

A grande diferença é a quantidade de infraestrutura que você precisa escrever e controlar.

Por isso é importante entender os dois níveis:

```text
Java / HTTP / JSON / JDBC
          ↓
      fundamentos
          ↓
      Spring Boot
          ↓
      abstrações
```

Aprender Spring sem entender Java pode virar decoração de anotações.

Entender Java primeiro faz as abstrações do Spring ficarem muito mais fáceis de compreender.
