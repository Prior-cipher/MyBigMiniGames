package com.example.myapplication;

import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Snake  extends AppCompatActivity
{

    SnakeView drawView;

    FrameLayout game;

    Runnable loop;
    SnakeLogic gameLogic;
    TextView score;
    int width;
    Handler mHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        game = new FrameLayout(this);
        gameLogic = new SnakeLogic();
        gameLogic.Game();

        drawView = new SnakeView(this, gameLogic);
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();

        width = metrics.widthPixels;


        score = new TextView(this);
        score.setText(R.string.score);
        score.setId(R.id.score);
        score.setTextSize(30);


        RelativeLayout.LayoutParams scoretext = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


        scoretext.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        scoretext.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);


        score.setLayoutParams(scoretext);





        game.addView(drawView);
        game.addView(score);
        setContentView(game);
        score.setText("1");


//        Handler handler = new Handler();
//
//        loop = new Runnable() {
//            public void run()
//            {
//
//                if(gameLogic.status)
//                {
//                    gameLogic.Game();
////                gameLogic ;
//
//                    score.setText(String.valueOf(gameLogic.zmey.size()));
//                    drawView.invalidate();
//
//                    handler.postDelayed(this, 500);
//                }
//
//
//
//
//            }
//        };
//        loop.run();
       mHandler = new Handler();   // Handler to update UI
        // This is being created in the main thread
        // so everything posted will be posted to
        // the main thread

        mUpdate.start();
    }
    public Thread mUpdate = new Thread()
    {
        public void run()
        {
            while (!this.interrupted())
            {


                // Define the UI change you want to make
                Runnable uiUpdate = new Runnable()
                {
                    public void run() {
                        // modify your UI here.

                        if(gameLogic.status)
                        {
                            gameLogic.Game();
//                gameLogic ;

                            score.setText(String.valueOf(gameLogic.zmey.size()));
                            drawView.invalidate();


                        }
                        else
                            {
                                score.setText("you loose");
                            }
                    }
                };
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // post the UI change back to the main thread.
                mHandler.post(uiUpdate);
            }
        }

        public void interrupt()
        {
            super.interrupt();


        }
    };

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    public void onClick(View action) {
//
//        if (action == left) {
//
//            gameLogic.checkMoveLeft();
//            drawView.invalidate();
//        }
//        if (action == right) {
//            gameLogic.checkMoveRight();
//            drawView.invalidate();
//        }
//        if (action == rotateAc) {
//            gameLogic.checkRotation();
//            drawView.invalidate();
//        }
//    }

    @Override
    public void onBackPressed() {

        Log.w("","iclosetetris");
//        SharedPreferences preferences = getSharedPreferences("tetrisCol", MODE_PRIVATE);
//        SharedPreferences.Editor edit= preferences.edit();
//
//
//        edit.putInt("isFirstRun", e);
//        edit.commit();
        finish();
        return;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if (x < width / 2) {

                gameLogic.turnLeft();
                drawView.invalidate();
            }
            else {

                gameLogic.turnRight();
                drawView.invalidate();
            }


        }
        return false;
    }


}


