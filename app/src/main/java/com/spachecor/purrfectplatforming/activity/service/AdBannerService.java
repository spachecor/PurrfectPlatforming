package com.spachecor.purrfectplatforming.activity.service;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.WindowMetrics;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

/**
 * Clase AdBannerService que gestiona la configuración y el funcionamiento del banner de anuncios
 * @author Selene
 * @version 1.0
 */
public class AdBannerService {
    private final Activity activity;
    private AdView adView;
    private FrameLayout frameLayout;
    private final Handler handler;
    private static final Integer TIME_ADV = 25000;

    public AdBannerService(Activity activity){
        this.activity = activity;
        this.handler = new Handler(Looper.getMainLooper());
    }

    /**
     * Funcion que configura y carga el banner de anuncio en el contenedor pasado por parámetros.
     * Cierra el anuncio automaticamente pasado el tiempo definido en la constante TIME_ADV
     * @param frameLayout El contenedor donde irá el anuncio
     */
    public void setUpAdvView(FrameLayout frameLayout){
        this.frameLayout = frameLayout;
        this.adView = new AdView(this.activity);
        this.adView.setAdUnitId("ca-app-pub-3940256099942544/9214589741");
        this.adView.setAdSize(this.getAdvSize());
        this.frameLayout.addView(this.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        this.adView.loadAd(adRequest);
        sheduleAdvClose();
    }

    /**
     * Funcion que programa el cierre del anuncio pasado el tiempo definido en la constante TIME_ADV
     */
    private void sheduleAdvClose(){
        this.handler.postDelayed(this::closeAdv, AdBannerService.TIME_ADV);
    }

    /**
     * Funcion que elimina el anuncio del conentenedor padre
     */
    private void closeAdv(){
        if(this.adView!= null && this.frameLayout != null){
            this.frameLayout.removeView(this.adView);
            this.adView = null;
        }
    }

    /**
     * Funcion que calcula el tamaño del banner publicitario de manera adaptable
     * @return Objeto que define la medida del banner de publicidad
     */
    private AdSize getAdvSize(){
        DisplayMetrics displayMetrics = this.activity.getResources().getDisplayMetrics();
        int adWidthPixels = displayMetrics.widthPixels;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics windowMetrics = this.activity.getWindowManager().getCurrentWindowMetrics();
            adWidthPixels = windowMetrics.getBounds().width();
        }

        float density = displayMetrics.density;
        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this.activity, adWidth);
    }

    /**
     * Funcion para limpiar recursos cuando ya no se necesita más el banner publicitario
     */
    public void destroy(){
        this.handler.removeCallbacksAndMessages(null);
        if(this.adView != null){
            this.adView.destroy();
            this.adView = null;
        }
    }
}
