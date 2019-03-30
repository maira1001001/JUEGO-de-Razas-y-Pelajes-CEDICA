package com.laboratorio.entrega.razasypelejesdiazduhour;

public class TipoRaza extends TipoCaballo {

    private Raza raza;

    /*
        Propósito: Constructor de la clase
     */
    public TipoRaza(Raza raza) {
        this.raza = raza;
        this.nombre = raza.toString().toLowerCase();
    }

    /*
        Propósito: describe todos nombres de los razas (en minúscula)
    */
    public static String[] nombresDeLasRazas() {
        String[] todasLasRazas = new String[Raza.values().length];
        Raza[] razas = Raza.values();
        for (int i = 0; i < Raza.values().length; i++) {
            todasLasRazas[i] = razas[i].toString().toLowerCase();
        }
        return todasLasRazas;
    }

    /*
        Propósito: Describe la cantidad de razas existentes
     */
    public static int cantidadDeRazas() {
        return Raza.values().length;
    }

    /*
        Propósito: describe todos los tipos de razas en función de la cantidad de razas existentes
     */
    public static TipoRaza[] todosLosTiposDeRazas() {
        TipoRaza[] todosLosTiposDeRazas = new TipoRaza[TipoRaza.cantidadDeRazas()];
        int i = 0;
        for (Raza raza : Raza.values()) {
            todosLosTiposDeRazas[i] = new TipoRaza(raza);
            i++;
        }
        return todosLosTiposDeRazas;
    }

    /*
        Propósito: Describe la raza
     */
    public Raza getRaza() {
        return this.raza;
    }

}
