package com.laboratorio.entrega.razasypelejesdiazduhour;

import java.util.Random;

public class Aleatorio {

    /*
        Propósito: Retorna un número aleatóreo entre "min" y "max"
     */
    public static int entreMinyMax(int min, int max) {
        if (max < min || min < 0 || max < 0) throw new IllegalArgumentException("Parametros invalidos. ");
        Random r = new Random();
        return min + (r.nextInt(max - min + 1));
    }

}
