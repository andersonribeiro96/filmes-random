package com.filmes.domain;


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

    private String comida;

    private String bebida;

    private String sobremesa;

}
