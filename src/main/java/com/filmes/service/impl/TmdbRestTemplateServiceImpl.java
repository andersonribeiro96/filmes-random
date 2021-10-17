package com.filmes.service.impl;

import com.filmes.domain.Json;
import com.filmes.domain.ProviderWatch;
import com.filmes.domain.Resultado;
import com.filmes.domain.Root;
import com.filmes.service.TmdbRestTemplateService;
import com.filmes.web.exception.CaracteristicasNaoEncontradaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


@Service
public class TmdbRestTemplateServiceImpl implements TmdbRestTemplateService {

    private final Logger logger = LoggerFactory.getLogger(TmdbRestTemplateService.class);
    private final RestTemplate restTemplate;

    @Autowired
    public TmdbRestTemplateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Resultado> obterFilmes(String genero, String nota) {
        return obterPorAno(genero, nota);
    }

    @Override
    public ProviderWatch obterProviderWatch(String id) {
        String URL = "https://api.themoviedb.org/3/movie/".concat(id) + "/watch/providers?api_key=0f99ba5808dd030a7732da55682410e6";
        Root root = restTemplate.getForObject(URL, Root.class);
        return Objects.requireNonNull(root).getResults();
    }


    private List<Resultado> obterPorAno(String genero, String nota) {

        Json json = restTemplate.getForObject(montarURL(genero, nota, 1), Json.class);
        int totalPage = Objects.requireNonNull(json).getTotal_pages();

        logger.info("total de resultados: " + json.getTotal_results());

        List<Resultado> resultados = new LinkedList<>(json.getResults());

        if (resultados.isEmpty()) {
            throw new CaracteristicasNaoEncontradaException();
        }

        for (int i = 2; i <= totalPage; i++) {
            json = restTemplate.getForObject(montarURL(genero, nota, i), Json.class);
            resultados.addAll(Objects.requireNonNull(json).getResults());
        }
        return resultados;
    }

    private String montarURL(String generos, String nota, int pagina) {
        return "https://api.themoviedb.org/3/discover/movie?api_key=0f99ba5808dd030a7732da55682410e6&language=en-US&sort_by=vote_average.desc&include_adult=false&include_video=false&page=".concat(String.valueOf(pagina)) + "&with_genres=".concat(generos) + "&with_watch_providers=8%7C9%7C119%7C337%7C619&watch_region=BR&with_watch_monetization_types=flatrate&vote_average.gte=".concat(nota);
    }

}
