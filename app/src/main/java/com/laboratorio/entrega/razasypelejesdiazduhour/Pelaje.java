package com.laboratorio.entrega.razasypelejesdiazduhour;

public enum Pelaje {
    ALAZAN,
    ALAZAN_PINTADO,
    ALAZAN_RUANO,
    ALAZAN_TOSTADO,
    BAYO,
    BAYO_GATEADO,
    BLANCO,
    MORO,
    OVERO_AZULEJO,
    OVERO_ROSADO,
    OVERO_ZAINO,
    PICASO,
    TOBIANO,
    TORDILLO,
    TORDILLO_CANELA,
    TORDILLO_MORO,
    ZAINO,
    ZAINO_COLORADO,
    ZAINO_OSCURO;

    /*
        Propósito: Describe la cantidad de pelajes existentes
    */
    public static int cantidadDePelajes() {
        return Pelaje.values().length;
    }

}