package com.example.administrator.game_4_in_a_row;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;




public class MyView extends View {

    // values
    private Paint paint;
    private static final int zero=0;
    private static final int ten=10;
    private Path path;
    private Random random;
    private float top, right, width, height;
    private String [][] cell_arr ;
    private final String EMPTY ="E";
    private final String PLAYER1="R";
    private final String PLAYER2="G";
    private Game game;
    private int flag ;
    private float rx,ry,witdh_cell,height_cell;


    public MyView(Context context) {
        super(context);
        flag=1;
        init(null, 0);
        Log.d("check4145","init 1");
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        flag=2;
        init(attrs, 0);
        Log.d("check4145", "init 2");
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
        Log.d("check4145", "init 3");
    }

    private void init(AttributeSet attrs, int defStyle) {

        // Load attributes
        // set circle configuration
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(ten);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        path = new Path();
        random = new Random();
        cell_arr = new String[6][7];
        clear_cell_Arr();
        Log.d("check414", "init");



    }




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        right = getPaddingRight();
        top = getPaddingTop();
        width = w - (getPaddingLeft() + getPaddingRight());
        height = h - (getPaddingTop() + getPaddingBottom());
        witdh_cell=(width/7);
        height_cell=(height/6);
        rx=right; //x of first cell
        ry=top; //y of first cell
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("check212", "OnDraw");

            //for test
              cell_arr[2][4]=PLAYER1;
              cell_arr[1][6]=PLAYER2;

        for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {

                    if (!cell_arr[i][j].equals(EMPTY))//if cell not empty paint
                    {
                        if(cell_arr[i][j].equals(PLAYER1))
                        {
                            paint.setColor(Color.RED);
                        }
                        else // player 2
                        {
                            paint.setColor(Color.GREEN);
                        }
                        rx=(witdh_cell*j); //x,y of cell to paint
                        ry=(height_cell*i);
                        canvas.drawRect(rx,ry,(rx+witdh_cell),(ry+height_cell),paint);
                    }
                    else
                    {
                        Log.d("check414", "empty cell"+i+""+j);
                    }
                }
            }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // touch on view
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(event.getX()<witdh_cell)
                {
                    Log.d("check777","col 0");
                    //cell_arr[5][0]="R";
                }
                if((event.getX()>(witdh_cell))&&(event.getX()<(witdh_cell*2)))
                {
                    Log.d("check777","col 1");

                }
                if((event.getX()>(witdh_cell*2))&&(event.getX()<(witdh_cell*3)))
                {
                    Log.d("check777","col 2");
                }
                if((event.getX()>(witdh_cell*3))&&(event.getX()<(witdh_cell*4)))
                {
                    Log.d("check777","col 3");
                }
                if((event.getX()>(witdh_cell*4))&&(event.getX()<(witdh_cell*5)))
                {
                    Log.d("check777","col 4");
                }
                if((event.getX()>(witdh_cell*5))&&(event.getX()<(witdh_cell*6)))
                {
                    Log.d("check777","col 5");
                }
                if((event.getX()>(witdh_cell*6))&&(event.getX()<(witdh_cell*7)))
                {
                    Log.d("check777","col 6");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;

        }
        return false;
    }



    private void clear_cell_Arr()
    {

        Log.d("check414", "clear_cell_Arr");
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                cell_arr[i][j]=EMPTY;
            }
        }
    }


    public void setCell_arr(String[][] arr)
    {

        Log.d("check414", "in set cell_arr");
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                this.cell_arr[i][j]=arr[i][j];
                if(!cell_arr[i][j].equals(EMPTY))
                {
                    Log.d("check414", "[" + i + "]" + "[" + j + "]:" + cell_arr[i][j].toString());
                }
            }
        }

    }





}
