package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

public class DrawPong extends View
{
    Paint p;

    PongLogic pl;
int height;
    DrawPong(Context context, PongLogic pL,int height)
    {
        super(context);
        this.p = new Paint();

        this.pl = pL;
        this.height=height;

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        p.setColor(Color.BLUE);
        p.setStrokeWidth(5f);

        writeOne(canvas);
        writeTwo(canvas);
        writeBall(canvas);



    }

    private void writeOne(Canvas canvas) {
        p.setColor(Color.BLUE);
        p.setStrokeWidth(5f);

        canvas.drawRect( pl.enemyPosition, 1 + 1 * 50 + 2,  + 7 * 50 + pl.enemyPosition, 1 + (1 + 1) * 50 - 2, p);
    }

    private void writeTwo(Canvas canvas)
    {
        p.setColor(Color.RED);
        p.setStrokeWidth(5f);
        canvas.drawRect( pl.curentPosition, height + 1 * 50 + 2,  + 7 * 50 + pl.curentPosition, height + (1 + 1) * 50 - 2, p);
    }

    private void writeBall(Canvas canvas)
    {
        p.setColor(Color.GREEN);
        p.setStrokeWidth(5f);
        canvas.drawRect( pl.ballX, pl.ballY + 1 * 50 + 2,  1 * 50 + pl.ballX, pl.ballY + (1 + 1) * 50 - 2, p);
    }

}
