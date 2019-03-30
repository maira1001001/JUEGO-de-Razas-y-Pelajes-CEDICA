package com.laboratorio.entrega.razasypelejesdiazduhour;

public class ImagenPalabra extends Interaccion {
    @Override
    public int obtenerLayoutFacil() {
        return R.layout.imagen_palabra_facil;
    }

    @Override
    public int obtenerLayoutDificil() {
        return R.layout.imagen_palabra_dificil;
    }

    /*
        Propósito: carga la imagen a reconocer según la configuracion definida. El item a reconocer se ubica en la parte superior
     */
    @Override
    public void cargarItemAReconocer(Dificultad dificultad, MiniJuegoActivity miniJuegoActivity, Jugada jugada) {
        dificultad.cargarImagenAReconocer(miniJuegoActivity, jugada);
    }

    /*
        Propósito: carga la palabra ganadora ubicada en la parte inferior segun la dificultad definida
     */
    @Override
    public void cargarItemGanador(Dificultad dificultad, MiniJuegoActivity miniJuegoActivity, Jugada jugada) {
        dificultad.cargarPalabraGanadora(miniJuegoActivity, jugada);
    }

    /*
        Propósito: carga las palabras No ganadoras ubicadas en la parte inferior segun la dificultad definida
     */
    @Override
    public void cargarItemsNoGanadores(Dificultad dificultad, MiniJuegoActivity miniJuegoActivity, Jugada jugada) {
        dificultad.cargarPalabrasNoGanadoras(miniJuegoActivity, jugada);
    }

    /*
        Propósito: carga la palabra ganadora de la última jugada
        Precondición: Es la última jugada
     */
    @Override
    public void cargarItemGanadorUltimaJugada(Dificultad dificultad, MiniJuegoActivity miniJuegoActivity, Jugada jugada) {
        dificultad.cargarPalabraGanadoraUltimaJugada(miniJuegoActivity, jugada);
    }

    /*
        Propósito: carga las palabras ganadoras de la última jugada
        Precondición: Es la última jugada
     */
    @Override
    public void cargarItemsNoGanadoresUltimaJugada(Dificultad dificultad, MiniJuegoActivity miniJuegoActivity, Jugada jugada) {
        dificultad.cargarPalabrasNoGanadorasUltimaJugada(miniJuegoActivity, jugada);
    }
}
