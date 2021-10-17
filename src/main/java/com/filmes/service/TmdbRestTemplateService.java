package com.filmes.service;

import com.filmes.domain.Resultado;
import com.filmes.domain.ProviderWatch;

import java.util.List;

public interface TmdbRestTemplateService {

    List<Resultado> obterFilmes(String genero, String nota);
    ProviderWatch obterProviderWatch(String id);
}
