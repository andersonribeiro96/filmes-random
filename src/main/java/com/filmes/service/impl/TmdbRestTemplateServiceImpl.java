package com.filmes.service.impl;

import com.filmes.domain.Json;
import com.filmes.domain.Resultado;
import com.filmes.domain.ProviderWatch;
import com.filmes.domain.Root;
import com.filmes.service.TmdbRestTemplate;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


@Service
public class TmdbRestTemplateServiceImpl implements TmdbRestTemplate {

    private final Logger logger = LoggerFactory.getLogger(TmdbRestTemplate.class);

    private final RestTemplate restTemplate;

    @Autowired
    public TmdbRestTemplateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Resultado> obterFilmes(String genero, String quantidade) {
        return obterPorQuantidade(genero, quantidade);
    }

    @Override
    public List<Resultado> obterFilmes(String genero, Long ano) {
        return obterPorAno(genero,ano);
    }

    @Override
    public ProviderWatch obterProviderWatch(String id) {
        String URL = "https://api.themoviedb.org/3/movie/".concat(id) + "/watch/providers?api_key=0f99ba5808dd030a7732da55682410e6";
        Root root = restTemplate.getForObject(URL, Root.class);
        return Objects.requireNonNull(root).getResults();
    }


    private List<Resultado> obterPorAno(String genero, Long ano){
        String URL = "https://api.themoviedb.org/3/discover/movie?api_key=0f99ba5808dd030a7732da55682410e6&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=".concat("1")+"&primary_release_year=".concat(ano.toString())+"&with_genres=".concat(genero)+"&with_watch_providers=8%7C9%7C119%7C337%7C619&watch_region=BR&with_watch_monetization_types=flatrate";
        Json json = restTemplate.getForObject(URL, Json.class);

        int totalPage = Objects.requireNonNull(json).getTotal_pages();
        logger.info("Total de resultados: " + json.getTotal_results());
        List<Resultado> resultados = new LinkedList<>(json.getResults());

        for(int i = 2; i <= totalPage; i++){
            logger.info("Obtendo pagina: " + i);
            URL = "https://api.themoviedb.org/3/discover/movie?api_key=0f99ba5808dd030a7732da55682410e6&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=".concat(String.valueOf(i))+"&primary_release_year=".concat(ano.toString())+"&with_genres=".concat(genero)+"&with_watch_providers=8%7C9%7C119%7C337%7C619&watch_region=BR&with_watch_monetization_types=flatrate&with_original_language=en";
            json = restTemplate.getForObject(URL, Json.class);
            resultados.addAll(Objects.requireNonNull(json).getResults());
        }
        return resultados;
    }

    private List<Resultado> obterPorQuantidade(String genero, String quantidade){
        String URL = "https://api.themoviedb.org/3/discover/movie?api_key=0f99ba5808dd030a7732da55682410e6&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=".concat("1")+"&with_genres=".concat(genero)+"&with_watch_providers=8%7C9%7C119%7C337%7C619&watch_region=BR&with_watch_monetization_types=flatrate";
        Json json = restTemplate.getForObject(URL, Json.class);
        int totalResultado = Objects.requireNonNull(json).getTotal_pages();
        List<Resultado> resultados = new LinkedList<>(json.getResults());

        if(Integer.parseInt(quantidade) > totalResultado){
            quantidade = String.valueOf(totalResultado);
        }

        for(int i = 2; i <= Integer.parseInt(quantidade); i++){
            URL = "https://api.themoviedb.org/3/discover/movie?api_key=0f99ba5808dd030a7732da55682410e6&page=".concat(String.valueOf(i))+"&with_genres=".concat(genero)+"&with_watch_providers=8%7C9%7C119%7C337%7C619&watch_region=BR&with_watch_monetization_types=flatrate&with_original_language=en";
            json = restTemplate.getForObject(URL, Json.class);
            resultados.addAll(Objects.requireNonNull(json).getResults());
        }
        return resultados;
    }



}
