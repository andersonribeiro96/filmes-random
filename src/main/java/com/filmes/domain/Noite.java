package com.filmes.domain;


import com.filmes.domain.enums.BebidaEnum;
import com.filmes.domain.enums.ComidaEnum;
import com.filmes.domain.enums.SobremesaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Noite {

    private Filme filme;

    private ComidaEnum comida;

    private BebidaEnum bebida;

    private SobremesaEnum sobremesa;

}
