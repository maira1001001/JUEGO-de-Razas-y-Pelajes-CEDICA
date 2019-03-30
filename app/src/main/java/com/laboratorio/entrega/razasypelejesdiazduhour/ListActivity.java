package com.laboratorio.entrega.razasypelejesdiazduhour;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * carga la lista del modo reconocimiento
 */
public class ListActivity extends ReconocimientoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconocimiento);
        cargarReconocimiento();
    }

    @Override
    public void cargarReconocimiento() {
        LinearLayout lista = findViewById(R.id.listaRec);
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        boolean razasypelajes = preferences.getBoolean("razas", true);
        boolean cruzas = preferences.getBoolean("cruzas", false);

        if (razasypelajes && cruzas) {
            for (ReconocimientoRazasyPelajes rP : ReconocimientoRazasyPelajes.values()) {
                armarLista(lista, rP);
            }
            for (ReconocimientoCruzas c : ReconocimientoCruzas.values()) {
                armarLista(lista, c);
            }
        } else if (razasypelajes && !cruzas) {
            for (ReconocimientoRazasyPelajes rP : ReconocimientoRazasyPelajes.values()) {
                armarLista(lista, rP);
            }
        } else if (!razasypelajes && cruzas) {
            for (ReconocimientoCruzas c : ReconocimientoCruzas.values()) {
                armarLista(lista, c);
            }
        }
    }


    private void armarLista(LinearLayout lista, Enum e) {
        LinearLayout l = new LinearLayout(getApplicationContext());
        l.setOrientation(LinearLayout.HORIZONTAL);
        l.setGravity(Gravity.CENTER);
        l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        l.setWeightSum(3);
        l.setPadding(5, 5, 5, 5);

        //image
        ImageView iv = createImageView(e);

        l.addView(iv);

        //nombre y boton audio
        LinearLayout l2 = new LinearLayout(getApplicationContext());
        l2.setOrientation(LinearLayout.VERTICAL);
        l2.setLayoutParams(new LinearLayout.LayoutParams(20, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        l2.setGravity(Gravity.CENTER);

        //textView nombre
        TextView tv = createTextViewName(e);
        l2.addView(tv);

        //boton audio
        ImageButton iB = createImageButton(e);

        l2.addView(iB);
        l.addView(l2);

        //descripcion
        TextView tv2 = createTextViewDescription(e);
        l.addView(tv2);

        //agrego a la lista
        lista.addView(l);
    }

    @NonNull
    private ImageView createImageView(Enum p) {
        ImageView iv = new ImageView(getApplicationContext());
        iv.setImageResource(ubicacionDeImagenDeCaballoPorNombre(p.name()));
        iv.setLayoutParams(new LinearLayout.LayoutParams(200, 200, 1.0f));
        iv.setMaxHeight(200);
        iv.setMaxWidth(200);
        iv.setContentDescription(p.name());
        return iv;
    }

    @NonNull
    private TextView createTextViewName(Enum p) {
        TextView tv = new TextView(getApplicationContext());
        tv.setText(p.name());
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);
        return tv;
    }

    @NonNull
    private ImageButton createImageButton(Enum p) {
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        String femenino = preferences.getString("audio", "Femenina");
        ImageButton iB = new ImageButton(getApplicationContext());
        iB.setImageResource(R.drawable.audio_button);
        iB.setBackgroundColor(Color.TRANSPARENT);
        iB.setMaxWidth(101);
        iB.setMaxHeight(79);
        iB.setOnClickListener((View v) -> {
            int audio;
            if (femenino.equals("Femenina")) {
                audio = ubicacionDeAudioDeCaballoPorNombreFemenino(p.name());
            } else {
                audio = ubicacionDeAudioDeCaballoPorNombreMasculino(p.name());
            }
            MediaPlayer mediaPlayer = MediaPlayer.create(ListActivity.this, audio);
            mediaPlayer.start();
        });
        return iB;
    }

    @NonNull
    private TextView createTextViewDescription(Enum p) {
        TextView tv2 = new TextView(getApplicationContext());
        tv2.setText(descripcionDeCaballoPorNombre(p.name()));
        tv2.setLayoutParams(new LinearLayout.LayoutParams(57, 94, 1.0f));
        tv2.setGravity(Gravity.CENTER);
        tv2.setTextColor(Color.BLACK);
        tv2.setMaxWidth(57);
        tv2.setMaxHeight(94);
        return tv2;
    }

    /*
    private Enum aplicarFiltros() {
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        boolean razasypelajes = preferences.getBoolean("razas", true);
        boolean cruzas preferences.getBoolean("cruzas", false);
    }
    */
}
