package com.spachecor.purrfectplatforming.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.spachecor.purrfectplatforming.gameobject.character.Enemy;
import com.spachecor.purrfectplatforming.gameobject.character.Gamer;
import com.spachecor.purrfectplatforming.gameobject.platform.Platform;
import com.spachecor.purrfectplatforming.levelgenerator.Level;
import com.spachecor.purrfectplatforming.service.collision.CharacterCollisionManager;
import com.spachecor.purrfectplatforming.service.collision.CollisionManager;
import com.spachecor.purrfectplatforming.service.SpriteManager;
import com.spachecor.purrfectplatforming.service.collision.PlatformCollisionManager;
import com.spachecor.purrfectplatforming.thread.GameThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    private Level level;
    private Gamer gamer;
    //Control del movimiento
    Boolean isHolding;//variable bandera que indica si el usuario realiza un toque mantenido o un toque simple
    Float touchX;//posicion en el eje X del toque detectado
    private Runnable moveRunnable;//objeto Runnable que se ejecuta periodicamente para mover al personaje
    private Handler handler;//manejador para programar tareas con un retardo(como el toque prolongado para movimiento)
    private int threshold;//tiempo en ms para considerar un toque prolongado
    private int gravity;
    //Control de victoria o derrota
    private boolean victory;
    private boolean gameOver;

    public GameView(Context context, Level level) {
        super(context);
        //agregamos el callback para gestionar los cambios en el SurfaceHolder(creacion, cambios y destruccion de la superficie)
        this.getHolder().addCallback(this);
        //creamos el hilo del juego y lo asociamos a este SurfaceView
        this.gameThread = new GameThread(this.getHolder(), this);
        //activamos que esta vista pueda recibir eventos de toque para controlar el juego
        this.setFocusable(true);
        //tomamos el level
        this.level = level;
        //tomamos el personaje
        this.gamer = level.getGAMER();
        this.handler = new Handler();
        this.threshold = 200;
        this.gravity = level.getSCENERY().getGravity();
        this.victory = false;
        this.gameOver = false;
    }

    public void update(){
        //todo comportamiento de actualizacion del bucle principal
        this.gamer.applyGravity(this.gravity);
        //activamos la secuenciacion de fotogramas del personaje para imitar movimiento
        SpriteManager.controlSpriteMovement(this.gamer);
        //reajustamos la posición del rectangulo del personaje según su movimiento
        this.gamer.getRECT_CONTAINER().set(this.gamer.getPosicionX(), this.gamer.getPosicionY(), this.gamer.getPosicionX()+this.gamer.getWidth(), this.gamer.getPosicionY()+this.gamer.getHeight());
        for(Enemy enemy:this.level.getENEMIES()){//reajustamos gravedad, movimiento y rectangulo del enemigo
            enemy.applyGravity(this.gravity);
            enemy.updateMovement();
            enemy.getRECT_CONTAINER().set(enemy.getPosicionX(), enemy.getPosicionY(), enemy.getPosicionX()+enemy.getWidth(), enemy.getPosicionY()+enemy.getHeight());
        }
        //reajustamos la posición del rectangulo del trofeo
        this.level.getTROPHY().getRECT_CONTAINER().set(this.level.getTROPHY().getPosicionX(), this.level.getTROPHY().getPosicionY(), this.level.getTROPHY().getPosicionX()+this.level.getTROPHY().getWidth(), this.level.getTROPHY().getPosicionY()+this.level.getTROPHY().getHeight());
        //controlar que el personaje no se salga por el suelo ni por los bordes
        CollisionManager.lowerCollision(this.getHeight(), this.gamer);
        for(Enemy enemy:this.level.getENEMIES()){
            CollisionManager.lowerCollision(this.getHeight(), enemy);
        }
        CollisionManager.horizontalCollision(this.getWidth(), this.gamer);
        for(Enemy enemy:this.level.getENEMIES()){
            CollisionManager.horizontalCollision(this.getWidth(), enemy);
        }
        //comprobamos colisionamiento entre personajes y plataformas y entre jugadores y enemigos
        PlatformCollisionManager.managingCollisionsPlatformsCharacters(this.level.getPLATFORMS(), this.gamer);
        for(Enemy enemy:this.level.getENEMIES()){
            PlatformCollisionManager.managingCollisionsPlatformsCharacters(this.level.getPLATFORMS(), enemy);
            this.gameOver = CharacterCollisionManager.managingCollisionGamerEnemy(this.gamer, enemy);
            if(this.gameOver)break;
        }
        //comprobamos colision entre jugador y trofeo
        this.victory = CharacterCollisionManager.managingCollisionGamerTrophy(this.gamer, this.level.getTROPHY());
        if(this.victory) System.out.println("VICTORY");
        else if(this.gameOver) System.out.println("GAME OVER");
        else System.out.println("NOTHING");
    }

    @Override
    public void draw(Canvas canvas) {
        //todo comportamiento de dibujo
        if (canvas != null) {
            super.draw(canvas);
            this.level.getSCENERY().drawBackground(canvas);
            this.level.getTROPHY().draw(canvas);
            for(Platform platform: this.level.getPLATFORMS()){
                platform.draw(canvas);
            }
            this.gamer.draw(canvas);
            for(Enemy enemy:this.level.getENEMIES()){
                enemy.draw(canvas);
            }
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
                //nuestro runnable se ejecutará cuando detecte que hay movimiento tras lo que determine la variable THRESHOLD
                this.moveRunnable = () ->{
                    this.isHolding = true;//declaramos toque prolongado
                    if(this.touchX<(float)this.getWidth()/2){//movimiento izquierda
                        SpriteManager.setCurrentAnimation(this.gamer, this.gamer.getGORDI_CAMINANDO_IZQ());
                        this.gamer.leftMove();
                    }else{//movimiento derecha
                        SpriteManager.setCurrentAnimation(this.gamer, this.gamer.getGORDI_CAMINANDO_DCHA());
                        this.gamer.rightMove();
                    }
                    //programamos la ejecucion del runnable cada 10ms para continuar movimiento
                    this.handler.postDelayed(this.moveRunnable, 50);
                };
                //ahora programamos el moverunnable para que se ejecute cuando se cumpla el tiempo de movimiento prolongado.
                this.handler.postDelayed(this.moveRunnable, this.threshold);
                break;
            //usuario deja de tocar
            case MotionEvent.ACTION_UP:
                //restauramos el sprite de sentado
                SpriteManager.setCurrentAnimation(this.gamer, this.gamer.getGORDI_SENTADA());
                //cancelamos el runnable de movimiento
                this.handler.removeCallbacks(this.moveRunnable);
                //si NO fue un toque prolongado, ejecutamos el salto
                if (!this.isHolding) {
                    this.gamer.jump();
                }
                break;
            //el usuario desplaza el dedo por la pantalla mientras pulsa
            case MotionEvent.ACTION_MOVE:
                //actualizamos el touchX según el usuauro se mueve
                this.touchX = event.getX();
                break;
            //el usuario pone un segundo dedo en la pantalla
            case MotionEvent.ACTION_POINTER_DOWN:
                this.gamer.jump();
                break;
        }
        //indicamos que el evento ha sido consumido
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        //todo el comportamiento al crear la pantalla superficie
        //construimos el fondo
        this.level.getSCENERY().setBackground(this, this.getWidth(), this.getHeight());
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
