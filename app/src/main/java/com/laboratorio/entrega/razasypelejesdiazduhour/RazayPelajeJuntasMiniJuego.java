package com.laboratorio.entrega.razasypelejesdiazduhour;

public class RazayPelajeJuntasMiniJuego extends MiniJuego {

    public RazayPelajeJuntasMiniJuego(Dificultad dificultad) {
        super(dificultad);
    }

    @Override
    public String tipoDeJuego() {
        return "Raza y Pelaje Juntas";
    }

    /*
        Propósito: describe las jugadas aleatoreas, definidas por 3 de Razas y 2 de Pelajes ubicadas aleatoreamente
    */
    @Override
    public Jugada[] generarSecuenciaDeJugadasAleatoreas() {
        Jugada [] jugadas = new Jugada[TOTAL_JUGADAS];
        TipoCaballo[] razasyPelajesAleatoreos = TipoRazayPelaje.nTiposAleatoreos(TipoRazayPelaje.todosLosTiposDeRazasyPelajes(),5);
        for (int i=0; i<this.cantidadTotalDeJugadas();i++) {
            jugadas[i] = new JugadaRazayPelaje((TipoRazayPelaje) razasyPelajesAleatoreos[i], this.dificultad);
        }
        return this.jugadasRandom(jugadas);
    }
}
