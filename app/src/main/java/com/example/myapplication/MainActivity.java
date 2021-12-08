package com.example.myapplication;

import androidx.activity.result.ActivityResultCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainActivity extends AppCompatActivity
{

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button play= findViewById(R.id.button);
        ImageView play2= findViewById(R.id.tetrisimg);

        play2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this, Choose_game.class);
                startActivity(intent);
            }
        });

        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this, Choose_game.class);
                startActivity(intent);
            }
        });





    Button pong;
    pong= findViewById(R.id.pongButton);
    pong.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            Intent intent = new Intent(MainActivity.this, PingPong.class);
            startActivity(intent);
        }
    });


        Button snake;
        snake= findViewById(R.id.snake);
        snake.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this, Snake.class);
                startActivity(intent);
            }
        });

        ImageView snakemg;

        snakemg= findViewById(R.id.snakeimg);


        snakemg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this, Snake.class);
                startActivity(intent);

            }
        });
    }
}