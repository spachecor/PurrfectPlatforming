package com.spachecor.purrfectplatforming.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.spachecor.purrfectplatforming.thread.GameThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;

    public GameView(Context context) {
        super(context);
        //agregamos el callback para gestionar los cambios en el SurfaceHolder(creacion, cambios y destruccion de la superficie)
        this.getHolder().addCallback(this);
        //creamos el hilo del juego y lo asociamos a este SurfaceView
        this.gameThread = new GameThread(this.getHolder(), this);
        //activamos que esta vista pueda recibir eventos de toque para controlar el juego
        this.setFocusable(true);
    }

    public void update(){
        //todo comportamiento de actualizacion del bucle principal
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //todo comportamiento de dibujo
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //todo el comportamiento al tocar en la pantalla
        return false;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        //todo el comportamiento al crear la pantalla superficie
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        //todo el comportamiento al modificar la pantalla superficie
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        //todo el comportamiento al eliminar/destruir la pantalla superficie
    }

}
