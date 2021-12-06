package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

class DrawView extends View
{
    Paint p;
    int yOffset =200;
    Game game;
    public DrawView(Context context,Game game)
    {
        super(context);
        p = new Paint();
        this.game=game;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        p.setColor(Color.GRAY);
        p.setStrokeWidth(5f);
        boundary(canvas);
        grid(canvas);
//        Coordinate[ ] test={new Coordinate(12,12),new Coordinate(12,13),new Coordinate(13,12),new Coordinate(13,13)};
        drawBriks(canvas);
        drawBrick(canvas);


    }

    private void grid(Canvas canvas) {
        p.setStrokeWidth(2f);
        for (int i = 90; i < 1040; i = i + 50) {
            canvas.drawLine(i, yOffset, i, yOffset + 1200, p);
        }
        for (int j = 50; j < 1200; j = j + 50) {
            canvas.drawLine(40, yOffset + j, 1040, yOffset + j, p);
        }
    }

    private void boundary(Canvas canvas) {
        p.setColor(Color.GRAY);
        p.setStrokeWidth(5f);
        canvas.drawLine(40, yOffset, 40, yOffset + 1200, p);
        canvas.drawLine(40, yOffset, 1040, yOffset, p);
        canvas.drawLine(1040, yOffset, 1040, yOffset + 1200, p);
        canvas.drawLine(1040, yOffset + 1200, 40, yOffset + 1200, p);
    }


    public void drawBrick( Canvas canvas)
    {

        p.setColor(Color.GRAY);
        for (Coordinate ccoord : game.currentBrick.coordinates)
        {
            canvas.drawRect(42 + ccoord.x * 50, yOffset + ccoord.y * 50 + 2, 88 + ccoord.x * 50, yOffset + (ccoord.y + 1) * 50 - 2, p);
        }



    }

    public  void ClearBrick()
    {

    }

    private  void drawBriks(Canvas canvas)
    {
        for (int i = 0; i < game.blocks.length; i++) {
            for (int j = 0; j <game.blocks[i].length ; j++)
            {
                if(game.blocks[i][j])
                {
                    p.setColor(Color.BLUE);
                    canvas.drawRect(42 + j * 50, yOffset + i * 50 + 2, 88 + j * 50, yOffset + (i + 1) * 50 - 2, p);
                }
            }
        }

        
    }
}
