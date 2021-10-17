package com.filmes.service.impl;

import com.filmes.domain.*;
import com.filmes.service.FilmeService;
import com.filmes.service.ProviderWatchService;
import com.filmes.service.DiscoveryService;
import com.filmes.web.exception.handler.ProviderWatchNaoEncontrandoException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class FilmeServiceImpl implements FilmeService {

    private final DiscoveryService discoveryService;
    private final MovieDetailsService movieDetailsService;
    private final ProviderWatchService providerWatchService;

    public FilmeServiceImpl(DiscoveryService discoveryService, MovieDetailsService movieDetailsService, ProviderWatchService providerWatchService) {
        this.discoveryService = discoveryService;
        this.movieDetailsService = movieDetailsService;
        this.providerWatchService = providerWatchService;
    }

    @Override
    public Filme criaFilme(String genero, String nota) {
        return criarPorAno(genero, nota);
    }

    private Filme criarPorAno(String genero, String nota) {

        List<MovieId> movieIds = discoveryService.obterDiscovery(genero, nota);
        MovieId movieId = gerarFilmeAleatorio(movieIds);
        MovieDetails movieDetails = movieDetailsService.obterMovieDetails(movieId.getId());
        ProviderWatch providerWatch = providerWatchService.obterProviderWatch(movieId.getId());

        return criaFilme(movieIds, movieDetails, providerWatch);
    }


    private Filme criaFilme(List<MovieId> movieIds, MovieDetails movieDetails, ProviderWatch providerWatch) {
        int repeticao = 0;
        boolean watch = verificarProviderWatch(providerWatch);
        while (!watch) {
            MovieId movieId = gerarFilmeAleatorio(movieIds);
            movieDetails = movieDetailsService.obterMovieDetails(movieId.getId());
            providerWatch = providerWatchService.obterProviderWatch(movieId.getId());
            watch = verificarProviderWatch(providerWatch);
            repeticao++;

            if (repeticao == 100) {
                throw new ProviderWatchNaoEncontrandoException();
            }
        }

        return criarFilme(movieDetails, providerWatch);

    }


    private MovieId gerarFilmeAleatorio(List<MovieId> movieIds) {
        int total = movieIds.size();
        Random random = new Random();
        return movieIds.get(random.nextInt(total));
    }

    private Boolean verificarProviderWatch(ProviderWatch resultado) {
        List<Integer> ls = Arrays.asList(8, 384, 119, 9, 337, 499, 484, 619);
        List<Flatrate> flatrate = resultado.getBR().getFlatrate();
        return flatrate.stream().anyMatch(fla -> ls.contains(fla.getProvider_id()));
    }

    private Filme criarFilme(MovieDetails movieDetails, ProviderWatch providerWatch) {
        return Filme.builder()
                .link(providerWatch.getBR().getLink())
                .movieDetails(movieDetails)
                .build();
    }


}
