package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

public class GameBreaker extends View implements SensorEventListener, View.OnTouchListener
{

    private Bitmap back;
    private Bitmap redBall;
    private Bitmap longphone;
    private Bitmap paddle_p;

    private Display display;
    private Point size;
    private Paint paint;

    private BallBreaker wall;
    private ArrayList<BrickBreaker> listofBriks;
    private Paddle paddle;


    private RectF r;

    private SensorManager sm;


    private int lifes;
    private int score;
    private int level;
    private boolean start;
    private boolean gameOver;
    private Context context;
    int speed;
    private float[] rotationMatrix;     //Матрица поворота
    private float[] accelData;           //Данные с акселерометра
    private float[] magnetData;       //Данные геомагнитного датчика
    private float[] OrientationData; //Матрица положения в пространстве

    public GameBreaker(Context context, int lifes, int score)
    {
        super(context);
        paint = new Paint();


        this.context = context;
        this.lifes = lifes;
        this.score = score;
        level = 0;


        start = false;
        gameOver = false;



        sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        rotationMatrix = new float[16];
        accelData = new float[3];
        magnetData = new float[3];
        OrientationData = new float[3];
        checkBack(context);


        redBall = BitmapFactory.decodeResource(getResources(), R.drawable.redball);
        paddle_p = BitmapFactory.decodeResource(getResources(), R.drawable.paddle);


        wall = new BallBreaker(size.x / 2, size.y - 480);
        paddle = new Paddle(size.x / 2, size.y - 400);
        listofBriks = new ArrayList<BrickBreaker>();

        createBrkicks(context);
        this.setOnTouchListener(this);

    }


    private void createBrkicks(Context context)
    {
        for (int i = 3; i < 7; i++) {
            for (int j = 1; j < 6; j++) {
                listofBriks.add(new BrickBreaker(context, j * 150, i * 100));
            }
        }
    }


    private void checkBack(Context context) {
        back = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.phone));
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        size = new Point();
        display.getSize(size);
    }

    protected void onDraw(Canvas canvas) {

        if (longphone == null)
        {
            longphone = Bitmap.createScaledBitmap(back, size.x, size.y, false);
        }
        canvas.drawBitmap(longphone, 0, 0, paint);


        paint.setColor(Color.RED);
        canvas.drawBitmap(redBall, wall.getX(), wall.getY(), paint);


        paint.setColor(Color.WHITE);
        r = new RectF(paddle.getX(), paddle.getY(), paddle.getX() + 200, paddle.getY() + 40);
        canvas.drawBitmap(paddle_p, null, r, paint);


        paint.setColor(Color.GREEN);
        for (int i = 0; i < listofBriks.size(); i++) {
            BrickBreaker b = listofBriks.get(i);
            r = new RectF(b.getX(), b.getY(), b.getX() + 100, b.getY() + 80);
            canvas.drawBitmap(b.getBrick(), null, r, paint);
        }


        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText("" + lifes, 400, 100, paint);
        canvas.drawText("" + score, 700, 100, paint);


        if (gameOver) {
            paint.setColor(Color.RED);
            paint.setTextSize(100);
            canvas.drawText("Game over!", size.x / 4, size.y / 2, paint);
        }
    }


    private void checkingEror() {
        if (wall.getX() + wall.getxRychlost() >= size.x - 60) {
            wall.changeDirection("prava");
        } else if (wall.getX() + wall.getxRychlost() <= 0) {
            wall.changeDirection("lava");
        } else if (wall.getY() + wall.getyRychlost() <= 150) {
            wall.changeDirection("hore");
        } else if (wall.getY() + wall.getyRychlost() >= size.y - 200) {
            checkLives();
        }
    }


    private void checkLives() {
        if (lifes == 1) {
            gameOver = true;
            start = false;
            invalidate();
        } else {
            lifes--;
            wall.setX(size.x / 2);
            wall.setY(size.y - 480);
            wall.speedCalculation();
            wall.increaseSpeed(level);
            start = false;
        }
    }


    public void update() {
        if (start) {
            win();
            checkingEror();
            wall.stopChangeDir(paddle.getX(), paddle.getY());
            for (int i = 0; i < listofBriks.size(); i++) {
                BrickBreaker b = listofBriks.get(i);
                if (wall.brickReflection(b.getX(), b.getY())) {
                    listofBriks.remove(i);
                    score = score + 80;
                }
            }
            wall.move();
        }
    }

    public void unregirstrListener() {

        sm.unregisterListener(this);
    }

    public void setUpdateSpeed()
    {

        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME );
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME );
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
    public void onSensorChanged(SensorEvent event)
    {
        loadNewSensorData(event); // Получаем данные с датчика

        sm.getRotationMatrix(rotationMatrix, null, accelData, magnetData); //Получаем матрицу поворота
        sm.getOrientation(rotationMatrix, OrientationData); //Получаем данные ориентации устройства в пространстве

        //Выводим результат
        sm.getRotationMatrixFromVector(
                rotationMatrix , event.values);
        speed=(int) Math.round(Math.toDegrees(OrientationData[2]));
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            paddle.setX(paddle.getX() + speed);

            if (paddle.getX() + speed > size.x - 240)
            {
                paddle.setX(size.x - 240);
            } else if (paddle.getX() + speed <= 20) {
                paddle.setX(20);
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }



    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if (gameOver == true && start == false)
        {
            score = 0;
            lifes = 3;
            resetLevel();
            gameOver = false;

        } else {
            start = true;
        }
        return false;
    }


    private void resetLevel() {
        wall.setX(size.x / 2);
        wall.setY(size.y - 480);
        wall.speedCalculation();
        listofBriks = new ArrayList<BrickBreaker>();
        createBrkicks(context);
    }


    private void win() {
        if (listofBriks.isEmpty()) {
            ++level;
            resetLevel();
            wall.increaseSpeed(level);
            start = false;
        }
    }
}