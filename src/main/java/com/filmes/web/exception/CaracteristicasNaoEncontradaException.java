package com.filmes.web.exception;

public class CaracteristicasNaoEncontradaException extends RuntimeException {

    static final String CARACTERISTA_NAO_ENCONTRADA = "Filme com essas características não encontrado";

    public CaracteristicasNaoEncontradaException(){
        super(CARACTERISTA_NAO_ENCONTRADA);
    }


}
