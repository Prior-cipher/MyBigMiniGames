package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;

public class BreaakerAppActivity extends AppCompatActivity
{
    private GameBreaker game;
    private UpdateThread myThread;
    private Handler updateHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        game = new GameBreaker(this, 3, 0);
        setContentView(game);


        createHAndler();
        myThread = new UpdateThread(updateHandler);
        myThread.start();
    }

    private void createHAndler() {
        updateHandler = new Handler() {
            public void handleMessage(Message msg) {
                game.invalidate();
                game.update();
                super.handleMessage(msg);
            }
        };
    }

    protected void onPause() {
        super.onPause();
        game.unregirstrListener();

    }

    protected void onResume() {
        super.onResume();
        game.setUpdateSpeed();
    }

}


