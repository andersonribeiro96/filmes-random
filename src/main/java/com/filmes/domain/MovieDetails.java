package com.filmes.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDetails implements Serializable {

    @JsonProperty("title")
    private String title;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("imdb_id")
    private String imdb_id;

    @JsonProperty("release_date")
    private String release_date;

    @JsonProperty("vote_average")
    private String vote_average;

    @JsonProperty("vote_count")
    private String vote_count;

    @JsonProperty("runtime")
    private String runtime;

    @JsonProperty("poster_path")
    private String poster_path;


}
