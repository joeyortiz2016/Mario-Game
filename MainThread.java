package com.example.maxpo.joey_ortiz_assignment_4;

import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.SurfaceHolder;

public class MainThread extends Thread{
    private int fps = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean run;
    public Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean run)
    {
        this.run = run;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void run()
    {
        long startTime;
        long timeMillis = 1000/fps;
        long waitTime;
        int frameCount = 0;
        int totalTime = 0;
        long targetTime = 1000/fps;
        while(run)
        {
            startTime = System.nanoTime();
            canvas = null;
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }
            catch(Exception e){
               e.printStackTrace();
            }
            finally{
                if(canvas!=null){
                    try{
                       surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){
                        e.printStackTrace();

                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;
            try{
                if (waitTime >0)
                    this.sleep(waitTime);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == fps)
            {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }

}
