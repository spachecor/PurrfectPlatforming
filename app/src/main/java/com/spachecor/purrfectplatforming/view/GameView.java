package com.spachecor.purrfectplatforming.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.spachecor.purrfectplatforming.gameobject.personaje.Gamer;
import com.spachecor.purrfectplatforming.service.SpriteManager;
import com.spachecor.purrfectplatforming.thread.GameThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    private Gamer gamer;
    Boolean isHolding;//variable bandera que indica si el usuario realiza un toque mantenido o un toque simple
    Float touchX;//posicion en el eje X del toque detectado

    public GameView(Context context, Gamer gamer) {
        super(context);
        //agregamos el callback para gestionar los cambios en el SurfaceHolder(creacion, cambios y destruccion de la superficie)
        this.getHolder().addCallback(this);
        //creamos el hilo del juego y lo asociamos a este SurfaceView
        this.gameThread = new GameThread(this.getHolder(), this);
        //activamos que esta vista pueda recibir eventos de toque para controlar el juego
        this.setFocusable(true);
        //creamos el personaje
        this.gamer = gamer;
    }

    public void update(){
        //todo comportamiento de actualizacion del bucle principal
        SpriteManager.controlSpriteMovement(this.gamer);
    }

    @Override
    public void draw(Canvas canvas) {
        //todo comportamiento de dibujo
        if (canvas != null) {
            super.draw(canvas);
            canvas.drawColor(0xFFFFFFFF); //pintamos de blanco pa ver el personaje
            this.gamer.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //todo el comportamiento al tocar en la pantalla
        //obtenemos la acción de evento de toque
        int action = event.getActionMasked();
        switch (action){
            //usuario inicia un toque
            case MotionEvent.ACTION_DOWN:
                //declaramos que el usuario ha dado un toque simple y recogemos su valor en el eje X
                this.isHolding = false;
                this.touchX = event.getX();
        }
        return false;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        //todo el comportamiento al crear la pantalla superficie
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        //todo el comportamiento al modificar la pantalla superficie
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        //todo el comportamiento al eliminar/destruir la pantalla superficie
        boolean retry = true;
        this.gameThread.setRunning(false);
        while (retry) {
            try {
                this.gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
