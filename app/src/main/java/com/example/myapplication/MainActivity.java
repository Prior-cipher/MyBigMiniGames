package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button play= findViewById(R.id.button);
        ImageView play2= findViewById(R.id.tetrisimg);
        WebSocket ws ;
        MyThread myThread= new MyThread();
        myThread.run();
        ws=myThread.ws;


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

        Button missiImg;
        missiImg= findViewById(R.id.missing);
        missiImg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this, BreaakerAppActivity.class);
                startActivity(intent);
            }
        });

    }
}