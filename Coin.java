package com.example.maxpo.joey_ortiz_assignment_4;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Coin extends GameObject {
    private int time;
    private Bitmap coin;
    public Coin(Bitmap bitmap, int width, int height, int x) {
        this.x = x;
        y = 750;
        this.width = width;
        this.height = height;
        coin = bitmap;

    }

    public void update(){
        x -= 10;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(coin, x, y, null);
    }

    @Override
    public int collision(int x, int y, int w, int h)
    {
        if(x <= this.x+width && (x+w) >= this.x && (y+h) > this.y && y+h <= this.y+height)
        {
            this.x = 0;
            this.y = -500;
            return 1;

        }
        return 0;
    }



}
