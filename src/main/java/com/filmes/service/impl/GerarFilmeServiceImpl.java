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
    public Filme gerarFilme(String genero, String ano, String nota, String votos) throws Exception {
        return criarPorAno(genero, ano, nota, votos);
    }

    private Filme criarPorAno(String genero, String ano, String nota, String votos) throws Exception {
        int repeticao = 0;
        List<Resultado> resultados = tmdbRestTemplate.obterFilmes(genero, ano, nota, votos);
        Resultado filme = gerarFilmeAleatorio(resultados);
        ProviderWatch providerWatch = tmdbRestTemplate.obterProviderWatch(filme.getId());

        boolean watch = verificarProviderWatch(providerWatch);

        while (!watch) {
            filme = gerarFilmeAleatorio(resultados);
            providerWatch = tmdbRestTemplate.obterProviderWatch(filme.getId());
            watch = verificarProviderWatch(providerWatch);
            repeticao++;

            if(repeticao == 100){
                throw new ProviderWatchNaoEncontrandoException();
            }
        }
        return criarFilme(filme, providerWatch);
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

    private Filme criarFilme(Resultado filme, ProviderWatch providerWatch) {
        return Filme.builder()
                .nome(filme.getTitulo())
                .link(providerWatch.getBR().getLink())
                .build();
    }


}
