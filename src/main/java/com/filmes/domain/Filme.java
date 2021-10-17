package com.filmes.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Filme {

    private String nome;
    private String link;
    private String urlImage;
    private String description;
    private String qtdvotos;
    private String nota;
    private ImdbRating rating;


}
