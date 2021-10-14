package com.filmes.service;

import com.filmes.domain.Filme;


public interface GerarFilmeService {
    Filme gerarFilme(String genero, String total);
    Filme gerarFilme(String genero, Long ano);
}
