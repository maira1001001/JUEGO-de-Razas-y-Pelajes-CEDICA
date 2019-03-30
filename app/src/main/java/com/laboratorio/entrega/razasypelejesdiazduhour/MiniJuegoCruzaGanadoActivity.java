package com.laboratorio.entrega.razasypelejesdiazduhour;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class MiniJuegoCruzaGanadoActivity extends AppCompatActivity {

    private VideoView videoTrofeoBg;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_juego_cruza_ganado);
        cargarVideoConfeti();

    }

    public void irAInicio(View view) {
        Intent i = new Intent(MiniJuegoCruzaGanadoActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void cargarVideoConfeti(){
        videoTrofeoBg = (VideoView) findViewById(R.id.trofeoBg);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.trofeo);
        videoTrofeoBg .setVideoURI(uri);
        videoTrofeoBg .start();
    }

}
