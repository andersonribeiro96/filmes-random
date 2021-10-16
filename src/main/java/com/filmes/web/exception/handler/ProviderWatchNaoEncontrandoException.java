package com.filmes.web.exception.handler;

public class ProviderWatchNaoEncontrandoException extends RuntimeException {

    static final String PROVIDER_NAO_ENCONTRADO = "Não foi possível encontrar provedor de serviços para as características selecionadas";

    public ProviderWatchNaoEncontrandoException(){
        super(PROVIDER_NAO_ENCONTRADO);
    }


}
