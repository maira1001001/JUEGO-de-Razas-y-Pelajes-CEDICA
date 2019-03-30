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

public class GridActivity extends ReconocimientoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconocimiento_grid);
        cargarReconocimiento();
    }

    @Override
    public void cargarReconocimiento() {
        LinearLayout ly = findViewById(R.id.gridRec);

        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        boolean razasypelajes = preferences.getBoolean("razas", true);
        boolean cruzas = preferences.getBoolean("cruzas", false);
        if (razasypelajes && cruzas) {
            armarGrid(ly, ReconocimientoRazasyPelajes.values());
            armarGrid(ly, ReconocimientoCruzas.values());
        } else if (razasypelajes && !cruzas) {
            armarGrid(ly, ReconocimientoRazasyPelajes.values());
        } else if (!razasypelajes && cruzas) {
            armarGrid(ly, ReconocimientoCruzas.values());
        }
    }

    private void armarGrid(LinearLayout ly, Enum[] e) {
        int n = 1;
        LinearLayout l = createLinearLayout();
        for (Enum p : e) {
            int cant = e.length - 1;
            //while (n < 3) {
            LinearLayout l2 = new LinearLayout(getApplicationContext());
            l2.setOrientation(LinearLayout.VERTICAL);
            l2.setGravity(Gravity.CENTER);
            l2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
            l2.setPadding(8, 8, 8, 8);

            // Imagen
            ImageView iv = createImageView(p);
            l2.addView(iv);
            // Text view nombre
            TextView tv = createTextViewName(p);
            l2.addView(tv);
            // Boton sonido
            ImageButton iB = createImageButton(p);
            l2.addView(iB);
            l.addView(l2);
            //}
            if ((n == 3) || (p.ordinal() == cant)) {
                ly.addView(l);
                l = createLinearLayout();
                n = 0;
            }
            n++;
        }
    }

    @NonNull
    private LinearLayout createLinearLayout() {
        LinearLayout l = new LinearLayout(getApplicationContext());
        l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        l.setOrientation(LinearLayout.HORIZONTAL);
        l.setWeightSum(3);
        l.setBaselineAligned(false);
        return l;
    }

    private ImageView createImageView(Enum p) {
        ImageView iv = new ImageView(getApplicationContext());
        iv.setImageResource(ubicacionDeImagenDeCaballoPorNombre(p.name()));
        iv.setLayoutParams(new LinearLayout.LayoutParams(250, 250));
        iv.setMaxHeight(250);
        iv.setMaxWidth(250);
        iv.setContentDescription(p.name());
        return iv;
    }

    private TextView createTextViewName(Enum p) {
        TextView tv = new TextView(getApplicationContext());
        tv.setText(p.name());
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);
        return tv;
    }

    private ImageButton createImageButton(Enum p) {
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        String femenino = preferences.getString("audio", "Femenina");
        ImageButton iB = new ImageButton(getApplicationContext());
        iB.setImageResource(R.drawable.audio_button);
        iB.setBackgroundColor(Color.TRANSPARENT);
        iB.setMaxWidth(150);
        iB.setMaxHeight(150);
        iB.setOnClickListener((View v) -> {
            int audio;
            if (femenino.equals("Femenina")) {
                audio = ubicacionDeAudioDeCaballoPorNombreFemenino(p.name());
            } else {
                audio = ubicacionDeAudioDeCaballoPorNombreMasculino(p.name());
            }
            MediaPlayer mediaPlayer = MediaPlayer.create(GridActivity.this, audio);
            mediaPlayer.start();
        });
        return iB;
    }
}
