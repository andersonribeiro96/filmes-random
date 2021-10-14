package com.filmes.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
public class BR implements Serializable {

    public String link;
    public List<Flatrate> flatrate = new LinkedList<>();
}
