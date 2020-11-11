package com.example.maxpo.joey_ortiz_assignment_4;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Anteater extends GameObject{
    private Bitmap anteater;
    private int time;
    public Anteater(Bitmap bitmap, int width, int height,int x)
    {
        this.x = x;
        y = 600;
        this.width = width;
        this.height = height;
        anteater = bitmap;
    }

    public void update()
    {
        x -= 10;
        time += 1;
        if (time < 15)
            y -= 10;
        if (time > 15 && time < 30)
            y += 10;
        if (time == 60)
            time = 0;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(anteater, x, y, null);
    }


}
