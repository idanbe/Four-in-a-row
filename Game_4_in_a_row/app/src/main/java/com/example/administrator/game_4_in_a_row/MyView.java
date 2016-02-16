package com.example.administrator.game_4_in_a_row;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.os.Vibrator;



public class MyView extends View {

    // values
    private Paint paint;
    private static final int zero=0;
    private static final int ten=10;
    private Path path;
    private Random random;
    private float top, right, width, height;
    private static String [][] cell_arr;
    private static int [][] win_cell;
    private static boolean find_win;
    private static String Winner;
    private final String EMPTY ="E";
    private final String PLAYER1="R";
    private final String PLAYER2="Y";
    private float rx,ry,witdh_cell,height_cell;
    private Vibrator v;


    public MyView(Context context) {
        super(context);
        init(null, 0);
        Log.d("check4145","init 1");
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(ten);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            path = new Path();
            random = new Random();
            cell_arr = new String[6][7];
            clear_cell_Arr();
            win_cell= new int[4][2];
            v =(Vibrator)this.getContext().getSystemService(Context.VIBRATOR_SERVICE);

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
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        for (int i = 0; i < 6; i++)
        {
                for (int j = 0; j < 7; j++) {

                    if (!cell_arr[i][j].equals(EMPTY))//if cell not empty paint
                    {
                        if(cell_arr[i][j].equals(PLAYER1))
                        {
                            paint.setColor(Color.RED);
                        }
                        else // player 2
                        {
                            paint.setColor(Color.YELLOW);
                        }
                        rx=(witdh_cell*j); //x,y of cell to paint
                        ry=(height_cell*i);
                        canvas.drawRect(rx, ry, (rx + witdh_cell), (ry + height_cell), paint);
                        v.vibrate(50);
                    }
                }
        }
        if(find_win) //mark 4 cells of win
        {
            for (int i = 0; i < 4; i++) {
                    rx = (witdh_cell * win_cell[i][1]); //x,y of cell to paint
                    ry = (height_cell * win_cell[i][0]);


                if(Winner.equals(PLAYER1))
                {
                    paint.setColor(Color.rgb(159, 0, 15));

                }
                else
                {
                    paint.setColor(Color.rgb(253, 208, 23 ));

                }
                canvas.drawRect(rx, ry, (rx + witdh_cell), (ry + height_cell), paint);
                    /*/
                    paint.setColor(Color.BLACK);
                    canvas.drawLine(rx+(witdh_cell/2)+2, ry, rx+(witdh_cell/2)+2, ry + height_cell, paint);
                    canvas.drawLine(rx,ry+(height_cell/2)-5,rx+witdh_cell,ry+(witdh_cell/2)-5,paint);
                    canvas.drawLine(rx, ry, rx+witdh_cell, ry + height_cell, paint);
                    canvas.drawLine(rx+witdh_cell,ry,rx,ry+height_cell,paint);
                    /*/
            }

        }
    }




    public void set_winer(String win)
    {
        Winner=win;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // touch on view
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
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


    public void set_find_win(boolean b)
    {
        find_win=b;
    }

    private void clear_cell_Arr()
    {

        Log.d("check777", "clear_cell_Arr");
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
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                cell_arr[i][j]=arr[i][j];
            }
        }
    }

    public void setwin_Cell(int[][] arr)
    {

        for (int i=0;i<4;i++)
        {
            for(int j =0; j<2;j++)
            {
                win_cell[i][j]=arr[i][j];
            }
        }
        Log.d("check949", "in set win cell");

        return;
    }





}
