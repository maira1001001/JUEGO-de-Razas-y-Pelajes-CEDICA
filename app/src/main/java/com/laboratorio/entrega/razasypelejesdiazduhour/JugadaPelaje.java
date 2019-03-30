package com.laboratorio.entrega.razasypelejesdiazduhour;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class JugadaPelaje extends  Jugada {


    public JugadaPelaje(TipoPelaje tipoPelajeAReconocer, Dificultad dificultad)  {
        this.tipoAReconocer = tipoPelajeAReconocer;
        this.tipoGanador = tipoPelajeAReconocer;
        this.tiposAInteraccionar = TipoPelaje.tiposAleatoreasConElTipo(TipoPelaje.todosLosTiposDePelajes(), tipoPelajeAReconocer, dificultad.cantidadDeElementos());
    }

    @Override
    public TipoCaballo[] nTiposAleatoreosConElTipo(TipoCaballo tipoAReconocer, int n) {
        return TipoPelaje.tiposAleatoreasConElTipo(TipoPelaje.todosLosTiposDePelajes(), tipoAReconocer, n);
    }

    /*
        Propósito: describe el tipo de jugada
     */
    @Override
    public String tipoDeJugada() {
        return "Pelaje";
    }

    /*
        Propósito: carga la palabra a reconocer ubicada en la parte superior.
     */
    public void cargarPalabraAReconocer(MiniJuegoActivity miniJuegoActivity){
        TextView palabraAReconocer = (TextView) miniJuegoActivity.findViewById(R.id.palabraAReconocer);
        palabraAReconocer.setText(sanitizarTexto(this.itemAReconocer()));
        ImageButton button = (ImageButton) miniJuegoActivity.findViewById(R.id.palabraButton1);
        setearOnClick(miniJuegoActivity, button, this.itemAReconocer());
    }

    /*
        Propósito: carga la imagen a reconocer ubicada en la parte superior.
     */
    public void cargarImagenAReconocer(MiniJuegoActivity miniJuegoActivity){
        ImageView imagenGanadora = (ImageView) miniJuegoActivity.findViewById(R.id.imagenAReconocer);
        imagenGanadora.setImageResource(miniJuegoActivity.ubicacionDeImagenDeCaballoPorPelaje(this.itemAReconocer()));
    }

    /*
        Propósito: carga la palabra ganadora en la parte inferior del layout, donde se interaccionará para jugar
    */
    public void cargarPalabraGanadora(MiniJuegoActivity miniJuegoActivity) {
        TextView palabraAReconocer = (TextView) miniJuegoActivity.findViewById(this.idTextViewSegunPosicion(this.posicionItemGanador()));
        palabraAReconocer.setText(sanitizarTexto(this.itemAReconocer()));
        palabraAReconocer.setBackgroundResource(R.drawable.border_green);
        miniJuegoActivity.cargarEventoOnClickParaPalabraGanadora(palabraAReconocer);
        ImageButton button = (ImageButton) miniJuegoActivity.findViewById(this.idImageButtonSegunPosicion(this.posicionItemGanador()));
        setearOnClick(miniJuegoActivity, button, this.itemAReconocer());
    }

    /*
        Propósito: carga la imagen ganadora en la parte inferior del layout, donde se interaccionará para jugar
     */
    public void cargarImagenGanadora (MiniJuegoActivity miniJuegoActivity) {
        ImageView imagenGanadora = (ImageView) miniJuegoActivity.findViewById(this.idImageViewSegunPosicion(this.posicionItemGanador()));
        imagenGanadora.setImageResource(miniJuegoActivity.ubicacionDeImagenDeCaballoPorPelaje(this.itemGanador()));
        imagenGanadora.setBackgroundResource(R.drawable.border_green);
        miniJuegoActivity.cargarEventoOnClickParaImagenGanadora(imagenGanadora);
    }

    /*
        Propósito: carga las imágenes NO ganadoras en la parte inferior del layout, donde se interaccionará para jugar
    */
    public void cargarImagenesNoGanadoras (MiniJuegoActivity miniJuegoActivity) {
        for (int i=0; i<this.cantidadDeItems()-1;i++) {
            ImageView imagenNoGanadora = (ImageView) miniJuegoActivity.findViewById(this.idImageViewSegunPosicion(this.posicionesItemsNoGanadores()[i]));
            imagenNoGanadora.setImageResource(miniJuegoActivity.ubicacionDeImagenDeCaballoPorPelaje(this.itemsNoGanadores()[i]));
            imagenNoGanadora.setBackgroundResource(R.drawable.border_red);
            miniJuegoActivity.cargarEventoOnClickParaImagenNoGanadora(imagenNoGanadora);
        }
    }

    /*
        Propósito: carga las palabras NO ganadoras en la parte inferior del layout, donde se interaccionará para jugar
    */
    public void cargarPalabrasNoGanadoras(MiniJuegoActivity miniJuegoActivity) {
        for (int i=0; i<this.cantidadDeItems()-1;i++) {
            TextView palabraNoGanadora = (TextView) miniJuegoActivity.findViewById(this.idTextViewSegunPosicion(this.posicionesItemsNoGanadores()[i]));
            palabraNoGanadora.setText(sanitizarTexto(this.itemsNoGanadores()[i]));
            palabraNoGanadora.setBackgroundResource(R.drawable.border_red);
            miniJuegoActivity.cargarEventoOnClickParaPalabraNoGanadora(palabraNoGanadora);
            ImageButton button = (ImageButton) miniJuegoActivity.findViewById(this.idImageButtonSegunPosicion(this.posicionesItemsNoGanadores()[i]));
            setearOnClick(miniJuegoActivity, button, this.itemsNoGanadores()[i]);
        }
    }

    /*
        Propósito: carga la imagen ganadora de la última jugada
        Precondición: Es la última jugada
    */
    public void cargarImagenGanadoraUltimaJugada (MiniJuegoActivity miniJuegoActivity) {
        ImageView imagenGanadora = (ImageView) miniJuegoActivity.findViewById(this.idImageViewSegunPosicion(this.posicionItemGanador()));
        imagenGanadora.setImageResource(miniJuegoActivity.ubicacionDeImagenDeCaballoPorPelaje(this.itemGanador()));
        imagenGanadora.setBackgroundResource(R.drawable.border_green);
        miniJuegoActivity.cargarEventoOnClickParaImagenGanadoraUltimaJugada(imagenGanadora);
    }

    /*
        Propósito: carga las imagenes NO ganadoras de la última jugada
        Precondición: Es la última jugada
    */
    public void cargarImagenesNoGanadorasUltimaJugada (MiniJuegoActivity miniJuegoActivity) {
        for (int i=0; i<this.cantidadDeItems()-1;i++) {
            ImageView imagenNoGanadora = (ImageView) miniJuegoActivity.findViewById(this.idImageViewSegunPosicion(this.posicionesItemsNoGanadores()[i]));
            imagenNoGanadora.setImageResource(miniJuegoActivity.ubicacionDeImagenDeCaballoPorPelaje(this.itemsNoGanadores()[i]));
            imagenNoGanadora.setBackgroundResource(R.drawable.border_red);
            miniJuegoActivity.cargarEventoOnClickParaImagenNoGanadoraUltimaJugada(imagenNoGanadora);
        }
    }

    /*
        Propósito: carga la palabra ganadora de la última jugada
        Precondición: Es la última jugada
    */
    public void cargarPalabraGanadoraUltimaJugada (MiniJuegoActivity miniJuegoActivity){
        TextView palabraAReconocer = (TextView) miniJuegoActivity.findViewById(this.idTextViewSegunPosicion(this.posicionItemGanador()));
        palabraAReconocer.setText(sanitizarTexto(this.itemGanador()));
        palabraAReconocer.setBackgroundResource(R.drawable.border_green);
        miniJuegoActivity.cargarEventoOnClickParaPalabraGanadoraUltimaJugada(palabraAReconocer);
        ImageButton button = (ImageButton) miniJuegoActivity.findViewById(this.idImageButtonSegunPosicion(this.posicionItemGanador()));
        setearOnClick(miniJuegoActivity, button, this.itemGanador());
    }

    /*
        Propósito: carga las palabras NO ganadoras de la última jugada
        Precondición: Es la última jugada
    */
    public void cargarPalabrasNoGanadorasUltimaJugada (MiniJuegoActivity miniJuegoActivity) {
        for (int i=0; i<this.cantidadDeItems()-1;i++) {
            TextView palabraNoGanadora = (TextView) miniJuegoActivity.findViewById(this.idTextViewSegunPosicion(this.posicionesItemsNoGanadores()[i]));
            palabraNoGanadora.setText(sanitizarTexto(this.itemsNoGanadores()[i]));
            palabraNoGanadora.setBackgroundResource(R.drawable.border_red);
            miniJuegoActivity.cargarEventoOnClickParaPalabraNoGanadoraUltimaJugada(palabraNoGanadora);
            ImageButton button = (ImageButton) miniJuegoActivity.findViewById(this.idImageButtonSegunPosicion(this.posicionesItemsNoGanadores()[i]));
            setearOnClick(miniJuegoActivity, button, this.itemsNoGanadores()[i]);
        }
    }

    protected String sanitizarTexto(String texto) {
        return texto.replace("_", " ").toUpperCase();
    }


}
