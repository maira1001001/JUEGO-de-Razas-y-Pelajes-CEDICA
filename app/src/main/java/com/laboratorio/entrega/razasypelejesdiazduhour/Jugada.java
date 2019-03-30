package com.laboratorio.entrega.razasypelejesdiazduhour;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

abstract class Jugada {

    protected TipoCaballo tipoAReconocer; //describe el tipo a reconocer ("Raza", "Pelaje" o "Cruza"), que se ubica en la parte superior del juego
    protected TipoCaballo tipoGanador; //describe el tipo ganador, que se ubica en la parte inferior del juego, con la que interaccionará el jugador
    protected TipoCaballo[] tiposAInteraccionar; // describe los tipos con los que interaccionará el jugador. Uno de los tipos es la jugada ganadora

    /*
        Propósito: describe el item a reconocer
     */
    public String itemAReconocer() {
        return this.tipoAReconocer.getNombre();
    }

    /*
        Propósito: describe la posición del item a ganador, el cual se encuentra en los tipos a interaccionar
        Resultado: número entero entre 0 y la cantidad de items a interaccionar
    */
    public int posicionItemGanador() {
        int posicion = -1;
        String[] items = this.itemsAInteraccionar();
        for (int i=0; i<cantidadDeItems(); i++) {
            if (items[i].compareTo(itemGanador())==0) {
                posicion = i; break;
            }
        }
        return posicion;
    }

    /*
        Propósito: describe el item ganador
     */
    public String itemGanador() {
        return this.tipoGanador.getNombre();
    }

    /*
        Propósito: describe los nombres de los items a interaccionar
     */
    public String[] itemsAInteraccionar() {
        //return this.tiposAInteraccionar.map(item -> item.getNombre());
        String[] itemsAInteraccionar = new String[cantidadDeItems()];
        for (int i=0; i<cantidadDeItems(); i++) itemsAInteraccionar[i] = tiposAInteraccionar[i].getNombre();
        return itemsAInteraccionar;
    }
    /*
        Propósito: describe la cantidad de items con los que se interaccionará segun la configuración
     */
    public int cantidadDeItems() {
        return this.tiposAInteraccionar.length;
    }

    /*
        Propósito: Describe los items NO ganadores
        Resultado: una lista de Strings
     */
    public String[] itemsNoGanadores() {
       //return this.itemsAInteraccionar().filter(item -> ((String)item).compareTo(this.itemGanador())!=0 );
        String[] itemsNoGanadores = new String[cantidadDeItems()-1];
        String[] items = this.itemsAInteraccionar();
        int j = 0;
        for (int i=0; i<cantidadDeItems();i++) {
            if (items[i].compareTo(itemGanador())!=0) {
                itemsNoGanadores[j] = items[i];
                j++;
            }
        }
        return itemsNoGanadores;
    }

    /*
        Propósito: Describe las posiciones de los items a interaccionar sin la posición del item ganador
     */
    public int[] posicionesItemsNoGanadores() {
        int j = 0;
        int[] posicionesSinItemGanador  = new int[this.cantidadDeItems()];
        for (int i=0; i<this.tiposAInteraccionar.length; i++) {
            if (this.posicionItemGanador() != i) {
                posicionesSinItemGanador[j] = i;
                j++;
            }
        }
        return posicionesSinItemGanador;
    }


    /*
        Propósito: describe "n" tipos aleatoreos de caballos, incluyendo "tipoAReconocer"
     */
    public abstract TipoCaballo[] nTiposAleatoreosConElTipo(TipoCaballo tipoAReconocer, int n);

    /*
        Propósito: describe el tipo de jugada, pudiendo ser Raza o Pelaje o Cruza
     */
    public abstract String tipoDeJugada();

    /*
    Propósito: describe el identificador de una ImageView en el layout según una posicion, que representa la ubicación dentro de los elementos a interaccionar
*/
    protected int idImageViewSegunPosicion(int posicion) {
        switch (posicion) {
            case 0:
                return R.id.imagenInteraccion0;
            case 1:
                return R.id.imagenInteraccion1;
            case 2:
                return R.id.imagenInteraccion2;
            case 3:
                return R.id.imagenInteraccion3;
            default:
                throw new IllegalArgumentException("Posicion de item inválida");
        }
    }

    /*
        Propósito: describe el identificador de una Textview en el layout según una posicion, que representa la ubicación dentro de los elementos a interaccionar
     */
    protected int idTextViewSegunPosicion(int posicion) {
        switch (posicion) {
            case 0:
                return R.id.palabraInteraccion0;
            case 1:
                return R.id.palabraInteraccion1;
            case 2:
                return R.id.palabraInteraccion2;
            case 3:
                return R.id.palabraInteraccion3;
            default:
                throw new IllegalArgumentException("Posicion de item inválida");
        }
    }

    /**
     * Devuelve el identificador del boton de audio según una posicion dada
     * @param posicion posicion del boton
     * @return identificador del boton
     */
    protected int idImageButtonSegunPosicion(int posicion) {
        switch (posicion) {
            case 0:
                return R.id.palabraButton0;
            case 1:
                return R.id.palabraButton1;
            case 2:
                return R.id.palabraButton2;
            case 3:
                return R.id.palabraButton3;
            default:
                throw new IllegalArgumentException("Posicion de item inválida");
        }
    }
    public void setTipoAReconocer(String itemAReconocer) {
        this.tipoAReconocer.setNombre(itemAReconocer);
    }

    /**
     * Genera un listener para onClick en el boton de la palabra del minijuego
     * @param button boton al cual se le seteara el listener
     * @param nombrePalabra palabra para buscar el sonido a reproducir
     */
    @NonNull
    public void setearOnClick(MiniJuegoActivity miniJuegoActivity, ImageButton button, String nombrePalabra){
        SharedPreferences preferences = miniJuegoActivity.getSharedPreferences("preferences", MODE_PRIVATE);
        String femenino = preferences.getString("audio", "Femenina");
        button.setOnClickListener((View v) -> {
            int audio;
            if (femenino.equals("Femenina")) {
                audio = miniJuegoActivity.ubicacionDeAudioDeCaballoPorNombreFemenino(nombrePalabra);
            } else {
                audio = miniJuegoActivity.ubicacionDeAudioDeCaballoPorNombreMasculino(nombrePalabra);
            }
            MediaPlayer mediaPlayer = MediaPlayer.create(miniJuegoActivity, audio);
            mediaPlayer.start();
        });
    }

    protected abstract String sanitizarTexto(String texto);

    public abstract void cargarPalabraAReconocer(MiniJuegoActivity miniJuegoActivity);

    public abstract void cargarImagenAReconocer(MiniJuegoActivity miniJuegoActivity);

    public abstract void cargarPalabraGanadora(MiniJuegoActivity miniJuegoActivity);

    public abstract void cargarImagenGanadora(MiniJuegoActivity miniJuegoActivity);

    public abstract void cargarImagenesNoGanadoras(MiniJuegoActivity miniJuegoActivity);

    public abstract void cargarPalabrasNoGanadoras(MiniJuegoActivity miniJuegoActivity);

    public abstract void cargarImagenGanadoraUltimaJugada(MiniJuegoActivity miniJuegoActivity);

    public abstract void cargarImagenesNoGanadorasUltimaJugada(MiniJuegoActivity miniJuegoActivity);

    public abstract void cargarPalabraGanadoraUltimaJugada(MiniJuegoActivity miniJuegoActivity);

    public abstract void cargarPalabrasNoGanadorasUltimaJugada(MiniJuegoActivity miniJuegoActivity);
}
