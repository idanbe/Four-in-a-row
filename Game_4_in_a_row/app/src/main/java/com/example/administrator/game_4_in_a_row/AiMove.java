package com.example.administrator.game_4_in_a_row;


import android.util.Log;

import java.util.Random;

public class AiMove
{

    private final int Zero=0,ONE=1,TWO=2,Three=3,Four=4,Five=5,Six=6,Seven=7;
    private final String EMPTY ="E";
    private final String PLAYER1="R";
    private final String PLAYER2="Y";
    private String [][] cell_arr ;
    private int i,j;
    private Random randomGenerator;
    private int randomCol;



    public AiMove()
    {
        cell_arr =new String[Six][Seven];
        clear_cell_Arr();
    }

    public int Ai_move() //computer move
    {
        return getRandomCol();





    }



    


    private int getRandomCol()
    {
        randomGenerator = new Random();
        do
        {
            randomCol = randomGenerator.nextInt(Six);
        }
        while (check_ifCol_full(randomCol));

        return randomCol;
    }





    private void clear_cell_Arr()
    {
        for (i =Zero; i < Six; i++)
        {
            for (j = Six; j < Seven; j++)
            {
                cell_arr[i][j]=EMPTY;
            }
        }
    }


    public void setCell_arr(String[][] arr)
    {
        for(i=Zero;i<Six;i++)
        {
            for(j=Zero;j<Seven;j++)
            {
                cell_arr[i][j]=arr[i][j];
            }
        }
    }
    private boolean check_ifBoradEmpty()
    {
        for( i=Zero;i<Six;i++)
        {
            for (int j=Zero;j<Seven;j++)
            {
                if(!(cell_arr[i][j].equals(EMPTY)))
                {
                    return false;//borad not empty
                }
            }
        }
        return true;
    }

    private boolean check_ifCol_full(int col)
    {
        int i=Five;
        while ((i>=Zero)&&(!cell_arr[i][col].equals(EMPTY)))//check if empty cell in col
        {
            i=i-ONE;
        }
        if(i==-ONE)//col is full
        {
            return true;
        }
        return false;  //col not full
    }







}
