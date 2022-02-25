package com.example.myapplication;


public class PongLogic {


    int curentSpeed = 0;
    int curentPosition = 350;
    int enemyPosition=350;
    int ballX=500;
    int ballY=500;
    int enemySpeed=10;

    int ballXxpeed=0;
    int ballYSpeed=30;

    int scoreO=0;
    int score1=0;
    int height;
    PongLogic(int height)
    {

this.height=height;
ballY=height/2;
    }

    public void pongTakt(int speed)
    {

        curentSpeed += speed * 0.5;
        curentSpeed *= 0.95;
        curentPosition += curentSpeed;
        ballX+=ballXxpeed;
        ballY+=ballYSpeed;
        if (curentPosition <= 0)
        {
            curentPosition = 0;
            curentSpeed = 0;
        } else if (curentPosition >= 730)
        {
            curentPosition = 730;
            curentSpeed = 0;
        }
        if(ballX>=curentPosition &&ballX<=curentPosition+350 &&ballY>=height-50)
        {
            ballY=height-49;
            ballYSpeed=-ballYSpeed;
            ballXxpeed+=curentSpeed;
        }
        else if (ballY>height
        )
        {
            ballX=500;
            ballY=height/2;
            ballXxpeed=0;
            ballYSpeed=-30;
            scoreO+=1;
        }
        if(ballX>=enemyPosition &&ballX<=enemyPosition+350 &&ballY<=50)
        {
            ballY=51;
            ballYSpeed=-ballYSpeed;
        }
        else if(ballY<0)
        {

            ballX=500;
            ballY=height/2;
            ballXxpeed=0;
            ballYSpeed=30;
            score1+=1;
        }
        if(ballX<=0)
        {
            ballX=0;
            ballXxpeed=-ballXxpeed;
        }
        else if(ballX>=1030)
        {
            ballX=1030;
            ballXxpeed=-ballXxpeed;

        }
        if(enemyPosition>=730)
        {
            enemySpeed=-enemySpeed;
        }
        else if(enemyPosition<=0)
        {
            enemySpeed=-enemySpeed;
        }
        enemyPosition += enemySpeed;

    }


}
