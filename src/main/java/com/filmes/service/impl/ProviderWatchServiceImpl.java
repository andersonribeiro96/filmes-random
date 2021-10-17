package com.filmes.service.impl;

import com.filmes.domain.ProviderWatch;
import com.filmes.domain.Root;
import com.filmes.service.ProviderWatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ProviderWatchServiceImpl implements ProviderWatchService {

    private final Logger logger = LoggerFactory.getLogger(ProviderWatchService.class);
    private final RestTemplate restTemplate;

    public ProviderWatchServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProviderWatch obterProviderWatch(String id) {
        logger.info("Obtendo provedor filme: " + id);
        Root root = restTemplate.getForObject(criaURL(id), Root.class);
        return Objects.requireNonNull(root).getResults();
    }

    private String criaURL(String id){
        return "https://api.themoviedb.org/3/movie/"+id+"/watch/providers?api_key=0f99ba5808dd030a7732da55682410e6";
    }

}
