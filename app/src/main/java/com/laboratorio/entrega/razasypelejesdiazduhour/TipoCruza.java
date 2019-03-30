package com.laboratorio.entrega.razasypelejesdiazduhour;

public class TipoCruza extends TipoCaballo {

    private Cruza cruza;

    /*
        Propósito: Constructor de la clase
    */
    public TipoCruza(Cruza cruza) {
        this.cruza = cruza;
        this.nombre = cruza.toString().toLowerCase();
    }

    /*
        Propósito: Describe la cantidad de cruzas existentes
    */
    public static int cantidadDeCruzas() {
        return Cruza.values().length;
    }


    public  static TipoCruza cruzaAleatoriaSinLaCruza(Cruza cruza) {
        TipoCaballo[] tipos = TipoCaballo.tiposAleatoreos(TipoCruza.todosLosTiposDeCruza());
        TipoCaballo[] tiposSinElTipo = TipoCaballo.tiposSinElTipo(tipos, new TipoCruza(cruza));
        return (TipoCruza) tiposSinElTipo[0];
    }

    public Cruza getCruza() {
        return this.cruza;
    }


    public static TipoCruza[] todosLosTiposDeCruza() {
        TipoCruza[] todosLosTiposDeCruza = new TipoCruza[TipoCruza.cantidadDeCruzas()];
        int i = 0;
        for (Cruza cruza : Cruza.values()) {
            todosLosTiposDeCruza[i] = new TipoCruza(cruza);
            i++;
        }
        return todosLosTiposDeCruza;
    }



}
