package com.exemplo.livros.service;

import com.exemplo.livros.model.Livro;
import com.exemplo.livros.repository.LivroRepository;

import java.sql.SQLException;
import java.util.List;

public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro cadastrar(Livro livro) throws SQLException {

        validar(livro);

        // Aqui o objeto já existe.
        // O Service decide que ele pode ser salvo.
        return repository.salvar(livro);
    }

    public List<Livro> listar() throws SQLException {
        return repository.listar();
    }

    private void validar(Livro livro) {

        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório.");
        }

        if (livro.getAutor() == null || livro.getAutor().isBlank()) {
            throw new IllegalArgumentException("Autor é obrigatório.");
        }

        if (livro.getAno() == null || livro.getAno() < 0) {
            throw new IllegalArgumentException("Ano inválido.");
        }
    }
}
