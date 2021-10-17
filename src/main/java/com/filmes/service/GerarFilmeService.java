package com.filmes.service;

import com.filmes.domain.Filme;


public interface GerarFilmeService {
    Filme gerarFilme(String genero, String nota);
}
