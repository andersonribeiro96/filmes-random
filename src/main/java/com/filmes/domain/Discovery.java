package com.filmes.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Discovery implements Serializable {

    private Long page;

    private int total_pages;

    private int total_results;

    private List<MovieId> results = new ArrayList<>();
}
