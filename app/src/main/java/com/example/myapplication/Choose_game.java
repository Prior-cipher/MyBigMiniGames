package com.example.myapplication;


import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Choose_game extends AppCompatActivity {


    DrawView drawView;

    FrameLayout game;
    Prediction pred;
    Runnable loop;
    Game gameLogic;
    TextView score;
    int width;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        game = new FrameLayout(this);
        gameLogic = new Game();

        drawView = new DrawView(this, gameLogic);
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


        pred = new Prediction(this, gameLogic);

        game.addView(pred);
        game.addView(drawView);
        game.addView(score);
        setContentView(game);
        score.setText("1");


        Handler handler = new Handler();



        loop = new Runnable() {
            public void run() {


                gameLogic.takt();
                pred.invalidate();
                score.setText(String.valueOf(gameLogic.score));
                drawView.invalidate();


                handler.postDelayed(this, 500);


            }
        };
        loop.run();
    }


    @Override
    public void onBackPressed() {


        finish();
        return;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (x < width / 3) {
                gameLogic.checkMoveLeft();
                drawView.invalidate();
            } else if (x < width / 3 * 2) {
                gameLogic.checkRotation();
                drawView.invalidate();
            } else {

                gameLogic.checkMoveRight();
                drawView.invalidate();
            }


        }
        return false;
    }
}


