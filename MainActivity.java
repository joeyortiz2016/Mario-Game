package com.example.maxpo.joey_ortiz_assignment_4;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE );


        setContentView(new GamePanel(this));
    }

    public static class Mushroom extends GameObject {
        private int time;
        private Bitmap drug;
        public Mushroom(Bitmap bitmap, int width, int height, int x)
        {
            this.x = x;
            y = 500;
            this.width = width;
            this.height = height;
            drug = bitmap;
        }

        public void update()
        {
            x -= 10;
        }

        public void draw(Canvas canvas){
            canvas.drawBitmap(drug, x, y, null);
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
}
