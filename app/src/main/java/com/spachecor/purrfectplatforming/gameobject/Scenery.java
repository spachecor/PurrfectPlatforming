package com.spachecor.purrfectplatforming.gameobject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Scenery {
    private Integer gravity;
    private Bitmap background;
    private int idBrackground;

    public Scenery(Integer gravity, int idBrackground) {
        this.gravity = gravity;
        this.idBrackground = idBrackground;
    }

    public Bitmap getBackground() {
        return background;
    }

    public void setBackground(View view, int width, int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap rawBackground = BitmapFactory.decodeResource(view.getResources(), this.idBrackground, options);
        this.background = Bitmap.createScaledBitmap(rawBackground, width, height, false);
        rawBackground.recycle();
    }

    public Integer getGravity() {
        return gravity;
    }

    public void setGravity(Integer gravity) {
        this.gravity = gravity;
    }

    public int getIdBrackground() {
        return idBrackground;
    }

    public void setIdBrackground(int idBrackground) {
        this.idBrackground = idBrackground;
    }

    public void drawBackground(Canvas canvas){
        Paint paint = new Paint();
        paint.setFilterBitmap(false);
        if(this.background==null){
            System.err.println("background nulo terrible");
        }else canvas.drawBitmap(this.background, 0, 0, paint);
    }
}
