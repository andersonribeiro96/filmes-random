package com.filmes.service.impl;

import com.filmes.domain.*;
import com.filmes.service.DiscoveryService;
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
public class DiscoveryServiceImpl implements DiscoveryService {

    private final Logger logger = LoggerFactory.getLogger(DiscoveryService.class);
    private final RestTemplate restTemplate;

    @Autowired
    public DiscoveryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MovieId> obterDiscovery(String genero, String nota) {
        return obterPorAno(genero, nota);
    }

    private List<MovieId> obterPorAno(String genero, String nota) {

        Discovery discovery = restTemplate.getForObject(criaURL(genero, nota, 1), Discovery.class);
        int totalPage = Objects.requireNonNull(discovery).getTotal_pages();
        logger.info("total de resultados: " + discovery.getTotal_results());
        List<MovieId> movieIds = new LinkedList<>(discovery.getResults());

        if (movieIds.isEmpty()) {
            throw new CaracteristicasNaoEncontradaException();
        }

        for (int i = 2; i <= totalPage; i++) {
            discovery = restTemplate.getForObject(criaURL(genero, nota, i), Discovery.class);
            movieIds.addAll(Objects.requireNonNull(discovery).getResults());
        }
        return movieIds;
    }


    private String criaURL(String generos, String nota, int pagina) {
        return "https://api.themoviedb.org/3/discover/movie?api_key=0f99ba5808dd030a7732da55682410e6&language=en-US&sort_by=vote_average.desc&include_adult=false&include_video=false&page=".concat(String.valueOf(pagina)) + "&with_genres=".concat(generos) + "&with_watch_providers=8%7C9%7C119%7C337%7C619&watch_region=BR&with_watch_monetization_types=flatrate&vote_average.gte=".concat(nota);
    }




}
