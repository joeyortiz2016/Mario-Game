package com.example.maxpo.joey_ortiz_assignment_4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.solver.widgets.Rectangle;

public class Background {

    private Bitmap map;
    private Paint brown,green,blue;
    private int x, y;
    public Background(Bitmap bitmap)
    {
        map = bitmap; }

    public void update()
    {
        x -= 10;
        if (x < -2560)
        {
            x = 0;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void draw(Canvas canvas)
    {
        brown = new Paint();
        blue = new Paint();
        green = new Paint();
        brown.setARGB(255,165,42,42);
        blue.setARGB(255,0,0,150);
        green.setARGB(255,0,150,0);
        canvas.drawBitmap(map,x,y,null);
        canvas.drawRect(0,800,5000000,1080,brown);
        canvas.drawOval(20,850,300,1040,blue);
        canvas.drawOval(400, 850,680,1040,blue);
        canvas.drawOval(1400,850,1680,1040,green);
        if (x < 0) {
            canvas.drawBitmap(map, x + 2560, y, null);
            canvas.drawRect(0,800,5000000,1080,brown);
            canvas.drawOval(20,850,300,1040,blue);
            canvas.drawOval(400, 850,680,1040,blue);
            canvas.drawOval(1400,850,1680,1040,green);
        }
    }
}
