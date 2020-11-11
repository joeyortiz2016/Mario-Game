package com.example.maxpo.joey_ortiz_assignment_4;

import android.graphics.Rect;

public abstract class GameObject {
    public int x, y, dx, dy, width, height;
    boolean left, right, up, down;

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Rect getRectangle()
    {
        return new Rect(x,y,x+width,y+height);
    }
    public int collision(int x, int y, int w, int h)
    {
        if(x <= this.x+width && (x+w) >= this.x && (y+h) > this.y && y+h <= this.y+height)  {
            return 0;
            //0 means mario loses one life and game resets
        }
        if (x <= this.x+width && (x+w) >= this.x && (y+h) <= this.y && y+h >= this.y-5){
            this.x = 0;
            this.y = -2000;
            return 2;
            //2 tells GamePanel to kill enemy
        }
        else
            return 1;

        //1 signals that nothing happens
    }

}
