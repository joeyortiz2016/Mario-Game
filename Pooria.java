package com.example.maxpo.joey_ortiz_assignment_4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.constraint.solver.widgets.Rectangle;

public class Pooria extends GameObject {
    private Bitmap pooria;
    private int time;
    public Pooria(Bitmap bitmap, int width, int height, int x)
    {
        pooria = bitmap;
        this.width = width;
        this.x = x;
        y = 700;
        this.height = height;
    }

    public void update()
    {
        if (time < 45) {
            x -= 20;
            time += 1;
        }
        if (time >= 45) {
            x += 10;
            time += 1;
            if (time == 80) {
                time = 0;
            }
        }
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(pooria, x, y, null);
    }


}
