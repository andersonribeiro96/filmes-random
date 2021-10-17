package com.filmes.service;

import com.filmes.domain.MovieId;

import java.util.List;

public interface DiscoveryService {
    List<MovieId> obterDiscovery(String genero, String nota);
}
