package com.laboratorio.entrega.razasypelejesdiazduhour;

public class CruzaMiniJuego extends MiniJuego {

    public CruzaMiniJuego(Dificultad dificultad) {
        super(dificultad);
    }

    @Override
    public String tipoDeJuego() {
        return "Cruza";
    }

    /*
        Propósito: describe las jugadas aleatoreas, definidas por 3 de Razas y 2 de Pelajes ubicadas aleatoreamente
     */
    @Override
    public Jugada[] generarSecuenciaDeJugadasAleatoreas() {
        Jugada [] jugadas = new Jugada[TOTAL_JUGADAS];
        TipoCaballo[] cruzasAleatoreas = TipoCruza.nTiposAleatoreos(TipoCruza.todosLosTiposDeCruza(),TOTAL_JUGADAS);
        for (int i=0; i<this.cantidadTotalDeJugadas();i++) {
            jugadas[i] = new JugadaCruza((TipoCruza) cruzasAleatoreas[i], this.dificultad);
        }
        return this.jugadasRandom(jugadas);
    }
}
