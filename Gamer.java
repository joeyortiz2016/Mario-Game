package com.example.maxpo.joey_ortiz_assignment_4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.animation.Animation;

import java.util.concurrent.TimeUnit;

public class Gamer extends GameObject{
    public Bitmap gamer;
    private int life;
    private String string1, string2, string3, string4,string5,string6;
    public int score;
    private int time;
    private Paint paint, paint2;
    private Rectangle bounds;
    private boolean reset;
    public int transform;
    public int level;
    //private Animation animation = new Animation();
    public Gamer(Bitmap map, int width, int height, int frames)
    {
        level = 1;
        gamer = map;
        transform = 0;
        x = 400;
        y = 630;
        dy = 0;
        dx = 0;
        score = 0;
        time = 0;
        life = 3;
        string1 = "Lives: ";
        string2 = "3";
        string3 = "Score: ";
        string4 = "0";
        string5 = "Level: ";
        string6 = "1";
        paint = new Paint();
        paint.setTextSize(50);
        paint.setARGB(255,255,0,0);
        paint2 = new Paint();
        paint2.setTextSize(200);
        paint2.setARGB(255,255,0,0);

        this.height = height;
        this.width = width;
    }

    public void update() throws InterruptedException {
        if(left)
        {
            x -= 25;
        }
        if(right)
        {
            x += 25;
            //dx = (int)(dya += 1.1);
        }
        if (up)
        {
            y-= 20;
            time += 1;
            if(time == 15){
                up = false;
                down = true;
                time = 0;
            }
        }
        if (down) {
            y += 20;
            time += 1;
            if (time == 15) {
                down = false;
                time = 0;
            }
        }
        if ((reset && y <630) || (reset && y > 630)){
            up = false;
            down = false;
            reset = false;
            time = 0;
            y = 630;
        }
        string4 = Integer.toString(score);
        string6 = Integer.toString(level);
    }

    public void draw(Canvas canvas)
    {
        if (level >=4){
            canvas.drawBitmap(gamer,x,y,null);
            canvas.drawText(string1+string2, 200, 100, paint);
            canvas.drawText(string3+string4, 500, 100, paint);
            canvas.drawText(string5+string6, 1500,100, paint);
            canvas.drawText("You Win!", 900,500, paint2);
            return;
        }
        canvas.drawBitmap(gamer,x,y,null);
        canvas.drawText(string1+string2, 200, 100, paint);
        canvas.drawText(string3+string4, 500, 100, paint);
        canvas.drawText(string5+string6, 1500,100, paint);

    }

    public int life(Pooria pooria)
    {
        int help = pooria.collision(x,y,width,height);
        if (help == 0)
        {
            if (transform == 1)
            {
                transform = 0;
                pooria.y = -2000;
                return 1;
            }
            life -= 1;
            string2 = Integer.toString(life);
            reset = true;
            return 0;
        }
        if (help == 2)
        {
            score += 200;
            System.out.println(score);
            string4 = Integer.toString(score);
            return 2;
        }
        return 1;
    }

    public int life2(Anteater anteater)
    {
        int help = anteater.collision(x,y,width,height);
        if (help == 0)
        {
            if (transform == 1)
            {
                transform = 0;
                anteater.y = -2000;
                return 1;
            }
            life -= 1;
            string2 = Integer.toString(life);
            reset = true;
            return 0;
        }
        if (help == 2)
        {
            score += 500;
            string4 = Integer.toString(score);
            return 2;
        }
        return 1;
    }

    public int life3(Wazer wazer)
    {
        int help = wazer.collision(x,y,width,height);
        if (help == 0)
        {
            if (transform == 1)
            {
                transform = 0;
                wazer.y = -2000;
                return 1;
            }
            life -= 1;
            string2 = Integer.toString(life);
            reset = true;
            return 0;
        }
        if (help == 2)
        {
            score += 1000;
            string4 = Integer.toString(score);
            return 2;
        }
        return 1;
    }

    public int coin(Coin coin)
    {
        int help = coin.collision(x,y,width,height);
        if (help == 1)
        {
            score += 200;
            string4 = Integer.toString(score);
            return 2;
        }
        return 1;
    }

    public int block(Block block)
    {
        int help = block.collision(x,y,width,height);
        if (help == 2)
        {
            score += 200;
            string4 = Integer.toString(score);
            return 2;
        }
        return 1;
    }

    public int power(Mushroom mushroom)
    {
        int help = mushroom.collision(x,y,width,height);
        {
            if (help == 2)
            {
                score+= 1000;
                string4 = Integer.toString(score);
                transform = 1;
                return 2;
            }
        }
        return 1;
    }

    public int finish(Finish finish)
    {
        int help = finish.collision(x,y,width,height);
        if (help == 2)
        {
            return 2;
        }
        return 1;
    }




}
