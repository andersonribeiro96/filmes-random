package com.filmes.shared;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Constantes {

    private Constantes(){

    }

    public static ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

    public static TypeFactory TYPE_FACTORY = MAPPER.getTypeFactory();

}
