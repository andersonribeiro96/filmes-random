package com.filmes.service.impl;

import com.filmes.domain.*;
import com.filmes.service.GerarFilmeService;
import com.filmes.service.TmdbRestTemplateService;
import com.filmes.web.exception.handler.ProviderWatchNaoEncontrandoException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GerarFilmeServiceImpl implements GerarFilmeService {

    private final TmdbRestTemplateService tmdbRestTemplate;

    public GerarFilmeServiceImpl(TmdbRestTemplateService tmdbRestTemplate) {
        this.tmdbRestTemplate = tmdbRestTemplate;
    }

    @Override
    public Filme gerarFilme(String genero, String nota) {
        return criarPorAno(genero, nota);
    }

    private Filme criarPorAno(String genero, String nota) {
        int repeticao = 0;
        List<Resultado> resultados = tmdbRestTemplate.obterFilmes(genero, nota);
        Resultado filme = gerarFilmeAleatorio(resultados);
        ProviderWatch providerWatch = tmdbRestTemplate.obterProviderWatch(filme.getId());

        boolean watch = verificarProviderWatch(providerWatch);

        while (!watch) {
            filme = gerarFilmeAleatorio(resultados);
            providerWatch = tmdbRestTemplate.obterProviderWatch(filme.getId());
            watch = verificarProviderWatch(providerWatch);
            repeticao++;

            if (repeticao == 100) {
                throw new ProviderWatchNaoEncontrandoException();
            }
        }

        IMBD imbd = tmdbRestTemplate.obterCodigoImdb(filme.getId());
        ImdbRating imdbRating = tmdbRestTemplate.obterRating(imbd.getImdb_id());

        return criarFilme(filme, providerWatch, imdbRating);
    }


    private Resultado gerarFilmeAleatorio(List<Resultado> resultados) {
        int total = resultados.size();
        Random random = new Random();
        return resultados.get(random.nextInt(total));
    }

    private Boolean verificarProviderWatch(ProviderWatch resultado) {
        List<Integer> ls = Arrays.asList(8, 384, 119, 9, 337, 499, 484, 619);
        List<Flatrate> flatrate = resultado.getBR().getFlatrate();
        return flatrate.stream().anyMatch(fla -> ls.contains(fla.getProvider_id()));
    }

    private Filme criarFilme(Resultado filme, ProviderWatch providerWatch, ImdbRating rating) {
        return Filme.builder()
                .nome(filme.getTitulo())
                .link(providerWatch.getBR().getLink())
                .urlImage("https://image.tmdb.org/t/p/original" + filme.getImagemPoster())
                .description(filme.getOverview())
                .nota(filme.getVoteAverage())
                .qtdvotos(filme.getVoteCount())
                .rating(rating)
                .build();
    }


}
