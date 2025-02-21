package com.spachecor.purrfectplatforming.gameobject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Clase Scenery que define el escenario, definiendo su fondo y gravedad
 * @author Selene
 * @version 1.0
 */
public class Scenery {
    private final Integer gravity;
    private Bitmap background;
    private final int ID_BACKGROUND;

    /**
     * Constructor del objeto Scenery
     * @param gravity La gravedad del escenario
     * @param idBackground El fondo del escenario
     */
    public Scenery(Integer gravity, int idBackground) {
        this.gravity = gravity;
        this.ID_BACKGROUND = idBackground;
    }

    public void setBackground(View view, int width, int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap rawBackground = BitmapFactory.decodeResource(view.getResources(), this.ID_BACKGROUND, options);
        this.background = Bitmap.createScaledBitmap(rawBackground, width, height, false);
        rawBackground.recycle();
    }

    public Integer getGravity() {
        return gravity;
    }

    /**
     * Funcion que define la forma en la que se dibuja el escenario
     * @param canvas El objeto que nos ayuda a crear la Bitmap
     */
    public void drawBackground(Canvas canvas){
        Paint paint = new Paint();
        paint.setFilterBitmap(false);
        if(this.background==null){
            System.err.println("background nulo terrible");
        }else canvas.drawBitmap(this.background, 0, 0, paint);
    }
}
