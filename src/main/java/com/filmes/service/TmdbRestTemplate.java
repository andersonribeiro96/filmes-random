package com.filmes.service;

import com.filmes.domain.Resultado;
import com.filmes.domain.ProviderWatch;

import java.util.List;

public interface TmdbRestTemplate {

    List<Resultado> obterFilmes(String genero, String total);
    List<Resultado> obterFilmes(String genero, Long ano);
    ProviderWatch obterProviderWatch(String id);
}
