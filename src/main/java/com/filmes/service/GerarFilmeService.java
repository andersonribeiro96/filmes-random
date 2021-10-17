package com.filmes.service;

import com.filmes.domain.Filme;
import com.filmes.domain.IMBD;


public interface GerarFilmeService {
    Filme gerarFilme(String genero, String nota);
}
