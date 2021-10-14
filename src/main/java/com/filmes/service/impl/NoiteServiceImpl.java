package com.filmes.service.impl;

import com.filmes.domain.Filme;
import com.filmes.domain.Noite;
import com.filmes.domain.enums.BebidaEnum;
import com.filmes.domain.enums.ComidaEnum;
import com.filmes.domain.enums.SobremesaEnum;
import com.filmes.service.*;
import org.springframework.stereotype.Service;

@Service
public class NoiteServiceImpl implements NoiteService {

    private final GerarFilmeService gerarFilmeService;
    private final ComidaService comidaService;
    private final BebidaService bebidaService;
    private final SobremesaService sobremesaService;

    public NoiteServiceImpl(GerarFilmeService gerarFilmeService, ComidaService comidaService, BebidaService bebidaService, SobremesaService sobremesaService) {
        this.gerarFilmeService = gerarFilmeService;
        this.comidaService = comidaService;
        this.bebidaService = bebidaService;
        this.sobremesaService = sobremesaService;
    }

    @Override
    public Noite gerarNoite(String genero, Long ano) {
        Filme filme = gerarFilmeService.gerarFilme(genero, ano);
        ComidaEnum comidaEnum = comidaService.gerarComida();
        BebidaEnum bebidaEnum = bebidaService.gerarBebida();
        SobremesaEnum sobremesaEnum = sobremesaService.gerarSobremesa();

        return Noite.builder()
                .filme(filme)
                .comida(comidaEnum)
                .bebida(bebidaEnum)
                .sobremesa(sobremesaEnum)
                .build();
    }
}

