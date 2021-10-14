package com.filmes.domain.enums;

public enum BebidaEnum {

    REFRIGERANTE(0),
    SUCO(1);

    private int bebidaCodigo;

    BebidaEnum(int bebidaCodigo){
        this.bebidaCodigo = bebidaCodigo;
    }

    public static BebidaEnum toEnum(Integer codigo){

        for(BebidaEnum bebidaEnum : BebidaEnum.values()){
            if(codigo.equals(bebidaEnum.getBebidaCodigo())){
                return bebidaEnum;
            }
        }
        throw new IllegalArgumentException("Código inválido " + codigo);

    }

    public int getBebidaCodigo() {
        return bebidaCodigo;
    }

    public void setBebidaCodigo(int bebidaCodigo) {
        this.bebidaCodigo = bebidaCodigo;
    }
}
