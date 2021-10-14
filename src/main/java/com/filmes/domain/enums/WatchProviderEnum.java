package com.filmes.domain.enums;



public enum WatchProviderEnum {
    NETFLIX(8),
    HBO_MAX(384),
    AMAZON_PRIME_VIDEO(119),
    AMAZON_VIDEO(9),
    DISNEY(337),
    STAR_PLUS(619);

    private int codigoWatch;

    WatchProviderEnum(int codigoWatch){
        codigoWatch = codigoWatch;
    }

    public int getCodigoWatch() {
        return codigoWatch;
    }

    public void setCodigoWatch(int codigoWatch) {
        this.codigoWatch = codigoWatch;
    }
}
