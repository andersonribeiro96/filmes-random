package com.filmes.domain.enums;

public enum SobremesaEnum {

    CHURROS(0),
    SORVETE(1),
    BRIGADEIRO(2);

    private int sobremesaCodigo;


    SobremesaEnum(int sobremesaCodigo){
        this.sobremesaCodigo = sobremesaCodigo;
    }

    public static SobremesaEnum toEnum(Integer codigo){

        for(SobremesaEnum sobremesaEnum : SobremesaEnum.values()){
            if(codigo.equals(sobremesaEnum.getSobremesaCodigo())){
                return sobremesaEnum;
            }
        }
        throw new IllegalArgumentException("Código inválido " + codigo);

    }

    public int getSobremesaCodigo() {
        return sobremesaCodigo;
    }

    public void setSobremesaCodigo(int sobremesaCodigo) {
        this.sobremesaCodigo = sobremesaCodigo;
    }
}
