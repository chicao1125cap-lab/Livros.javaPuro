package com.exemplo.livros;

import com.exemplo.livros.http.LivroHttpServer;
import com.exemplo.livros.repository.LivroRepository;
import com.exemplo.livros.service.LivroService;

public class Main {

    public static void main(String[] args) throws Exception {

        // 1. Criamos o acesso ao banco.
        LivroRepository repository = new LivroRepository();

        // 2. Criamos o Service, que contém as regras.
        LivroService service = new LivroService(repository);

        // 3. Criamos nosso servidor HTTP usando Java puro.
        LivroHttpServer server = new LivroHttpServer(service);

        // 4. Iniciamos o servidor na porta 8080.
        server.iniciar();

        System.out.println("======================================");
        System.out.println(" Sistema de Livros - Java puro");
        System.out.println(" http://localhost:8080");
        System.out.println("======================================");
    }
}
