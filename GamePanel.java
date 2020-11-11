package com.example.maxpo.joey_ortiz_assignment_4;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    BitmapFactory.Options options = new BitmapFactory.Options();
    private MainThread thread;
    public Background background;
    public Gamer gamer;
    public Pooria pooria, pooria2, pooria3;
    public Anteater anteater, anteater2;
    public Wazer wazer, wazer2;
    public ArrayList <Coin> objects;
    public Block block, block2;
    public Mushroom mushroom;
    public Finish finish;
    public Bitmap hold;
    public Bitmap f = BitmapFactory.decodeResource(getResources(), R.drawable.flag);
    public Bitmap temp = BitmapFactory.decodeResource(getResources(), R.drawable.pooria);
    public Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.maxresdefault);
    public Bitmap joey = BitmapFactory.decodeResource(getResources(), R.drawable.image);
    public Bitmap enemy1 = BitmapFactory.decodeResource(getResources(), R.drawable.goomba);
    public Bitmap enemy2 = BitmapFactory.decodeResource(getResources(), R.drawable.anteater);
    public Bitmap enemy3 = BitmapFactory.decodeResource(getResources(), R.drawable.java_tech);
    public Bitmap coin_pic = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
    public Bitmap box = BitmapFactory.decodeResource(getResources(), R.drawable.stone);
    public Bitmap shroom = BitmapFactory.decodeResource(getResources(), R.drawable.cmon);
    public Rect rectangle;
    public int coinX, count;
    public GamePanel(Context context)
    {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        objects = new ArrayList<Coin>(1);

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    public void surfaceDestroyed(SurfaceHolder holder)
    {

        boolean retry = true;
        while(retry)
        {
            try {
                thread.setRunning(false);
                thread.join();

            }
            catch(Exception e) {e.printStackTrace();}
            retry = false;
        }

    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        f = Bitmap.createScaledBitmap(f, 1000,1000,false);
        temp = Bitmap.createScaledBitmap(temp, 100, 170, false);
        b = Bitmap.createScaledBitmap(b, 2560, 1440, false);
        joey = Bitmap.createScaledBitmap(joey,100,170,false);
        hold = joey;
        enemy1 = Bitmap.createScaledBitmap(enemy1, 100,100, false);
        enemy2 = Bitmap.createScaledBitmap(enemy2, 150 ,200, false);
        enemy3 = Bitmap.createScaledBitmap(enemy3, 150, 200, false);
        coin_pic = Bitmap.createScaledBitmap(coin_pic, 50, 50, false);
        box = Bitmap.createScaledBitmap(box, 100 ,100, false);
        shroom = Bitmap.createScaledBitmap(shroom, 100, 100, false);
        background = new Background(b);
        pooria = new Pooria(enemy1,100,100, 1800);
        pooria2 = new Pooria(enemy1,100,100,2100);
        pooria3 = new Pooria(enemy1,100,100, 2500);
        anteater = new Anteater(enemy2, 150, 200, 4000);
        anteater2 = new Anteater(enemy2, 150, 200, 5000);
        wazer = new Wazer(enemy3, 150, 200, 5700);
        wazer2 = new Wazer(enemy3, 150, 200, 6200);
        gamer = new Gamer(joey, 100,170,3);
        block = new Block(box, 100, 100, 3000);
        block2 = new Block(box, 100, 100, 3150);
        mushroom = new Mushroom(shroom, 100 ,100 ,1000);
        finish = new Finish(f, 1000, 1000, 6000);

        coinX = 2650;
        for (count = 0; count < 20; count++)
        {
            objects.add(0,new Coin(coin_pic, 50, 50, coinX));
            coinX += 55;
        }
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }


    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        System.out.println(x);
        System.out.println(y);
        if (event.getAction() == MotionEvent.ACTION_DOWN && x > 325 && x < 700 && y < 1080 && y > 825)
        {
            gamer.left = false;
            gamer.right = true;
            return true;

        }
        if (event.getAction() == MotionEvent.ACTION_DOWN && x < 375 && y < 1080 && y > 825)
        {
            gamer.right = false;
            gamer.left = true;
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            gamer.left = false;
            gamer.right = false;
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN && x > 1400 && x < 1680 && y < 1080 && y >825 )
        {
            gamer.up = true;
            return true;
        }


        return super.onTouchEvent(event);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void draw(Canvas canvas)
    {
        Coin coin;
        super.draw(canvas);
        if (gamer.finish(finish) == 2 && gamer.level == 3)
        {
            gamer.level += 4;
            gamer.draw(canvas);
        }
        background.draw(canvas);
        gamer.draw(canvas);
        pooria.draw(canvas);
        pooria2.draw(canvas);
        pooria3.draw(canvas);
        anteater.draw(canvas);
        anteater2.draw(canvas);
        wazer.draw(canvas);
        wazer2.draw(canvas);
        block.draw(canvas);
        block2.draw(canvas);
        mushroom.draw(canvas);
        finish.draw(canvas);
        for (count = 0; count < 20; count++)
        {
            coin = objects.get(count);
            coin.draw(canvas);
        }
    }

    public void update() throws InterruptedException {
        Coin coin;
        background.update();
        gamer.update();
        pooria.update();
        pooria2.update();
        pooria3.update();
        anteater.update();
        anteater2.update();
        wazer.update();
        wazer2.update();
        block.update();
        block2.update();
        mushroom.update();
        finish.update();
        for (count = 0; count < 20; count++)
        {
            coin = objects.get(count);
            coin.update();
            gamer.coin(coin);
        }
        gamer.block(block);
        gamer.block(block2);int help;
        help = gamer.power(mushroom);
        if (help == 2) {
            joey = temp;
            gamer.gamer = joey;
        }
        if (gamer.transform == 0) {
            joey = hold;
            gamer.gamer = joey;
        }

        //if statement resets level when mario dies
        if ((gamer.life(pooria) == 0 || gamer.life(pooria2) == 0 || gamer.life(pooria3) == 0 || gamer.life2(anteater) == 0 || gamer.life2(anteater2) == 0
                || gamer.life3(wazer) == 0 || gamer.life3(wazer2) == 0) && gamer.level == 1)
        {
            thread.setRunning(false);
            temp = Bitmap.createScaledBitmap(temp, 100, 170, false);
            b = Bitmap.createScaledBitmap(b, 2560, 1440, false);
            joey = Bitmap.createScaledBitmap(joey,100,170,false);
            enemy1 = Bitmap.createScaledBitmap(enemy1, 100,100, false);
            background = new Background(b);
            //enemy start
            pooria = new Pooria(enemy1,100,100,1800);
            pooria2 = new Pooria(enemy1,100,100,2100);
            pooria3 = new Pooria(enemy1,100,100, 2500);
            anteater = new Anteater(enemy2, 150, 200, 4000);
            anteater2 = new Anteater(enemy2, 150, 200, 5000);
            wazer = new Wazer(enemy3, 150, 200, 5700);
            wazer2 = new Wazer(enemy3, 150, 200, 6200);
            //enemy end
            gamer.x = 400;
            gamer.y = 630;
            //items start
            coinX = 2650;
            block = new Block(box, 100, 100, 3000);
            block2 = new Block(box, 100, 100, 3150);
            mushroom = new Mushroom(shroom, 100 ,100 ,1000);
            finish = new Finish(f, 1000, 1000, 6000);

            for (count = 0; count < 20; count++)
            {
                objects.add(0,new Coin(coin_pic, 50, 50, coinX));
                coinX += 50;
            }
            //items end
            thread = new MainThread(getHolder(), this);
            thread.setRunning(true);
            thread.start();
        }
        if (gamer.finish(finish) == 2 && gamer.level == 1)
        {
            thread.setRunning(false);
            temp = Bitmap.createScaledBitmap(temp, 100, 170, false);
            b = Bitmap.createScaledBitmap(b, 2560, 1440, false);joey = Bitmap.createScaledBitmap(joey,100,170,false);
            enemy1 = Bitmap.createScaledBitmap(enemy1, 100,100, false);
            background = new Background(b);
            //enemy start
            pooria = new Pooria(enemy1,100,100,4000);
            pooria2 = new Pooria(enemy1,100,100,4500);
            pooria3 = new Pooria(enemy1,100,100, 1200);
            anteater = new Anteater(enemy2, 150, 200, 3000);
            anteater2 = new Anteater(enemy2, 150, 200, 3600);
            wazer = new Wazer(enemy3, 150, 200, 3300);
            wazer2 = new Wazer(enemy3, 150, 200, 4000);
            //enemy end
            gamer.x = 400;
            gamer.y = 630;
            //items start
            coinX = 800;
            block = new Block(box, 100, 100, 3000);
            block2 = new Block(box, 100, 100, 3150);
            mushroom = new Mushroom(shroom, 100 ,100 ,2700);
            finish = new Finish(f, 1000, 1000, 5000);
            for (count = 0; count < 20; count++)
            {
                objects.add(0,new Coin(coin_pic, 50, 50, coinX));
                coinX += 50;
            }
            //items end
            gamer.level += 1;
            thread = new MainThread(getHolder(), this);
            thread.setRunning(true);
            thread.start();

        }
        if ((gamer.life(pooria) == 0 || gamer.life(pooria2) == 0 || gamer.life(pooria3) == 0 || gamer.life2(anteater) == 0 || gamer.life2(anteater2) == 0
                || gamer.life3(wazer) == 0 || gamer.life3(wazer2) == 0) && gamer.level == 2){
            thread.setRunning(false);
            temp = Bitmap.createScaledBitmap(temp, 100, 170, false);
            b = Bitmap.createScaledBitmap(b, 2560, 1440, false);
            joey = Bitmap.createScaledBitmap(joey,100,170,false);
            enemy1 = Bitmap.createScaledBitmap(enemy1, 100,100, false);
            background = new Background(b);
            //enemy start
            pooria = new Pooria(enemy1,100,100,4000);
            pooria2 = new Pooria(enemy1,100,100,4500);
            pooria3 = new Pooria(enemy1,100,100, 1200);
            anteater = new Anteater(enemy2, 150, 200, 3000);
            anteater2 = new Anteater(enemy2, 150, 200, 3600);
            wazer = new Wazer(enemy3, 150, 200, 3300);
            wazer2 = new Wazer(enemy3, 150, 200, 4000);
            //enemy end
            gamer.x = 400;
            gamer.y = 630;
            //items start
            coinX = 800;
            block = new Block(box, 100, 100, 3000);
            block2 = new Block(box, 100, 100, 3150);
            mushroom = new Mushroom(shroom, 100 ,100 ,2700);
            finish = new Finish(f, 1000, 1000, 5000);
            for (count = 0; count < 20; count++)
            {
                objects.add(0,new Coin(coin_pic, 50, 50, coinX));
                coinX += 50;
            }
            //items end
            thread = new MainThread(getHolder(), this);
            thread.setRunning(true);
            thread.start();

        }
        if (gamer.finish(finish) == 2 && gamer.level == 2){
            thread.setRunning(false);
            temp = Bitmap.createScaledBitmap(temp, 100, 170, false);
            b = Bitmap.createScaledBitmap(b, 2560, 1440, false);
            joey = Bitmap.createScaledBitmap(joey,100,170,false);
            enemy1 = Bitmap.createScaledBitmap(enemy1, 100,100, false);
            background = new Background(b);
            //enemy start
            pooria = new Pooria(enemy1,100,100,3000);
            pooria2 = new Pooria(enemy1,100,100,3200);
            pooria3 = new Pooria(enemy1,100,100, 3400);
            anteater = new Anteater(enemy2, 150, 200, 1000);
            anteater2 = new Anteater(enemy2, 150, 200, 5000);
            wazer = new Wazer(enemy3, 150, 200, 2000);
            wazer2 = new Wazer(enemy3, 150, 200, 2700);
            //enemy end
            gamer.x = 400;
            gamer.y = 630;
            //items start
            coinX = 5500;
            block = new Block(box, 100, 100, 1200);
            block2 = new Block(box, 100, 100, 1800);
            mushroom = new Mushroom(shroom, 100 ,100 ,2700);
            finish = new Finish(f, 1000, 1000, 6000);
            for (count = 0; count < 20; count++)
            {
                objects.add(0,new Coin(coin_pic, 50, 50, coinX));
                coinX += 50;
            }
            //items end
            gamer.level += 1;
            thread = new MainThread(getHolder(), this);
            thread.setRunning(true);
            thread.start();

        }

        if ((gamer.life(pooria) == 0 || gamer.life(pooria2) == 0 || gamer.life(pooria3) == 0 || gamer.life2(anteater) == 0 || gamer.life2(anteater2) == 0
                || gamer.life3(wazer) == 0 || gamer.life3(wazer2) == 0) && gamer.level == 3){
            thread.setRunning(false);
            temp = Bitmap.createScaledBitmap(temp, 100, 170, false);
            b = Bitmap.createScaledBitmap(b, 2560, 1440, false);
            joey = Bitmap.createScaledBitmap(joey,100,170,false);
            enemy1 = Bitmap.createScaledBitmap(enemy1, 100,100, false);
            background = new Background(b);
            //enemy start
            pooria = new Pooria(enemy1,100,100,3000);
            pooria2 = new Pooria(enemy1,100,100,3200);
            pooria3 = new Pooria(enemy1,100,100, 3400);
            anteater = new Anteater(enemy2, 150, 200, 1000);
            anteater2 = new Anteater(enemy2, 150, 200, 5000);
            wazer = new Wazer(enemy3, 150, 200, 2000);
            wazer2 = new Wazer(enemy3, 150, 200, 2700);
            //enemy end
            gamer.x = 400;
            gamer.y = 630;
            //items start
            coinX = 5500;
            block = new Block(box, 100, 100, 1200);
            block2 = new Block(box, 100, 100, 1800);
            mushroom = new Mushroom(shroom, 100 ,100 ,2700);
            finish = new Finish(f, 1000, 1000, 6000);
            for (count = 0; count < 20; count++)
            {
                objects.add(0,new Coin(coin_pic, 50, 50, coinX));
                coinX += 50;
            }
            //items end
            thread = new MainThread(getHolder(), this);
            thread.setRunning(true);
            thread.start();

        }
        if (gamer.finish(finish) == 2 && gamer.level == 4)
            gamer.level += 4;

    }

}
