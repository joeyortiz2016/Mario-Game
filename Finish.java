package com.example.maxpo.joey_ortiz_assignment_4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Finish extends GameObject {
    private int time;
    private Bitmap finish;
    public Finish(Bitmap bitmap, int width, int height, int x)
    {
        this.x = x;
        y = -100;
        this.width = width;
        this.height = height;
        finish = bitmap;
    }

    public void update()
    {
        x -= 8;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(finish, x, y, null);
    }

    public int collision(int x , int y, int w, int h)
    {
        if(x > this.x)
            return 2;

        return 1;
    }

}