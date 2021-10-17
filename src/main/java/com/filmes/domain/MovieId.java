package com.filmes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.io.Serializable;

@Data
public class MovieId implements Serializable {

    @JsonProperty("id")
    private String id;
    
}
