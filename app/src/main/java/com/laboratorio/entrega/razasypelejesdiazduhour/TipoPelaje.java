package com.laboratorio.entrega.razasypelejesdiazduhour;

public class TipoPelaje extends TipoCaballo {

    private Pelaje pelaje;

    /*
        Propósito: Constructor de la clase
     */
    public TipoPelaje(Pelaje pelaje) {
        this.pelaje = pelaje;
        this.nombre = pelaje.toString().toLowerCase();
    }

    /*
        Propósito: describe todos nombres de los pelajes (en minúscula)
    */
    public static String[] nombresDeLosPelajes() {
        String[] todosLosPelajes = new String[Pelaje.values().length];
        Pelaje[] pelajes = Pelaje.values();
        for (int i = 0; i < Pelaje.values().length; i++) {
            todosLosPelajes[i] = pelajes[i].toString().toLowerCase();
        }
        return todosLosPelajes;
    }

    /*
        Propósito: Describe la cantidad de pelajes existentes
     */
    public static int cantidadDePelajes() {
        return Pelaje.values().length;
    }


    /*
        Propósito: describe todos los tipos de pelajes en función de la cantidad de pelajes existentes
     */
    public static TipoPelaje[] todosLosTiposDePelajes() {
        TipoPelaje[] todosLosTiposDePelajes = new TipoPelaje[TipoPelaje.cantidadDePelajes()];
        int i = 0;
        for (Pelaje pelaje : Pelaje.values()) {
            todosLosTiposDePelajes[i] = new TipoPelaje(pelaje);
            i++;
        }
        return todosLosTiposDePelajes;
    }

    /*
        Propósito: describe la pelaje
     */
    public Pelaje getPelaje() {
        return this.pelaje;
    }


}
