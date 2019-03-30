package com.laboratorio.entrega.razasypelejesdiazduhour;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class ReconocimientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public abstract void cargarReconocimiento();
    /*

        Propósito: Evento del boton volver que regresa a la pantalla de Principal (MainActivity)
    */
    public void volver(View view) {
        finish();
    }

    protected int ubicacionDeImagenDeCaballoPorNombre(String nombre) {
        switch (nombre.toUpperCase()) {
            case "AMAPOLA": return R.drawable.amapola_petiso_argentino_rosillo;
            case "AMBAR": return R.drawable.ambar_mestizo_cruza_arabe_alazan_tostado;
            case "ANGOLA": return  R.drawable.angola_sangrepuracarrera_zaino_colorado;
            case "ANTU": return R.drawable.antu_mestizo_overo_zaino;
            case "BELLA": return R.drawable.bella;
            case "BIONDA": return R.drawable.bionda_mestizo_alazan_ruano;
            case "BLANCO": return R.drawable.blanco_mestizo_blanco;
            case "BONITA": return R.drawable.bonita_mestizo_qh_con_criollo_overo_azulejo;
            case "CACIQUE": return R.drawable.cacique_mestizo_alazan;
            case "CANDELARIA": return R.drawable.candelaria_mestizo_tobiano;
            case "CIRO": return R.drawable.ciro_mestizo_tordillo_canela;
            case "FELIPE": return R.drawable.felipe_mestizo_zaino;
            case "HUALFIN": return R.drawable.hualfin_criollo_picaso;
            case "HUAYRA": return R.drawable.huayra;
            case "JUANA": return R.drawable.juana_cuarto_de_milla_bayo;
            case "MANCHA": return R.drawable.mancha;
            case "MORA": return R.drawable.mora_petiso_argentino_tordillo_moro;
            case "MUÑECO": return R.drawable.muneco_mestizo_overo_rosado;
            case "NALA": return R.drawable.nala_mestizo_moro;
            case "NALITO": return R.drawable.nalito;
            case "ORITA": return R.drawable.orita;
            case "PAMPERITO": return R.drawable.pamperito_petiso_argentino_zaino;
            case "PINTADA": return R.drawable.pintada_mestizo_alazan_pintado;
            case "PIRATA": return R.drawable.pirata;
            case "POCHITO": return R.drawable.pochito_mestizo_zaino;
            case "PRIMAVERA": return R.drawable.primavera_silla_argentino_alazan;
            case "TIGRE": return R.drawable.tigre_criollo_bayo_gateado;
            case "TUPAC": return R.drawable.tupac_mestizo_zaino_oscuro;
            case "ZORZAL": return R.drawable.zorzal_mestizo_tordillo;
            default: return R.drawable.defaultfur;
        }
    }


    protected int ubicacionDeAudioDeCaballoPorNombreFemenino(String nombre) {
        switch (nombre.toUpperCase()) {
            case "AMAPOLA": return R.raw.petiso_argentino_rosillo;
            case "AMBAR": return R.raw.mestizo_cruza_arabe_alazan_tostado;
            case "ANGOLA": return  R.raw.sangre_pura_de_carrera_zaina_colorada;
            case "ANTU": return R.raw.mestizo_overo_zaino;
            case "BELLA": return R.raw.relincho;
            case "BIONDA": return R.raw.mestizo_alazan_ruano;
            case "BLANCO": return R.raw.blanco;
            case "BONITA": return R.raw.mestizo_cuarto_de_milla_overo_azulejo;
            case "CACIQUE": return R.raw.mestizo_alazan;
            case "CANDELARIA": return R.raw.mestizo_tobiano;
            case "CIRO": return R.raw.mestizo_tordillo_canela;
            case "FELIPE": return R.raw.mestizo_zaino;
            case "HUALFIN": return R.raw.criollo;
            case "HUAYRA": return R.raw.relincho;
            case "JUANA": return R.raw.cuarto_de_milla_bayo;
            case "MANCHA": return R.raw.relincho;
            case "MORA": return R.raw.petiso_argentino_mora;
            case "MUÑECO": return R.raw.mestizo_overo_rosado;
            case "NALA": return R.raw.mestizo_mora;
            case "NALITO": return R.raw.relincho;
            case "ORITA": return R.raw.relincho;
            case "PAMPERITO": return R.raw.zaino;
            case "PINTADA": return R.raw.mestizo_alazan_pintado;
            case "PIRATA": return R.raw.relincho;
            case "POCHITO": return R.raw.mestizo_zaino;
            case "PRIMAVERA": return R.raw.silla_argentino_alazan;
            case "TIGRE": return R.raw.bayo_gateado;
            case "TUPAC": return R.raw.mestizo_zaino_oscuro;
            case "ZORZAL": return R.raw.mestizo_tordillo;
            default: return R.raw.relincho;
        }
    }
    protected int ubicacionDeAudioDeCaballoPorNombreMasculino(String nombre) {
        switch (nombre.toUpperCase()) {
            case "AMAPOLA": return R.raw.nombre_amapola;
            case "AMBAR": return R.raw.nombre_ambar;
            case "ANGOLA": return  R.raw.nombre_angola;
            case "ANTU": return R.raw.nombre_antu;
            case "BELLA": return R.raw.relincho;
            case "BIONDA": return R.raw.nombre_bionda;
            case "BLANCO": return R.raw.nombre_blanco;
            case "BONITA": return R.raw.nombre_bonita;
            case "CACIQUE": return R.raw.nombre_cacique;
            case "CANDELARIA": return R.raw.nombre_candelaria;
            case "CIRO": return R.raw.nombre_ciro;
            case "FELIPE": return R.raw.nombre_felipe;
            case "HUALFIN": return R.raw.nombre_hualfin;
            case "HUAYRA": return R.raw.relincho;
            case "JUANA": return R.raw.nombre_juana;
            case "MANCHA": return R.raw.relincho;
            case "MORA": return R.raw.nombre_mora;
            case "MUÑECO": return R.raw.nombre_muneco;
            case "NALA": return R.raw.nombre_nala;
            case "NALITO": return R.raw.relincho;
            case "ORITA": return R.raw.relincho;
            case "PAMPERITO": return R.raw.nombre_pamperito;
            case "PINTADA": return R.raw.nombre_pintada;
            case "PIRATA": return R.raw.relincho;
            case "POCHITO": return R.raw.relincho;
            case "PRIMAVERA": return R.raw.nombre_primavera;
            case "TIGRE": return R.raw.nombre_tigre;
            case "TUPAC": return R.raw.nombre_tupac;
            case "ZORZAL": return R.raw.nombre_zorzal;
            default: return R.raw.relincho;
        }
    }

    //ubicacionDeDescripcionDeCaballoPorNombre
    protected int descripcionDeCaballoPorNombre(String nombre) {
        switch (nombre.toUpperCase()) {
            case "AMAPOLA": return R.string.amapola;
            case "AMBAR": return R.string.ambar;
            case "ANGOLA": return  R.string.angola;
            case "ANTU": return R.string.antu;
            case "BELLA": return R.string.bella;
            case "BIONDA": return R.string.bionda;
            case "BLANCO": return R.string.blanco_2;
            case "BONITA": return R.string.bonita;
            case "CACIQUE": return R.string.cacique;
            case "CANDELARIA": return R.string.candelaria;
            case "CIRO": return R.string.ciro;
            case "FELIPE": return R.string.felipe;
            case "HUALFIN": return R.string.hualfin;
            case "HUAYRA": return R.string.huayra;
            case "JUANA": return R.string.juana;
            case "MANCHA": return R.string.mancha;
            case "MORA": return R.string.mora;
            case "MUÑECO": return R.string.muneco;
            case "NALA": return R.string.nala;
            case "NALITO": return R.string.nalito;
            case "ORITA": return R.string.orita;
            case "PAMPERITO": return R.string.pamperito;
            case "PINTADA": return R.string.pintada;
            case "PIRATA": return R.string.pirata;
            case "POCHITO": return R.string.pochito;
            case "PRIMAVERA": return R.string.primavera;
            case "TIGRE": return R.string.tigre;
            case "TUPAC": return R.string.tupac;
            case "ZORZAL": return R.string.zorzal;
            default: return R.string.lorem;
        }
    }
}
