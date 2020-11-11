package com.example.maxpo.joey_ortiz_assignment_4;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Wazer extends GameObject{
    private int time;
    private Bitmap wazer;
    public Wazer(Bitmap bitmap, int width, int height, int x) {
        this.x = x;
        y = 700;
        this.width = width;
        this.height = height;
        wazer = bitmap;
    }

    public void update()
    {
        x -= 10;
        time += 1;
        if (time < 60)
            y -= 10;
        if (time > 60 && time < 120)
            y += 10;
        if (time == 120)
            time = 0;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(wazer,x,y, null);
    }

    public int collision(int x, int y, int w, int h)
    {
        int keep = super.collision(x, y, w, h);

        if(x <= this.x+width && (x+w) >= this.x && y+h > this.y && y <= this.y+height){
            return 0;
        }



        return keep;

    }
}
