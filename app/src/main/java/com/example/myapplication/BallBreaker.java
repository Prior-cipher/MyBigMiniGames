package com.example.myapplication;

public class BallBreaker {

    protected float xSpeed;
    protected float ySpeed;
    private float x;
    private float y;

    public BallBreaker(float x, float y) {
        this.x = x;
        this.y = y;
        speedCalculation();
    }


    protected void speedCalculation()
    {
        int maxX = 13;
        int minX = 7;
        int maxY = -17;
        int minY = -23;
        int rangeX = maxX - minX + 1;
        int rangeY = maxY - minY + 1;

        xSpeed = (int) (Math.random() * rangeX) + minX;
        ySpeed = (int) (Math.random() * rangeY) + minY;
    }


    protected void changeDirection() {
        if (xSpeed > 0 && ySpeed  < 0) {
            reflectX();
        } else if (xSpeed < 0 && ySpeed < 0) {
            reflectY();
        } else if (xSpeed < 0 && ySpeed > 0) {
            reflectX();
        } else if (xSpeed > 0 && ySpeed > 0) {
            reflectY();
        }
    }


    protected void increaseSpeed(int level) {
        xSpeed = xSpeed + (1 * level);
        ySpeed = ySpeed - (1 * level);
    }


    protected void changeDirection(String stena) {
        if (xSpeed > 0 && ySpeed < 0 && stena.equals("prava")) {
            reflectX();
        } else if (xSpeed > 0 && ySpeed < 0 && stena.equals("hore")) {
            reflectY();
        } else if (xSpeed < 0 && ySpeed < 0 && stena.equals("hore")) {
            reflectY();
        } else if (xSpeed < 0 && ySpeed < 0 && stena.equals("lava")) {
            reflectX();
        } else if (xSpeed < 0 && ySpeed > 0 && stena.equals("lava")) {
            reflectX();
        } else if (xSpeed > 0 && ySpeed > 0 && stena.equals("dole")) {
            reflectY();
        } else if (xSpeed > 0 && ySpeed > 0 && stena.equals("prava")) {
            reflectX();
        }
    }


    private boolean stopping(float ax, float ay, float bx, float by) {
        bx += 12;
        by += 11;
        if ((Math.sqrt(Math.pow((ax + 50) - bx, 2) + Math.pow(ay - by, 2))) < 80) {
            return true;
        } else if ((Math.sqrt(Math.pow((ax + 100) - bx, 2) + Math.pow(ay - by, 2))) < 60) {
            return true;
        } else if ((Math.sqrt(Math.pow((ax + 150) - bx, 2) + Math.pow(ay - by, 2))) < 60) {
            return true;
        }
        return false;
    }


    private boolean brickCLose(float ax, float ay, float bx, float by) {
        bx += 12;
        by += 11;
        double d = Math.sqrt(Math.pow((ax + 50) - bx, 2) + Math.pow((ay + 40) - by, 2));
        return d < 80;
    }


    protected void stopChangeDir(float xPaddle, float yPaddle) {
        if (stopping(xPaddle, yPaddle, getX(), getY())) changeDirection();
    }


    protected boolean brickReflection(float xBrick, float yBrick) {
        if (brickCLose(xBrick, yBrick, getX(), getY())) {
            changeDirection();
            return true;
        } else return false;
    }


    protected void move() {
        x = x + xSpeed;
        y = y + ySpeed;
    }

    public void reflectX() {
        xSpeed = -xSpeed;
    }

    public void reflectY() {
        ySpeed = -ySpeed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getxRychlost() {
        return xSpeed;
    }

    public float getyRychlost() {
        return ySpeed;
    }
}
