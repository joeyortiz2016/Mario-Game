package com.example.maxpo.joey_ortiz_assignment_4;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Block extends GameObject {
    private int time;
    private Bitmap stone;
    public Block(Bitmap bitmap, int width, int height, int x)
    {
        this.x = x;
        y = 500;
        this.width = width;
        this.height = height;
        stone = bitmap;
    }

    public void update()
    {
        x -= 10;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(stone, x, y, null);
    }

    public int collision(int x , int y, int w, int h)
    {
        if(x <= this.x+width && (x+w) >= this.x && (y+h) > this.y && y+h <= this.y+height){
            this.x = 0;
            this.y = -2000;
            return 2;
        }
        if (x <= this.x+width && (x+w) >= this.x && (y+h) <= this.y && y+h >= this.y-5){
            this.x = 0;
            this.y = -2000;
            return 2;

        }
        if(x <= this.x+width && (x+w) >= this.x && y > this.y && y <= this.y+height) {
            this.x = 0;
            this.y = -2000;
            return 2;
        }

        return 1;
    }

}
