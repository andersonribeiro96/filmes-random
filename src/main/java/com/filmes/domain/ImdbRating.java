package com.filmes.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImdbRating {

    public String imDbId;
    public String title;
    public String fullTitle;
    public String type;
    public String year;
    public String imDb;
    public String metacritic;
    public String theMovieDb;
    public String rottenTomatoes;
    public String tV_com;
    public String filmAffinity;
    public String errorMessage;

}
