package com.laboratorio.entrega.razasypelejesdiazduhour;

public class PalabraImagen extends Interaccion {
    @Override
    public int obtenerLayoutFacil() {
        return R.layout.palabra_imagen_facil;
    }

    @Override
    public int obtenerLayoutDificil() {
        return R.layout.palabra_imagen_dificil;
    }

    /*
        Propósito: carga la palabra a reconocer según la configuracion definida. El item a reconocer se ubica en la parte superior
    */
    @Override
    public void cargarItemAReconocer(Dificultad dificultad, MiniJuegoActivity miniJuegoActivity, Jugada jugada) {
        dificultad.cargarPalabraAReconocer(miniJuegoActivity, jugada);
    }

    /*
        Propósito: carga la imagen ganadora ubicada en la parte inferior segun la dificultad (facil o dificil) definida
     */
    @Override
    public void cargarItemGanador(Dificultad dificultad, MiniJuegoActivity miniJuegoActivity, Jugada jugada) {
        dificultad.cargarImagenGanadora(miniJuegoActivity, jugada);
    }

    /*
        Propósito: carga las imagenes No ganadoras ubicadas en la parte inferior segun la dificultad (facil o dificil) definida
     */
    @Override
    public void cargarItemsNoGanadores(Dificultad dificultad, MiniJuegoActivity miniJuegoActivity, Jugada jugada) {
        dificultad.cargarImagenesNoGanadoras(miniJuegoActivity, jugada);
    }

    /*
        Propósito: carga la imagen ganadora de la última jugada
        Precondición: Es la última jugada
     */
    @Override
    public void cargarItemGanadorUltimaJugada(Dificultad dificultad, MiniJuegoActivity miniJuegoActivity, Jugada jugada) {
        dificultad.cargarImagenGanadoraUltimaJugada(miniJuegoActivity, jugada);
    }

    /*
        Propósito: carga las imagenes NO ganadoras de la última jugada
        Precondición: Es la última jugada
     */
    @Override
    public void cargarItemsNoGanadoresUltimaJugada(Dificultad dificultad, MiniJuegoActivity miniJuegoActivity, Jugada jugada) {
        dificultad.cargarImagenesNoGanadoraUltimaJugada(miniJuegoActivity, jugada);
    }

}
