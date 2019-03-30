package com.laboratorio.entrega.razasypelejesdiazduhour;

public class Facil extends Dificultad {

    @Override
    public int obtenerLayout(Interaccion interaccion) {
        return interaccion.obtenerLayoutFacil();
    }

    @Override
    public int cantidadDeElementos() { return 2; }


}
