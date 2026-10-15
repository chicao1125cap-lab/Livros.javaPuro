package com.exemplo.livros.http;

import com.exemplo.livros.json.JsonUtil;
import com.exemplo.livros.model.Livro;
import com.exemplo.livros.service.LivroService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class LivroHttpServer {

    private final LivroService service;
    private HttpServer server;

    public LivroHttpServer(LivroService service) {
        this.service = service;
    }

    public void iniciar() throws IOException {

        server = HttpServer.create(
                new InetSocketAddress(8080),
                0
        );

        // Equivale, de forma conceitual, ao endpoint:
        // POST /api/livros
        // GET  /api/livros
        server.createContext("/api/livros", this::tratarLivros);

        // Serve o index.html quando o navegador pedir "/".
        server.createContext("/", this::servirFrontEnd);

        server.start();
    }

    private void tratarLivros(HttpExchange exchange) throws IOException {

        try {

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                cadastrar(exchange);
                return;
            }

            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                listar(exchange);
                return;
            }

            responder(exchange, 405, "{\"erro\":\"Método não permitido.\"}");

        } catch (IllegalArgumentException e) {

            responder(
                    exchange,
                    400,
                    "{\"erro\":\"" + escapar(e.getMessage()) + "\"}"
            );

        } catch (SQLException e) {

            e.printStackTrace();

            responder(
                    exchange,
                    500,
                    "{\"erro\":\"Erro no banco de dados.\"}"
            );
        }
    }

    private void cadastrar(HttpExchange exchange)
            throws IOException, SQLException {

        // 1. Recebe o corpo da requisição.
        String jsonRecebido = lerCorpo(exchange);

        System.out.println("\nJSON recebido do Front-end:");
        System.out.println(jsonRecebido);

        // 2. JSON -> objeto Java.
        Livro livro = JsonUtil.fromJson(jsonRecebido, Livro.class);

        System.out.println("Objeto Java criado:");
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("Ano: " + livro.getAno());

        // 3. Service -> Repository -> banco.
        Livro salvo = service.cadastrar(livro);

        // 4. Objeto Java -> JSON.
        String respostaJson = JsonUtil.toJson(salvo);

        responder(exchange, 201, respostaJson);
    }

    private void listar(HttpExchange exchange)
            throws IOException, SQLException {

        List<Livro> livros = service.listar();

        String respostaJson = JsonUtil.toJson(livros);

        responder(exchange, 200, respostaJson);
    }

    private void servirFrontEnd(HttpExchange exchange) throws IOException {

        if (!"/".equals(exchange.getRequestURI().getPath())) {
            responder(exchange, 404, "Página não encontrada.");
            return;
        }

        try (InputStream input =
                     getClass().getResourceAsStream("/index.html")) {

            if (input == null) {
                responder(exchange, 404, "index.html não encontrado.");
                return;
            }

            byte[] conteudo = input.readAllBytes();

            exchange.getResponseHeaders()
                    .set("Content-Type", "text/html; charset=UTF-8");

            exchange.sendResponseHeaders(200, conteudo.length);

            exchange.getResponseBody().write(conteudo);
            exchange.close();
        }
    }

    private String lerCorpo(HttpExchange exchange) throws IOException {

        try (InputStream input = exchange.getRequestBody()) {
            return new String(
                    input.readAllBytes(),
                    StandardCharsets.UTF_8
            );
        }
    }

    private void responder(
            HttpExchange exchange,
            int status,
            String corpo
    ) throws IOException {

        byte[] resposta = corpo.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders()
                .set("Content-Type", "application/json; charset=UTF-8");

        exchange.sendResponseHeaders(status, resposta.length);

        exchange.getResponseBody().write(resposta);
        exchange.close();
    }

    private String escapar(String texto) {

        if (texto == null) {
            return "";
        }

        return texto
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}
