package com.laboratorio.entrega.razasypelejesdiazduhour;

public class RazayPelajeMinijuego extends MiniJuego {

    /*
        Cosntructor de clase
     */
    public RazayPelajeMinijuego(Dificultad dificultad) {
        super(dificultad);
    }

    @Override
    public String tipoDeJuego() {
        return "Raza y Pelaje";
    }

    /*
        Propósito: describe las jugadas aleatoreas, definidas por 3 de Razas y 2 de Pelajes ubicadas aleatoreamente
     */
    @Override
    public Jugada[] generarSecuenciaDeJugadasAleatoreas() {
        Jugada [] jugadas = new Jugada[TOTAL_JUGADAS];
        TipoCaballo[] razasAleatoreas = TipoCaballo.nTiposAleatoreos(TipoRaza.todosLosTiposDeRazas(),this.cantidadDeJugadasDeRaza());
        TipoCaballo[] pelajesAleatoreos = TipoCaballo.nTiposAleatoreos(TipoPelaje.todosLosTiposDePelajes(), this.cantidadDeJugadasDePelaje());
        for (int i=0; i<this.cantidadDeJugadasDeRaza();i++) {
            jugadas[i] = new JugadaRaza((TipoRaza) razasAleatoreas[i], this.dificultad);
        }
        for (int i=0; i<this.cantidadDeJugadasDePelaje();i++) {
            jugadas[i+this.cantidadDeJugadasDeRaza()] = new JugadaPelaje((TipoPelaje) pelajesAleatoreos[i], this.dificultad);
        }
        return this.jugadasRandom(jugadas);
    }

    private int cantidadDeJugadasDeRaza() {
        return 3;
    }

    private int cantidadDeJugadasDePelaje() {
        return 2;
    }

}
