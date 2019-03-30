package com.laboratorio.entrega.razasypelejesdiazduhour;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public abstract class MiniJuegoActivity extends AppCompatActivity {


    protected MiniJuego miniJuego;
    protected MediaPlayer sonidoRelincheCaballo, sonidoResoplidoCaballo;
    protected Dificultad dificultad; //la dificultad puede ser Facil o Dificil. El primero corresponde a la subclase DificultadFacil y el segundo corresponde a la subclase DificultadDificil
    protected Interaccion interaccion; //la interacción puede ser Imagen-Palabra, PalabraImagen o Imagen-Imagen y cada una corresponde con una subclase.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setearConfiguracion();
        definirLayoutSegunConfiguracion();
        iniciarMiniJuego();
        cargarElementosEnLayout();
    }

    /**
     * Propósito: define la configuración para el tipo de interacción y el tipo de dificultad
     * Precondición: Ninguna
     */
    public void setearConfiguracion() {
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        //    definirDificultadSegunConfiguracion();
        boolean esDificultadFacil = preferences.getString("dificultad", "Facil").compareTo("Facil") == 0;
        this.dificultad = esDificultadFacil ? new Facil() : new Dificil();

        //    definirInteraccionSegunConfiguracion();
        boolean esImagenPalabra = preferences.getBoolean("imagen-palabra", true);
        boolean esPalabraImagen = preferences.getBoolean("palabra-imagen", false);
        boolean esImagenImagen = preferences.getBoolean("imagen-imagen", false);
        if (esImagenPalabra) {
            this.interaccion = new ImagenPalabra();
        } else if (esPalabraImagen) {
            this.interaccion = new PalabraImagen();
        } else {
            this.interaccion = new ImagenImagen();
        }
    }

    /*
        Propósito: crea  e iniciar un miniJuego
        Precondicion: Hay una interaccion y dificultad definidas
     */
    public abstract void iniciarMiniJuego();

    /*
        Propósito: Selecciona el layout segun configuración
        Precondición: Hay una dificultad e interacción definida
        Observación: Existen 4 (cuatro) tipos de layouts:
                        1. layout imagen_palabra_facil
                        2. layout imagen_palabra_dificil
                        3. layout palabra_imagen_facil
                        4. layout palabra_imagen_dificil
     */
    public void definirLayoutSegunConfiguracion() {
        setContentView(this.dificultad.obtenerLayout(this.interaccion));
    }


    /*
     * Propósito: Carga el nombre del minijuego, la jugada actual y el feedback sonoro
     * Observación: "cargar" hace referencia a la accion que muestra un recurso (imagen, texto, sonido) necesario en la pantalla
     */
    public void cargarElementosEnLayout() {
        cargarNombreDelMiniJuego();
        cargarJugadaActual();
        cargarFeedbackSonoroCaballo();
    }

    /*
     * Propósito: carga el nombre del juego ("Raza y Pelaje" , "Razas y Pelajes juntas", "Cruza)
     * * Precondición: Hay un minijuego inicializado y un layout definido
     */
    public void cargarNombreDelMiniJuego() {
        TextView nombreDelMiniJuego = (TextView) findViewById(R.id.nombreDelMiniJuego);
        nombreDelMiniJuego.setText(this.miniJuego.tipoDeJuego());
    }

    /*
     * Propósito: carga el sonido del caballo que representa el mensaje de una interacción con el minijuego
     * Precondición: Existe una judaga cargada
     */
    public void cargarFeedbackSonoroCaballo() {
        sonidoRelincheCaballo = MediaPlayer.create(this, R.raw.relincho);
        sonidoResoplidoCaballo = MediaPlayer.create(this, R.raw.resoplido);
    }

    /*
     * Propósito: Carga la jugada actual compuesta por el Item principal (ubicado en la parte superior) y los items con los que se interaccionará (ubicados en la parte inferior)
     * Precondición: Hay un minijuego inicializado y un layout definido
     */
    public void cargarJugadaActual() {
        cargarItemAReconocerDeLaJugadaActual();
        cargarItemsAInteraccionarDeLaJugadaActual();
    }

    public Jugada jugadaActual() {
        return this.miniJuego.jugadaActual();
    }
    /*
        Propósito: Carga el item a reconoer de la jugada actual, ubicado en la parte superior
        Obsevación: el item a reconocer se encuentra en la parte superior del minijuego
     */
    public void cargarItemAReconocerDeLaJugadaActual() {
        this.interaccion.cargarItemAReconocer(this.dificultad, this, this.jugadaActual());
    }

    /*
        Propósito: Carga los items los que se interaccionará según la configuración
        Precondición: Hay una jugada actual definida
        Observación: "items a interaccionar": texto o imagen que se encuentra en la parte inferior del minijuego
     */
    public void cargarItemsAInteraccionarDeLaJugadaActual() {
        if (this.esUltimaJugada()) {
            cargarItemGanadorUltimaJugada();
            cargarItemsNoGanadoresUltimaJugada();
        } else {
            cargarItemGanador();
            cargarItemsNoGanadores();
        }
    }

    /*
        Propósito: describe verdadero si es la ultima jugada del minijuego, es decir es el desafío #5
        Precondición: hay una jugada actual definida
     */
    protected boolean esUltimaJugada() {
        return this.miniJuego.esUltimaJugada();
    }

    /*
      Propósito: Carga el item  ganador en la posición correspondiente.
      Precondición: Hay una jugada actual definida
     */
    public void cargarItemGanador() {
        this.interaccion.cargarItemGanador(this.dificultad, this, this.jugadaActual());
    }

    /*
        Propósito: Carga los items  NO ganadores en sus posición correspondientes.
        Precondición: hay una jugada actual definida.
     */
    public void cargarItemsNoGanadores() {
        this.interaccion.cargarItemsNoGanadores(this.dificultad, this, this.jugadaActual());
    }

    /*
        Propósito: Carga el item  ganador en la posición correspondiente que corresponde con el último desafío.
        Precondición: Hay una jugada actual definida
    */
    public void cargarItemGanadorUltimaJugada() {
        this.interaccion.cargarItemGanadorUltimaJugada(this.dificultad, this, this.jugadaActual());
    }

    /*
        Propósito: Carga los items NO ganadores que corresponde con el último desafío.
        Precondición: Hay una jugada actual definida
    */
    public void cargarItemsNoGanadoresUltimaJugada() {
        this.interaccion.cargarItemsNoGanadoresUltimaJugada(this.dificultad, this, this.jugadaActual());
    }

    /*
        Propósito: Carga la próxima jugada para la jugada actual
        Precondición: Existe una próxima jugada en el minijuego
     */
    public void cargarJugadaSiguiente() {
        this.miniJuego.irAProximaJugada();
        this.cargarJugadaActual();
    }

    /*
        Propósito: Regresa a la pantalla de Principal (MainActivity)
    */
    public void irAPantallaPrincipal() {
        Intent i = new Intent(MiniJuegoActivity.this, MainActivity.class);
        startActivity(i);
    }

    /*
        Propósito: carga el siguiente minijuego
     */
    public abstract  void cargarSiguienteMiniJuego();

    /*
        Propósito: Evento del boton volver que regresa a la pantalla de Principal (MainActivity)
    */
    public void volverAPantallaPrincipal(View view) {
        Intent i = new Intent(MiniJuegoActivity.this, MainActivity.class);
        startActivity(i);
    }

    /*
        Propósito: Reinicia el MiniJuego
        Precondición: Ninguna
    */
    public abstract void reIniciarMiniJuego();


    /*
        Propósito: Emite el sonido de resoplo de un caballo  que representa una interacción NO acertada para la jugada actual.
        Precondición: hay una jugada cargada
    */
    public void feedbackSonoroJugadaNoGanada() { sonidoResoplidoCaballo.start(); }

    /*
        Propósito: Emite el sonido de un caballo relinchando, que representa una interacción Acertada para la jugada actual.
        Precondición: hay una jugada cargada
    */
    public void feedbackSonoroJugadaGanada() {
        sonidoRelincheCaballo.start();
    }

    /*
        Propósito: genera un feedback visual para el minijuego ganado (papel picado o copa)
        Precondición: hay un minijuego ganado
     */
    public abstract void feedbackVisualMiniJuegoGanado();

    /*
       Propósito: genera un feedback cuando se finalizó el minijuego y no se ha ganado, dandole las opciones de
                   * Ir a Inicio
                   * Reanudar minijuego
       Precondición: Hay un minijuego no ganado
    */
    public void feedbackVisualMiniJuegoNoGanado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("MiniJuego no ganado").setCancelable(false);
        builder.setPositiveButton("Ir al inicio", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                irAPantallaPrincipal();
            }
        });
        builder.setNeutralButton("Reanudar juego", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                reIniciarMiniJuego();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /*
        Propósito: genera un feedback para la última jugada ganada
        Precondición: Es la última jugada
     */
    public void feedbackVisualUltimaJugada(){
        if (esMiniJuegoGanado()) {
            feedbackVisualMiniJuegoGanado();
        } else {
            feedbackVisualMiniJuegoNoGanado();
        }
    }

    /*
        Propósito: describe el tipo de juego
     */
    public String tipoDeJuego() {
        return this.miniJuego.tipoDeJuego();
    }

    /*
        Propósito: incrementa en uno la jugada actual
        Precondición: Hay un acierto en la jugada actual, es decir, se ganó la jugada actual
     */
    public void incrementarJugadaGanada(){
        this.miniJuego.incrementarJugadaGanada();
    }


    /*
    Propósito: Carga el evento OnClick para la imagen ganadora
    Precondición: Hay una imagen ganadora cargada en el layout correspondiente y no es la última jugada
*/
    public void cargarEventoOnClickParaImagenGanadora (final ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackSonoroJugadaGanada();
                incrementarJugadaGanada();
                SystemClock.sleep(3000);
                cargarJugadaSiguiente();
            }
        });
    }

    /*
        Propósito: Carga el evento OnClick para la palabra ganadora
        Precondición: Hay una palabra ganadora cargada en el layout correspondiente y no es la última jugada
    */
    public void cargarEventoOnClickParaPalabraGanadora (final TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackSonoroJugadaGanada();
                incrementarJugadaGanada();
                SystemClock.sleep(3000);
                cargarJugadaSiguiente();
            }
        });
    }

    /*
       Propósito: Carga el evento OnClick para la imagen no ganadora
       Precondición: Hay una imagen No ganadora cargada en el layout correspondiente y no es la última jugada
   */
    public void cargarEventoOnClickParaImagenNoGanadora (final ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackSonoroJugadaNoGanada();
                SystemClock.sleep(3000);
                cargarJugadaSiguiente();
            }
        });
    }

    /*
        Propósito: Carga el evento OnClick para la palabra No ganadora
        Precondición: Hay una palabra No ganadora cargada en el layout correspondiente y no es la última jugada
    */
    public void cargarEventoOnClickParaPalabraNoGanadora (final TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackSonoroJugadaNoGanada();
                SystemClock.sleep(3000);
                cargarJugadaSiguiente();
            }
        });
    }

    /*
       Propósito: Carga el evento OnClick para la imagen no ganadora
       Precondición: Hay una imagen No ganadora cargada en el layout correspondiente
   */
    public void cargarEventoOnClickParaImagenGanadoraUltimaJugada (final ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackSonoroJugadaGanada();
                SystemClock.sleep(2000);
                incrementarJugadaGanada();
                feedbackVisualUltimaJugada();
            }
        });
    }

    /*
        Propósito: Carga el evento OnClick para la palabra No ganadora
        Precondición: Hay una palabra No ganadora cargada en el layout correspondiente
    */
    public void cargarEventoOnClickParaPalabraGanadoraUltimaJugada (final TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackSonoroJugadaGanada();
                SystemClock.sleep(2000);
                incrementarJugadaGanada();
                feedbackVisualUltimaJugada();
            }
        });
    }

    /*
       Propósito: Carga el evento OnClick para la imagen no ganadora
       Precondición: Hay una imagen No ganadora cargada en el layout correspondiente
    */
    public void cargarEventoOnClickParaImagenNoGanadoraUltimaJugada (final ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackSonoroJugadaNoGanada();
                SystemClock.sleep(2000);
                feedbackVisualUltimaJugada();
            }
        });
    }

    /*
        Propósito: Carga el evento OnClick para la palabra No ganadora
        Precondición: Hay una palabra No ganadora cargada en el layout correspondiente
    */
    public void cargarEventoOnClickParaPalabraNoGanadoraUltimaJugada (final TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackSonoroJugadaNoGanada();
                SystemClock.sleep(2000);
                feedbackVisualUltimaJugada();
            }
        });
    }

    /*
        Propósito: describe verdadero cuando se ha ganado al menos 3 jugadas o desafíos
        Precondición: Todas las jugadas fueron jugadas
     */
    public boolean esMiniJuegoGanado() {
        return this.miniJuego.esMiniJuegoGanado();
    }


    /*
     * Propósito: describe el número que representa la ubicación de una imagen  a partir de un nombre
     * Parámetro: "nombre" representa el nombre de una imágen ubicada en "res/drawable"
     */
    public int ubicacionDeImagenDeCaballoPorPelaje(String nombrePelaje) {
        switch (nombrePelaje.toUpperCase()) {
            case "ALAZAN":
                return segundaSeleccionRandomAlazan();
            case "ALAZAN_PINTADO":
                return R.drawable.pintada_mestizo_alazan_pintado;
            case "ALAZAN_RUANO":
                return R.drawable.bionda_mestizo_alazan_ruano;
            case "ALAZAN_TOSTADO":
                return R.drawable.ambar_mestizo_cruza_arabe_alazan_tostado;
            case "BAYO":
                return R.drawable.juana_cuarto_de_milla_bayo;
            case "BAYO_GATEADO":
                return R.drawable.tigre_criollo_bayo_gateado;
            case "BLANCO":
                return R.drawable.blanco_mestizo_blanco;
            case "MORO":
                return R.drawable.nala_mestizo_moro;
            case "OVERO_AZULEJO":
                return R.drawable.bonita_mestizo_qh_con_criollo_overo_azulejo;
            case "OVERO_ROSADO":
                return R.drawable.muneco_mestizo_overo_rosado;
            case "OVERO_ZAINO":
                return R.drawable.antu_mestizo_overo_zaino;
            case "PICASO":
                return R.drawable.hualfin_criollo_picaso;
            case "TOBIANO":
                return R.drawable.candelaria_mestizo_tobiano;
            case "TORDILLO":
                return R.drawable.zorzal_mestizo_tordillo;
            case "TORDILLO_CANELA":
                return R.drawable.ciro_mestizo_tordillo_canela;
            case "TORDILLO_MORO":
                return R.drawable.mora_petiso_argentino_tordillo_moro;
            case "ZAINO":
                return segundaSeleccionRandomZaino();
            case "ZAINO_COLORADO":
                return R.drawable.angola_sangrepuracarrera_zaino_colorado;
            case "ZAINO_OSCURO":
                return R.drawable.tupac_mestizo_zaino_oscuro;
            default:
                return R.drawable.defaultfur;
        }
    }

    public int ubicacionDeImagenDeCaballoPorRaza(String nombreRaza) {
        switch (nombreRaza.toUpperCase()) {
            case "CUARTO_DE_MILLA":
                return R.drawable.juana_cuarto_de_milla_bayo;
            case "CRIOLLO":
                return segundaSeleccionRandomCriollo();
            case "PETISO_ARGENTINO":
                return segundaSeleccionRandomPetisoArgentino();
            case "MESTIZO_CRUZA_ARABE":
                return R.drawable.ambar_mestizo_cruza_arabe_alazan_tostado;
            case "MESTIZO":
                return segundaSeleccionRandomMestizo();
            case "SILLA_ARGENTINO":
                return R.drawable.primavera_silla_argentino_alazan;
            case "SANGRE_PURO_CARRERA":
                return R.drawable.angola_sangrepuracarrera_zaino_colorado;
             default:
                 return R.drawable.defaultfur;
        }
    }

    public int ubicacionDeImagenDeCaballoPorRazayPelaje(String nombre) {
        switch (nombre.toUpperCase()) {
            case "PETISO_ARGENTINO_FIN_ROSILLO":
                return R.drawable.amapola_petiso_argentino_rosillo;
            case "MESTIZO_CRUZA_ARABE_FIN_ALAZAN_TOSTADO":
                return R.drawable.ambar_mestizo_cruza_arabe_alazan_tostado;
            case "SANGRE_PURA_CARRERA_FIN_ZAIN_COLORADO":
                return R.drawable.angola_sangrepuracarrera_zaino_colorado;
            case "MESTIZO_OVERO_FIN_ZAINO":
                return R.drawable.antu_mestizo_overo_zaino;
            case "MESTIZO_FIN_ALAZAN_RUANO":
                return R.drawable.bionda_mestizo_alazan_ruano;
            case "MESTIZO_FIN_BLANCO":
                return R.drawable.blanco_mestizo_blanco;
            case "MESTIZO_QH_CON_CRIOLLO_FIN_OVERO_AZULEJO":
                return R.drawable.bonita_mestizo_qh_con_criollo_overo_azulejo;
            case "MESTIZO_FIN_ALAZAN":
                return R.drawable.cacique_mestizo_alazan;
            case "MESTIZO_FIN_TOBIANO":
                return R.drawable.candelaria_mestizo_tobiano;
            case "MESTIZO_FIN_TORDILLO_CANELA":
                return R.drawable.ciro_mestizo_tordillo_canela;
            case "MESTIZO_FIN_ZAINO":
                return R.drawable.pochito_mestizo_zaino;
            case "CRIOLLO_FIN_PICASO":
                return R.drawable.hualfin_criollo_picaso;
            case "CURATO_DE_MILLA_FIN_BAYO":
                return R.drawable.juana_cuarto_de_milla_bayo;
            case "PETISO_ARGENTINO_FIN_TORDILLO_MORO":
                return R.drawable.mora_petiso_argentino_tordillo_moro;
            case "MESTIZO_FIN_OVERO_ROSADO":
                return R.drawable.muneco_mestizo_overo_rosado;
            case "MESTIZO_FIN_MORO":
                return R.drawable.nala_mestizo_moro;
            case "PETISO_ARGENTINO_FIN_ZAINO":
                return R.drawable.pamperito_petiso_argentino_zaino;
            case "MESTIZO_FIN_ALAZAN_PINTADO":
                return R.drawable.pintada_mestizo_alazan_pintado;
            case "SILLA_ARGENTINO_FIN_ALAZAN":
                return R.drawable.primavera_silla_argentino_alazan;
            case "CRIOLLO_FIN_BAYO_GATEADO":
                return R.drawable.tigre_criollo_bayo_gateado;
            case "MESTIZO_FIN_ZAINO_OSCURO":
                return R.drawable.tupac_mestizo_zaino_oscuro;
            case "MESTIZO_FIN_TORDILLO":
                return R.drawable.zorzal_mestizo_tordillo;
            default:
                return R.drawable.defaultfur;
        }
    }

    public int ubicacionDeImagenDeCaballoPorCruza(String nombreCruza) {
        switch (nombreCruza.toUpperCase()) {
            case "BELLA":
                return R.drawable.bella;
            case "BELLA_PADRES":
                return R.drawable.bella_padres;
            case "HUAYRA":
                return R.drawable.huayra;
            case "HUAYRA_PADRES":
                return R.drawable.huayra_padres;
            case "MANCHA":
                return R.drawable.mancha;
            case "MANCHA_PADRES":
                return R.drawable.mancha_padres;
            case "NALITO":
                return R.drawable.nalito;
            case "NALITO_PADRES":
                return R.drawable.nalito_padres;
            case "ORITA":
                return R.drawable.orita;
            case "ORITA_PADRES":
                return R.drawable.orita_padres;
            case "PIRATA":
                return R.drawable.pirata;
            case "PIRATA_PADRES":
                return R.drawable.pirata_padres;
            default:
                return R.drawable.defaultfur;
        }
    }

    private int segundaSeleccionRandomCriollo() {
        int randomCriollo = Aleatorio.entreMinyMax(1, Criollo.values().length);
        switch (randomCriollo) {
            case 1: return R.drawable.hualfin_criollo_picaso;
            case 2: return R.drawable.tigre_criollo_bayo_gateado;
            default: return R.drawable.defaultfur;
        }
    }

    private int segundaSeleccionRandomPetisoArgentino() {
        int randomPetisoArgentino = Aleatorio.entreMinyMax(1, PetisoArgentino.values().length);
        switch (randomPetisoArgentino) {
            case 1: return R.drawable.mora_petiso_argentino_tordillo_moro;
            case 2: return R.drawable.pamperito_petiso_argentino_zaino;
            case 3: return R.drawable.amapola_petiso_argentino_rosillo;
            default: return R.drawable.defaultfur;
        }
    }

    private int segundaSeleccionRandomMestizo() {
        int randomMestizo = Aleatorio.entreMinyMax(1, Mestizo.values().length);
        switch (randomMestizo) {
            case 1: return R.drawable.ambar_mestizo_cruza_arabe_alazan_tostado;
            case 2: return R.drawable.antu_mestizo_overo_zaino;
            case 3: return R.drawable.bionda_mestizo_alazan_ruano;
            case 4: return R.drawable.blanco_mestizo_blanco;
            case 5: return R.drawable.bonita_mestizo_qh_con_criollo_overo_azulejo;
            case 6: return R.drawable.cacique_mestizo_alazan;
            case 7: return R.drawable.candelaria_mestizo_tobiano;
            case 8: return R.drawable.ciro_mestizo_tordillo_canela;
            case 9: return R.drawable.felipe_mestizo_zaino;
            case 10: return R.drawable.muneco_mestizo_overo_rosado;
            case 11: return R.drawable.nala_mestizo_moro;
            case 12: return R.drawable.pintada_mestizo_alazan_pintado;
            case 13: return R.drawable.pochito_mestizo_zaino;
            case 14: return R.drawable.tupac_mestizo_zaino_oscuro;
            case 15: return R.drawable.zorzal_mestizo_tordillo;
            default: return R.drawable.defaultfur;
        }
    }

    private int segundaSeleccionRandomAlazan() {
        int randomAlazan = Aleatorio.entreMinyMax(1, Alazan.values().length);
        switch (randomAlazan) {
            case 1: return R.drawable.primavera_silla_argentino_alazan;
            case 2: return R.drawable.cacique_mestizo_alazan;
            default: return R.drawable.defaultfur;
        }
    }

    private int segundaSeleccionRandomZaino() {
        int randomZaino = Aleatorio.entreMinyMax(1, Zaino.values().length);
        switch (randomZaino) {
            case 1: return R.drawable.felipe_mestizo_zaino;
            case 2: return R.drawable.pamperito_petiso_argentino_zaino;
            case 3: return R.drawable.pochito_mestizo_zaino;
            default: return R.drawable.defaultfur;
        }
    }

    @NonNull
    public int ubicacionDeAudioDeCaballoPorNombreFemenino(String nombrePalabra) {
        switch (nombrePalabra.toUpperCase()) {
            //Razas
            case "CUARTO_DE_MILLA":
                return R.raw.cuarto_de_milla;
            case "CRIOLLO":
                return R.raw.criollo;
            case "PETISO_ARGENTINO":
                return R.raw.petiso_argentino;
            case "MESTIZO_CRUZA_ARABE":
                return R.raw.mestizo_cruza_arabe;
            case "MESTIZO":
                return R.raw.mestizo;
            case "SILLA_ARGENTINO":
                return R.raw.silla_argentino;
            case "SANGRE_PURO_CARRERA":
                return R.raw.sangre_pura_de_carrera;
            //Pelajes
            case "ALAZAN":
                return R.raw.alazan;
            case "ALAZAN_PINTADO":
                return R.raw.alazan_pintado;
            case "ALAZAN_RUANO":
                return R.raw.alazan_ruano;
            case "ALAZAN_TOSTADO":
                return R.raw.alazan_tostado;
            case "BAYO":
                return R.raw.bayo;
            case "BAYO_GATEADO":
                return R.raw.bayo_gateado;
            case "BLANCO":
                return R.raw.blanco;
            case "MORO":
                return R.raw.mora;
            case "OVERO_AZULEJO":
                return R.raw.overo_azulejo;
            case "OVERO_ROSADO":
                return R.raw.overo_rosado;
            case "OVERO_ZAINO":
                return R.raw.overo_zaino;
            case "PICASO":
                return R.raw.picazo;
            case "TOBIANO":
                return R.raw.tobiano;
            case "TORDILLO":
                return R.raw.tordillo;
            case "TORDILLO_CANELA":
                return R.raw.tordillo_canela;
            case "TORDILLO_MORO":
                return R.raw.tordillo;
            case "ZAINO":
                return R.raw.zaino;
            case "ZAINO_COLORADO":
                return R.raw.zaino_colorado;
            case "ZAINO_OSCURO":
                return R.raw.zaino_oscuro;
            //raza y pelaje
            case "PETISO_ARGENTINO_FIN_ROSILLO":
                return R.raw.petiso_argentino_rosillo;
            case "MESTIZO_CRUZA_ARABE_FIN_ALAZAN_TOSTADO":
                return R.raw.mestizo_cruza_arabe_alazan_tostado;
            case "SANGRE_PURA_CARRERA_FIN_ZAINO_COLORADO":
                return R.raw.sangre_pura_de_carrera_zaina_colorada;
            case "MESTIZO_OVERO_FIN_ZAINO":
                return R.raw.mestizo_overo_zaino;
            case "MESTIZO_FIN_ALAZAN_RUANO":
                return R.raw.mestizo_alazan_ruano;
            case "MESTIZO_FIN_BLANCO":
                return R.raw.mestizo_blanco;
            case "MESTIZO_QH_CON_CRIOLLO_FIN_OVERO_AZULEJO":
                return R.raw.mestizo;
            case "MESTIZO_FIN_ALAZAN":
                return R.raw.mestizo_alazan;
            case "MESTIZO_FIN_TOBIANO":
                return R.raw.mestizo_tobiano;
            case "MESTIZO_FIN_TORDILLO_CANELA":
                return R.raw.mestizo_tordillo_canela;
            case "MESTIZO_FIN_ZAINO":
                return R.raw.mestizo_zaino;
            case "CRIOLLO_FIN_PICASO":
                return R.raw.criollo;
            case "CUARTO_DE_MILLA_FIN_BAYO":
                return R.raw.cuarto_de_milla_bayo;
            case "PETISO_ARGENTINO_FIN_TORDILLO_MORO":
                return R.raw.petiso_argentino;
            case "MESTIZO_FIN_OVERO_ROSADO":
                return R.raw.mestizo_overo_rosado;
            case "MESTIZO_FIN_MORO":
                return R.raw.mestizo_mora;
            case "PETISO_ARGENTINO_FIN_ZAINO":
                return R.raw.petiso_argentino_zaino;
            case "MESTIZO_FIN_ALAZAN_PINTADO":
                return R.raw.mestizo_alazan_pintado;
            case "SILLA_ARGENTINO_FIN_ALAZAN":
                return R.raw.silla_argentino_alazan;
            case "CRIOLLO_FIN_BAYO_GATEADO":
                return R.raw.criollo_gateado;
            case "MESTIZO_FIN_ZAINO_OSCURO":
                return R.raw.mestizo_zaino_oscuro;
            case "MESTIZO_FIN_TORDILLO":
                return R.raw.mestizo_tordillo;
            default:
                return R.raw.relincho;
        }
    }

    @NonNull
    public int ubicacionDeAudioDeCaballoPorNombreMasculino(String nombrePalabra) {
        switch (nombrePalabra.toUpperCase()) {
            //Razas
            case "CUARTO_DE_MILLA":
                return R.raw.raza_cuarto_de_milla;
            case "CRIOLLO":
                return R.raw.raza_criollo;
            case "PETISO_ARGENTINO":
                return R.raw.raza_petiso_argentino;
            case "MESTIZO_CRUZA_ARABE":
                return R.raw.raza_mestizo_cruza_arabe;
            case "MESTIZO":
                return R.raw.raza_mestizo;
            case "SILLA_ARGENTINO":
                return R.raw.raza_silla_argentino;
            case "SANGRE_PURO_CARRERA":
                return R.raw.raza_sangre_pura_de_carrera;
            //Pelajes
            case "ALAZAN":
                return R.raw.pelaje_alazan;
            case "ALAZAN_PINTADO":
                return R.raw.pelaje_alazan_pintado;
            case "ALAZAN_RUANO":
                return R.raw.pelaje_alazan_ruano;
            case "ALAZAN_TOSTADO":
                return R.raw.pelaje_alazan_tostado;
            case "BAYO":
                return R.raw.pelaje_bayo;
            case "BAYO_GATEADO":
                return R.raw.pelaje_bayo;
            case "BLANCO":
                return R.raw.pelaje_blanco;
            case "MORO":
                return R.raw.pelaje_moro;
            case "OVERO_AZULEJO":
                return R.raw.pelaje_overo_azulejo;
            case "OVERO_ROSADO":
                return R.raw.pelaje_overo_rosado;
            case "OVERO_ZAINO":
                return R.raw.pelaje_overo_zaino;
            case "PICASO":
                return R.raw.pelaje_picazo;
            case "TOBIANO":
                return R.raw.pelaje_tobiano;
            case "TORDILLO":
                return R.raw.pelaje_tordillo;
            case "TORDILLO_CANELA":
                return R.raw.pelaje_tordillo_canela;
            case "TORDILLO_MORO":
                return R.raw.pelaje_tordillo;
            case "ZAINO":
                return R.raw.pelaje_zaino;
            case "ZAINO_COLORADO":
                return R.raw.pelaje_zaino_colorado;
            case "ZAINO_OSCURO":
                return R.raw.pelaje_zaino_oscuro;
            default:
                return R.raw.relincho;
        }
    }
}
