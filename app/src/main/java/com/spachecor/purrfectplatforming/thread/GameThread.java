package com.spachecor.purrfectplatforming.thread;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.spachecor.purrfectplatforming.view.GameView;

/**
 * Clase GameThread, que define el hilo principal del juego, a traves del cual se refresca
 * la pantalla dibujando lo que está ocurriendo y actualizando la lógica del juego
 * @author Selene
 * @version 1.0
 */
public class GameThread extends Thread{
    private final SurfaceHolder SURFACE_HOLDER;
    private final GameView GAME_VIEW;
    private boolean running;
    public static Canvas canvas;

    /**
     * Constructor que define como se construye un objeto del tipo GameThread
     * @param surfaceHolder Objeto que permite que el código de la aplicación interactue con la
     *                      pantalla
     * @param gameView La vista del juego
     */
    public GameThread(SurfaceHolder surfaceHolder, GameView gameView){
        this.SURFACE_HOLDER = surfaceHolder;
        this.GAME_VIEW = gameView;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while(this.running){
            canvas = null;
            try{
                //bloqueamos el bloque canvas para dibujar
                canvas = this.SURFACE_HOLDER.lockCanvas();
                synchronized (this.SURFACE_HOLDER){
                    //actualizamos la logica del juego
                    this.GAME_VIEW.update();
                    //dibujamos la vista actualizada del canvas
                    this.GAME_VIEW.draw(canvas);
                }
            } catch (Exception e) {
                //todo hay que gestionar que nos devuelva a la pantalla anterior
                //todo y que nos muestre un mensaje de error simple
                throw new RuntimeException(e);
            }finally {
                //liberamos el canvas(a null) y mostramos el contenido dibujado
                if(canvas!=null)this.SURFACE_HOLDER.unlockCanvasAndPost(canvas);
            }
        }
    }
}
