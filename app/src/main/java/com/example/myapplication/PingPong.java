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
import android.view.View;
import android.widget.Button;

import android.widget.RelativeLayout;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

public class PingPong extends AppCompatActivity implements View.OnClickListener, SensorEventListener
{
    DrawPong pongView;
    PongLogic logic;
    FrameLayout game;

    SensorManager sm;


    TextView scoreOne;
    TextView scoreTwo;
    int speed;

    private float[] rotationMatrix;     //Матрица поворота
    private float[] accelData;           //Данные с акселерометра
    private float[] magnetData;       //Данные геомагнитного датчика
    private float[] OrientationData; //Матрица положения в пространстве



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        game = new FrameLayout(this);
        scoreOne=new TextView( this);
        scoreTwo=new TextView( this);
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();

        int height = metrics.heightPixels;

        logic = new PongLogic(height);
        pongView = new DrawPong(this,logic,height);

        scoreOne.setText(String.valueOf(logic.scoreO));
        scoreTwo.setText(String.valueOf("0"));
        scoreOne.setId(R.id.score1);
        scoreTwo.setId(R.id.score2);
        scoreOne.setTextSize(80);
        scoreTwo.setTextSize(80);

        RelativeLayout scors= new RelativeLayout(this);

        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams scoretext1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams scoretext2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        scors.setLayoutParams(rl);
        scoretext1.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        scoretext1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        scoretext2.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        scoretext2.addRule(RelativeLayout.BELOW,R.id.score1);
        scoreOne.setLayoutParams(scoretext1);
        scoreTwo.setLayoutParams(scoretext2);

        scors.addView(scoreOne);
        scors.addView(scoreTwo);
        game.addView(scors);
        game.addView(pongView);
        setContentView(game);



        Handler handler = new Handler();
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);


        rotationMatrix = new float[16];
        accelData = new float[3];
        magnetData = new float[3];
        OrientationData = new float[3];





        Runnable loop = new Runnable()
        {
            public void run()
            {





                logic.pongTakt(speed);
                if(scoreOne.getText().toString()!=String.valueOf(logic.scoreO) ||scoreTwo.getText().toString()!=String.valueOf(logic.score1))
                {
                    scoreOne.setText(String.valueOf(logic.scoreO));
                    scoreTwo.setText(String.valueOf(logic.score1));
                }
                pongView.invalidate();
                handler.postDelayed(this, 30);




            }
        };

        loop.run();
    }
    private void loadNewSensorData(SensorEvent event)
    {
        final int type = event.sensor.getType(); //Определяем тип датчика
        if (type == Sensor.TYPE_ACCELEROMETER) { //Если акселерометр
            accelData = event.values.clone();
        }

        if (type == Sensor.TYPE_MAGNETIC_FIELD) { //Если геомагнитный датчик
            magnetData = event.values.clone();
        }

    }
    @Override
    public void onClick(View view) {

    }


    @Override
    public void onSensorChanged(SensorEvent event)
    {

        loadNewSensorData(event); // Получаем данные с датчика

        SensorManager.getRotationMatrix(rotationMatrix, null, accelData, magnetData); //Получаем матрицу поворота
        SensorManager.getOrientation(rotationMatrix, OrientationData); //Получаем данные ориентации устройства в пространстве

        //Выводим результат
        SensorManager.getRotationMatrixFromVector(
                rotationMatrix , event.values);

        speed=(int) Math.round(Math.toDegrees(OrientationData[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME );
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME );

    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }


}
