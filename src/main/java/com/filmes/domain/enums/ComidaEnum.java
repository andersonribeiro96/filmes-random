package com.filmes.domain.enums;

public enum ComidaEnum {

    PIZZA(0),
    HAMBURGUER(1),
    COMIDA_ITALIANA(2);

    private int codigoComida;

     ComidaEnum(int codigoComida) {
        this.codigoComida = codigoComida;
    }

    public static ComidaEnum toEnum(Integer codigo){

        for(ComidaEnum comidaEnum : ComidaEnum.values()){
            if(codigo.equals(comidaEnum.getCodigoComida())){
                return comidaEnum;
            }
        }
        throw new IllegalArgumentException("Código inválido " + codigo);

    }

    public int getCodigoComida() {
        return codigoComida;
    }

    public void setCodigoComida(int codigoComida) {
        this.codigoComida = codigoComida;
    }
}
