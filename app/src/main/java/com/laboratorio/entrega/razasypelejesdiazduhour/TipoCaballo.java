package com.laboratorio.entrega.razasypelejesdiazduhour;

import java.util.function.ToIntBiFunction;

class TipoCaballo {

    protected String nombre;

    protected String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
        Propósito: Describe "n" tipos aleatoreos a partir de "tipos"
     */
    public static TipoCaballo[]  nTiposAleatoreos(TipoCaballo[] tipos, int n) {
        TipoCaballo[] nTipos = new TipoCaballo[n];
        TipoCaballo[] todosLostiposAleatoreos = tiposAleatoreos(tipos);
        for (int i = 0; i < n; i++) {
            nTipos[i] = todosLostiposAleatoreos[i];
        }
        return nTipos;
    }

    /*
        Propósito: Describe una secuencia aleatórea de TiposCaballo a partir del parámetro "tipos"
    */
    public static TipoCaballo[] tiposAleatoreos(TipoCaballo[] tipos) {
        if (tipos.length == 2) {
            int a = Aleatorio.entreMinyMax(0,1);
            TipoCaballo aux;
            if (a==0) {
                aux = tipos[0];
                tipos[0] = tipos[1];
                tipos[1] = aux;
                return  tipos;
            } else {
                return tipos;
            }
        } else {
            int j;
            TipoCaballo aux;
            for (int i = 1; i < tipos.length; i++) {
                j = Aleatorio.entreMinyMax(0, i - 1);
                aux = tipos[i];
                tipos[i] = tipos[j];
                tipos[j] = aux;
            }
            return tipos;
        }
    }


    /*
        * Propósito: Retorna los tipos pasado por parámetro, pero sin el tipo "tipoAEliminar"
        * Precondición: Hay al menos 1 elemento en "tipos"
    */
    public static TipoCaballo[] tiposSinElTipo(TipoCaballo[] tipos, TipoCaballo tipoAEliminar) {
        //TODO: es un filter por el tipo de nombre .
        TipoCaballo[] tiposSinElTipo = new TipoCaballo[tipos.length-1];
        int i = 0;
        String nombreAEliminar = tipoAEliminar.getNombre();
        for (TipoCaballo tipo : tipos) {
            String nombreActual = tipo.getNombre();
            if (tipo.getNombre().compareTo(tipoAEliminar.getNombre())!=0) {
                tiposSinElTipo[i] = tipo;
                i++;
            }
        }
        return tiposSinElTipo;
    }

    /*
        * Propósito: describe los tipos elegidos de forma aleatoreas a partir de "tipos", la cantidad definida por "cantidadDeTipos", incluyendo el tipo por parámetro "tipo"
        * Precondición: Hay al menos "cantidadDeTipos" definidas en "tipos"
    */
    public static TipoCaballo[] tiposAleatoreasConElTipo(TipoCaballo[] tipos, TipoCaballo tipo, int cantidadDeTipos) {
        TipoCaballo[] resultado = new TipoCaballo[cantidadDeTipos];
        TipoCaballo[] tiposSinElTipo = TipoCaballo.tiposSinElTipo(tipos, tipo);
        TipoCaballo[] tiposAleatoreosSinElTipo = TipoCaballo.tiposAleatoreos(tiposSinElTipo);
        resultado[0] = tipo;
        for (int i = 1; i < cantidadDeTipos; i++) {
            resultado[i] = tiposAleatoreosSinElTipo[i];
        }
        return TipoCaballo.tiposAleatoreos(resultado);
    }


    /*
        * Propósito: describe 3 Tipos elegidos de forma aleatoreas, pero sin el tipo "sinEltipo"
        * Precondición: Hay al menos 3 Tipos
    */
    public static TipoCaballo[] tresTiposAleatoreasSinElTipo(TipoCaballo[] tipos,TipoCaballo sinElTipo) {
        //obtengo todos los tipos sin el tipo "sinElTipo"
        TipoCaballo[] tipoSinElTipo = TipoCaballo.tiposSinElTipo(tipos, sinElTipo);
        //generar los tipos aleatoreos
        TipoCaballo[] tiposAleatoreosSinElTipo = TipoCaballo.tiposAleatoreos(tipoSinElTipo);
        TipoCaballo[] tresTiposAleatoreasSinElTipo = new TipoCaballo[3];
        //obtengo los 3 tipos
        tresTiposAleatoreasSinElTipo[0] = tiposAleatoreosSinElTipo[0];
        tresTiposAleatoreasSinElTipo[1] = tiposAleatoreosSinElTipo[1];
        tresTiposAleatoreasSinElTipo[2] = tiposAleatoreosSinElTipo[2];
        return tresTiposAleatoreasSinElTipo;
    }

    /*
        * Propósito: describe un tipo cualquiera de "tipos" , pero sin el tipo "sinElTipo"
        * Precondición: "sinElTipo" existe en "tipos" y hay al menos dos elementos distintos en "tipos"
     */
    public static TipoCaballo tipoSinElTipo(TipoCaballo[] tipos, TipoCaballo sinElTipo) {
        TipoCaballo tipoSinElTipo = tipos[0];
        for (int i = 0; i < tipos.length; i++) {
            if (tipos[i].getNombre().compareTo(sinElTipo.getNombre())!=0) {
                tipoSinElTipo = tipos[i]; break;
            }
        }
        return  tipoSinElTipo;
    }
}
