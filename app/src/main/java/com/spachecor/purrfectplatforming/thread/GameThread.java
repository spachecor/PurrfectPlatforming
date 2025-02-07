package com.spachecor.purrfectplatforming.thread;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.spachecor.purrfectplatforming.view.GameView;

public class GameThread extends Thread{
    private final SurfaceHolder SURFACE_HOLDER;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;
    public GameThread(SurfaceHolder surfaceHolder, GameView gameView){
        this.SURFACE_HOLDER = surfaceHolder;
        this.gameView = gameView;
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
                    this.gameView.update();
                    //dibujamos la vista actualizada del canvas
                    this.gameView.draw(canvas);
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

    public void setRunning(boolean running) {
        this.running = running;
    }
}
