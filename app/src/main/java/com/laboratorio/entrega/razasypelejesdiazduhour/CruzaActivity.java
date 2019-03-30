package com.laboratorio.entrega.razasypelejesdiazduhour;

import android.content.Intent;

public class CruzaActivity extends MiniJuegoActivity {


    /*
        Propósito: Crea e iniciar el minijuego "Raza y Pelaje"
        Precondición: Ninguna
     */
    @Override
    public void iniciarMiniJuego() {
        this.miniJuego = new CruzaMiniJuego(this.dificultad);
        this.miniJuego.iniciar();
    }

    @Override
    public void reIniciarMiniJuego() {
        Intent i = new Intent(CruzaActivity.this, CruzaActivity.class);
        startActivity(i);
    }

    @Override
    public void feedbackVisualMiniJuegoGanado() {
        Intent i = new Intent(CruzaActivity.this, MiniJuegoCruzaGanadoActivity.class);
        startActivity(i);
    }

    /*
        Propósito: como no hay siguiente minijuego, regresa a Inicio
    */
    @Override
    public void cargarSiguienteMiniJuego() {
        Intent i = new Intent(CruzaActivity.this, MainActivity.class);
        startActivity(i);
    }

    /*
        Propósito: Selecciona el layout segun configuración
        Precondición: Hay una dificultad e interacción definida
     */
    public void definirLayoutSegunConfiguracion() {
        this.interaccion = new ImagenImagen();
        setContentView(this.dificultad.obtenerLayout(this.interaccion));
    }
}
