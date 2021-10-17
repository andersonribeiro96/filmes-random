package com.filmes.service.impl;

import com.filmes.domain.MovieDetails;
import com.filmes.service.DiscoveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieDetailsServiceImpl implements MovieDetailsService {

    private final Logger logger = LoggerFactory.getLogger(DiscoveryService.class);
    private final RestTemplate restTemplate;

    public MovieDetailsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MovieDetails obterMovieDetails(String id) {
        return restTemplate.getForObject(criarURL(id), MovieDetails.class);
    }

    private String criarURL(String id){
        return "https://api.themoviedb.org/3/movie/"+id+"?api_key=0f99ba5808dd030a7732da55682410e6&language=en-US";
    }


}
