package com.laboratorio.entrega.razasypelejesdiazduhour;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Activity act = this;

        ImageButton aboutButton = act.findViewById(R.id.imageButton2);
        aboutButton.setOnClickListener((View v) -> {
                    AlertDialog.Builder about = new AlertDialog.Builder(act);
                    about.setView(R.layout.about_dialog);
                    about.setCancelable(false)
                            .setPositiveButton(R.string.about_dialog_btn, (DialogInterface dialog, int which) ->
                                    dialog.dismiss()
                            );
                    AlertDialog alert = about.create();
                    alert.setTitle(R.string.about_title);
                    alert.show();
                }
        );
    }

    /**
     * Called when the user taps the "Jugar" button
     */
    public void miniJuego(View view) {
        Intent i = new Intent(MainActivity.this, RazaYPelajeActivity.class);
        startActivity(i);
    }

    /**
     * Called when the user taps the "Reconocimiento" button
     */
    public void playRecognitionMode(View view) {
        Intent i;
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        boolean b = preferences.getBoolean("lista", true);
        if (b) {
            i = new Intent(MainActivity.this, ListActivity.class);
        } else {
            i = new Intent(MainActivity.this, GridActivity.class);
        }
        startActivity(i);
    }

    /**
     * Called when the user taps the icon "configuration"
     */
    public void settings(View view) {
        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(i);
    }

}


