package com.filmes.service;

import com.filmes.domain.IMBD;
import com.filmes.domain.ImdbRating;
import com.filmes.domain.Resultado;
import com.filmes.domain.ProviderWatch;

import java.util.List;

public interface TmdbRestTemplateService {

    List<Resultado> obterFilmes(String genero, String nota);
    IMBD obterCodigoImdb(String id);
    ImdbRating obterRating(String id);
    ProviderWatch obterProviderWatch(String id);
}
