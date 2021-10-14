package com.filmes.shared.util;

import com.filmes.shared.Constantes;

public class JsonConvert {

    public static <T> T asJsonToClass(final String json[], Class<T> classe){
        try {
            return Constantes.MAPPER.readValue(String.valueOf(json), Constantes.TYPE_FACTORY.constructType(classe));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }


}
