package com.laboratorio.entrega.razasypelejesdiazduhour;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class MiniJuegoRazayPelajeGanadoActivity extends AppCompatActivity {

    private VideoView videoConfetiBg;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_juego_razay_pelaje_ganado);
        cargarVideoConfeti();

    }

    public void irAInicio(View view) {
        Intent i = new Intent(MiniJuegoRazayPelajeGanadoActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void cargarVideoConfeti(){
        videoConfetiBg = (VideoView) findViewById(R.id.confetiBg);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.confeti);
        videoConfetiBg.setVideoURI(uri);
        videoConfetiBg.start();
    }

    public void irAProximoJuego(View view) {
        Intent i = new Intent(MiniJuegoRazayPelajeGanadoActivity.this, RazayPelajeJuntasActivity.class);
        startActivity(i);
    }
}
