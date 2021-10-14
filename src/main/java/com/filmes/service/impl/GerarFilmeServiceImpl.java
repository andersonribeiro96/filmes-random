package com.filmes.service.impl;

import com.filmes.domain.*;
import com.filmes.service.GerarFilmeService;
import com.filmes.service.TmdbRestTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GerarFilmeServiceImpl implements GerarFilmeService {

    private final TmdbRestTemplate tmdbRestTemplate;

    public GerarFilmeServiceImpl( TmdbRestTemplate tmdbRestTemplate) {
        this.tmdbRestTemplate = tmdbRestTemplate;
    }

    @Override
    public Filme gerarFilme(String genero, String quantidade) {
        return criarPorQuantidade(genero, quantidade);
    }

    @Override
    public Filme gerarFilme(String genero, Long ano) {
        return criarPorAno(genero, ano);
    }

    private Filme criarPorAno(String genero, Long ano){
        List<Resultado> resultados = tmdbRestTemplate.obterFilmes(genero, ano);
        Resultado filme = gerarFilmeAleatorio(resultados);
        ProviderWatch providerWatch = tmdbRestTemplate.obterProviderWatch(filme.getId());
        boolean watch = verificarProviderWatch(providerWatch);

        while (!watch){
            filme = gerarFilmeAleatorio(resultados);
            providerWatch = tmdbRestTemplate.obterProviderWatch(filme.getId());
            watch = verificarProviderWatch(providerWatch);
        }
        return filmeBuilder(filme, providerWatch);
    }

    private Filme criarPorQuantidade(String genero, String quantidade){
        List<Resultado> resultados = tmdbRestTemplate.obterFilmes(genero, quantidade);
        Resultado filme = gerarFilmeAleatorio(resultados);
        ProviderWatch providerWatch = tmdbRestTemplate.obterProviderWatch(filme.getId());
        boolean watch = verificarProviderWatch(providerWatch);
        while (!watch){
            filme = gerarFilmeAleatorio(resultados);
            providerWatch = tmdbRestTemplate.obterProviderWatch(filme.getId());
            watch = verificarProviderWatch(providerWatch);
        }
        return filmeBuilder(filme, providerWatch);
    }

    private Resultado gerarFilmeAleatorio(List<Resultado> resultados){
        int total = resultados.size();
        Random random = new Random();
        return resultados.get(random.nextInt(total));
    }

    private Boolean verificarProviderWatch(ProviderWatch resultado){
        List<Integer> ls = Arrays.asList(8, 384, 119, 9, 337, 499, 484, 619);
        List<Flatrate> flatrate = resultado.getBR().getFlatrate();
        return flatrate.stream().anyMatch(fla -> ls.contains(fla.getProvider_id()));
    }

    private Filme filmeBuilder(Resultado filme, ProviderWatch providerWatch){
        return Filme.builder()
                .nome(filme.getTitulo())
                .link(providerWatch.getBR().getLink())
                .build();
    }


}
