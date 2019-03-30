package com.laboratorio.entrega.razasypelejesdiazduhour;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);

        recognitionSettings(preferences);

        RadioButton rb2 = SettingsActivity.this.findViewById(R.id.radioButton3);
        RadioButton rb3 = SettingsActivity.this.findViewById(R.id.radioButton4);
        RadioButton rb4 = SettingsActivity.this.findViewById(R.id.radioButton5);

        radioGroup(preferences, rb2, rb3, rb4, "imagen-palabra", "palabra-imagen", "imagen-imagen");

        configs(preferences);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveSettings();
    }

    private void recognitionSettings(@NonNull SharedPreferences preferences) {
        Boolean b = preferences.getBoolean("lista", true);
        RadioButton rb;
        if (b) {
            rb = SettingsActivity.this.findViewById(R.id.radioButton);
            rb.setChecked(true);
        } else {
            rb = SettingsActivity.this.findViewById(R.id.radioButton2);
            rb.setChecked(true);
        }

        CheckBox cb1 = SettingsActivity.this.findViewById(R.id.checkBox);
        CheckBox cb2 = SettingsActivity.this.findViewById(R.id.checkBox2);
        cb1.setChecked(preferences.getBoolean("razas", true));
        cb2.setChecked(preferences.getBoolean("cruzas", false));
    }

    private void radioGroup(@NonNull SharedPreferences preferences, RadioButton r1, RadioButton r2, RadioButton r3, String key, String key2, String key3) {
        if (preferences.getBoolean(key, true)) {
            r1.setChecked(true);
        } else if (preferences.getBoolean(key2, false)) {
            r2.setChecked(true);
        } else if (preferences.getBoolean(key3, false)) {
            r3.setChecked(true);
        }
    }

    private void configs(@NonNull SharedPreferences preferences) {
        Switch sw = SettingsActivity.this.findViewById(R.id.switch1);
        String text = preferences.getString("dificultad", "Facil");
        if (text.equals("Facil")) {
            sw.setChecked(false);
        } else {
            sw.setChecked(true);
        }
        sw = SettingsActivity.this.findViewById(R.id.switch2);
        text = preferences.getString("audio", "Femenina");
        if (text.equals("Femenina")) {
            sw.setChecked(true);
        } else {
            sw.setChecked(false);
        }
    }

    public void saveSettings() {
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        saveRecognition(editor);

        RadioGroup rg = SettingsActivity.this.findViewById(R.id.radioGroup8);
        int radioId = rg.getCheckedRadioButtonId();
        RadioButton rb = SettingsActivity.this.findViewById(radioId);
        String text = (String) rb.getText();
        switch (text) {
            case "Razas y Pelajes: Imagen-Palabra":
                editor.putBoolean("imagen-palabra", true);
                editor.putBoolean("palabra-imagen", false);
                editor.putBoolean("imagen-imagen", false);
                break;
            case "Razas y Pelajes: Palabra-Imagen":
                editor.putBoolean("palabra-imagen", true);
                editor.putBoolean("imagen-palabra", false);
                editor.putBoolean("imagen-imagen", false);
                break;
            case "Cruza: Imagen-Imagen":
                editor.putBoolean("imagen-imagen", true);
                editor.putBoolean("palabra-imagen", false);
                editor.putBoolean("imagen-palabra", false);
                break;
            default:
                break;
        }

        saveSwitch(editor);

        editor.apply();
        finish();
    }

    private void saveRecognition(@NonNull SharedPreferences.Editor editor) {
        RadioGroup rg = SettingsActivity.this.findViewById(R.id.radioGroup7);
        int radioId = rg.getCheckedRadioButtonId();
        RadioButton rb = SettingsActivity.this.findViewById(radioId);
        if ((rb.getText()).toString().compareTo("Lista") == 0) {
            editor.putBoolean("lista", true);
            editor.putBoolean("grilla", false);
        } else {
            editor.putBoolean("grilla", true);
            editor.putBoolean("lista", false);
        }
        CheckBox cb1 = SettingsActivity.this.findViewById(R.id.checkBox);
        CheckBox cb2 = SettingsActivity.this.findViewById(R.id.checkBox2);
        editor.putBoolean("razas", cb1.isChecked());
        editor.putBoolean("cruzas", cb2.isChecked());
    }

    private void saveSwitch(@NonNull SharedPreferences.Editor editor) {
        Switch sw = SettingsActivity.this.findViewById(R.id.switch1);
        if (sw.isChecked()) {
            editor.putString("dificultad", (String) sw.getTextOn());
        } else {
            editor.putString("dificultad", (String) sw.getTextOff());
        }
        sw = SettingsActivity.this.findViewById(R.id.switch2);
        if (sw.isChecked()) {
            editor.putString("audio", (String) sw.getTextOn());
        } else {
            editor.putString("audio", (String) sw.getTextOff());
        }
    }
}
