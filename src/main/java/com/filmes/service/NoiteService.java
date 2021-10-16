package com.filmes.service;

import com.filmes.domain.Noite;

public interface NoiteService {

    Noite gerarNoite(String genero, String ano, String nota, String votos) throws Exception;

}
