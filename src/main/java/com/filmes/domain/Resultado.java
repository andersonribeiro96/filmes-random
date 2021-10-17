package com.filmes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.io.Serializable;

@Data
public class Resultado implements Serializable {
    
    @JsonProperty("title")
    private String titulo;

    @JsonProperty("id")
    private String id;

    @JsonProperty("poster_path")
    private String imagemPoster;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("vote_count")
    private String voteCount;

    @JsonProperty("vote_average")
    private String voteAverage;
    
}
