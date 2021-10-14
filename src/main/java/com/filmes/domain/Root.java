package com.filmes.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Root implements Serializable {

    private int id;
    private ProviderWatch results;


}
