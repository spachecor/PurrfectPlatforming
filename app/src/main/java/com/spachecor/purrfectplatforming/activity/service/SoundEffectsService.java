package com.spachecor.purrfectplatforming.activity.service;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.TextView;

import com.spachecor.purrfectplatforming.R;
import com.spachecor.purrfectplatforming.activity.MainActivity;

/**
 * Clase SoundEffectsService que se encarga de definir la activacion y desactivacion del sonido del
 * juego.
 * @author Selene
 * @version 1.0
 */
public class SoundEffectsService {
    private static Boolean isMute;
    private MediaPlayer mediaPlayerMenu;
    private MediaPlayer mediaPlayerGame;
    private MediaPlayer mediaPlayerJump;
    static {
        SoundEffectsService.isMute = false;
    }
    public SoundEffectsService(Context context){
        this.mediaPlayerMenu = MediaPlayer.create(context, R.raw.menu);
        this.mediaPlayerMenu.setLooping(true);
        this.mediaPlayerGame = MediaPlayer.create(context, R.raw.game);
        this.mediaPlayerGame.setLooping(true);
        this.mediaPlayerJump = MediaPlayer.create(context, R.raw.jump);
    }

    /**
     * Funcion que activa o desactiva la musica del menu teniendo en cuenta si está activada y si
     * está el silencio activado en el juego
     */
    public void toggleMenuMusic(){
        if(SoundEffectsService.isMute)return;
        if(this.mediaPlayerMenu.isPlaying()){
            this.mediaPlayerMenu.pause();
        }else{
            if(this.mediaPlayerGame.isPlaying())this.mediaPlayerGame.pause();
            this.mediaPlayerMenu.seekTo(0);
            this.mediaPlayerMenu.start();
        }
    }

    /**
     * Funcion que activa o desactiva la musica del juego teniendo en cuenta si está activada y si
     * está el silencio activado en el juego
     */
    public void toggleGameMusic(){
        if(SoundEffectsService.isMute)return;
        if(this.mediaPlayerGame.isPlaying()){
            this.mediaPlayerGame.pause();
        }else{
            if(this.mediaPlayerMenu.isPlaying())this.mediaPlayerMenu.pause();
            this.mediaPlayerGame.seekTo(0);
            this.mediaPlayerGame.start();
        }
    }
    /**
     * Funcion que alterna el modo silencio y sonido en el juego
     * @param button El texto a modificar dependiendo del modo
     */
    public void toggleMute(Button button){
        SoundEffectsService.isMute = !SoundEffectsService.isMute;
        button.setText(SoundEffectsService.isMute?"Activar sonido":"Desactivar sonido");
        if(this.mediaPlayerMenu.isPlaying())this.mediaPlayerMenu.pause();
        else this.mediaPlayerMenu.start();
    }

    /**
     * Funcion que nos devuelve si está o no reproduciéndose la música del menú
     * @return true si se está reproduciendo y false si no
     */
    public Boolean isMenuMusicActivated(){
        return this.mediaPlayerMenu.isPlaying();
    }

    /**
     * Funcion que nos devuelve si está o no reproduciéndose la música del juego
     * @return true si se está reproduciendo y false si no
     */
    public Boolean isGameMusicActivated(){
        return this.mediaPlayerGame.isPlaying();
    }

    /**
     * Funcion que hace que suene el sonido del salto
     */
    public void theJumpSounds(){
        this.mediaPlayerJump.start();
    }
    public static Boolean getIsMute(){
        return SoundEffectsService.isMute;
    }


}
