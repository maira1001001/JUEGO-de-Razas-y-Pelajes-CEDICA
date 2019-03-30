package com.laboratorio.entrega.razasypelejesdiazduhour;

class TipoRazayPelaje extends TipoCaballo {

    private RazayPelaje razayPelaje;

    /*
        Propósito: Constructor de la clase
     */
    public TipoRazayPelaje(RazayPelaje razayPelaje) {
        this.razayPelaje = razayPelaje;
        this.nombre = razayPelaje.toString().toLowerCase();
    }

    /*
        Propósito: describe todos nombres de los razas (en minúscula)
    */
    public static String[] nombresDeLasRazasYPelajesJuntas() {
        String[] nombres = new String[RazayPelaje.values().length];
        RazayPelaje[] ryp = RazayPelaje.values();
        for (int i = 0; i < RazayPelaje.values().length; i++) {
            nombres[i] = ryp[i].toString().toLowerCase();
        }
        return nombres;
    }

    /*
        Propósito: Describe la cantidad de razas existentes
     */
    public static int cantidadDeRazasyPelajes() {
        return RazayPelaje.values().length;
    }

    /*
        Propósito: describe todos los tipos de razas en función de la cantidad de razas existentes
     */
    public static TipoRazayPelaje[] todosLosTiposDeRazasyPelajes() {
        TipoRazayPelaje[] todosLosTiposDeRazasyPelajes = new TipoRazayPelaje[TipoRazayPelaje.cantidadDeRazasyPelajes()];
        int i = 0;
        for (RazayPelaje ryp : RazayPelaje.values()) {
            todosLosTiposDeRazasyPelajes[i] = new TipoRazayPelaje(ryp);
            i++;
        }
        return todosLosTiposDeRazasyPelajes;
    }

    /*
        Propósito: Describe la raza
     */
    public RazayPelaje getRazayPelaje() {
        return this.razayPelaje;
    }

}
