package com.filmes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProviderWatch implements Serializable {

    @JsonProperty("BR")
    public BR bR;

}
