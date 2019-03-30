package com.laboratorio.entrega.razasypelejesdiazduhour;

import java.util.Random;

public enum Raza {
    CUARTO_DE_MILLA,
    CRIOLLO,
    PETISO_ARGENTINO,
    MESTIZO_CRUZA_ARABE,
    MESTIZO,
    SILLA_ARGENTINO,
    SANGRE_PURO_CARRERA;

    public static int cantidadDeRazas() {
        return Raza.values().length;
    }

}
