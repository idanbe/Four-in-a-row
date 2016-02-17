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
    private int check;
    private final int NotFind =-1;


    public AiMove()
    {
        cell_arr =new String[Six][Seven];
        clear_cell_Arr();
    }

    public int Ai_move() //computer move
    {

        if(find_Ai_threeSequence()!=NotFind)
        {
            return find_Ai_threeSequence();
        }
        if(find_rival_threeSequence()!=NotFind)
        {
            return find_rival_threeSequence();
        }
        if(find_Ai_twoSequence() !=NotFind)
        {
            return find_Ai_twoSequence();
        }

        if(find_rival_twoSequence() !=NotFind)
        {
            return find_rival_twoSequence();
        }
        if(find_Ai_OneCell()!=NotFind)
        {
            return find_Ai_OneCell();
        }
        return getRandomCol();

    }

    private int find_Ai_OneCell()
    {
        return NotFind;
    }



    private int find_Ai_threeSequence()
    {





        return NotFind;
    }

    private int find_Ai_twoSequence()
    {

        return NotFind;
    }




    private int find_rival_threeSequence()
    {
        return NotFind;
    }
    private int find_rival_twoSequence()
    {
        return NotFind;
    }



    private int find_Sequence(String player,int Sequence)
    {
        for(i=Zero;i<Six;i++) {
            for (j = Zero; j < Seven; j++) {
                if ((check=check_up(i, j, player))!=NotFind) {
                    return check ;
                }
                if ((check=check_Down(i, j, player))!=NotFind)
                {
                    return check;
                }
                if ((check=check_Right(i, j, player))!=NotFind) {
                    return check;
                }
                if ((check=check_Left(i, j, player))!=NotFind) {
                    return check;
                }
                if ((check=check_Diagonal_up_Right(i, j, player))!=NotFind) {
                    return check;
                }
                if ((check=check_Diagonal_up_Left(i, j, player))!=NotFind) {
                    return check;
                }
                if ((check=check_Diagonal_Down_Right(i, j, player))!=NotFind) {
                    return check;
                }
                if ((check=check_Diagonal_Down_Left(i, j, player))!=NotFind) {
                    return check;
                }
            }
        }
        return NotFind;
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



    private int check_up(int r,int c,String player)   // r=row , c=col
    {

        if(r>TWO)
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r-ONE][c].equals(player))
                    &&(cell_arr[r-TWO][c].equals(player))&&(cell_arr[r-Three][c].equals(player)))
            {
                //return true; //find 4 cell up of player
            }
        }
        return NotFind;
    }


    private int check_Down(int r,int c,String player)   // r=row , c=col
    {
        if(r<Three)
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r+ONE][c].equals(player))
                    &&(cell_arr[r+TWO][c].equals(player))&&(cell_arr[r+Three][c].equals(player)))
            {
                //return true; //find 4 cell Down of player
            }
        }
        return NotFind;
    }


    private int check_Right(int r,int c,String player)   // r=row , c=col
    {
        if(c<Four)
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r][c+ONE].equals(player))
                    &&(cell_arr[r][c+TWO].equals(player))&&(cell_arr[r][c+Three].equals(player)))
            {
                //return true; //find 4 cell Right of player
            }
        }
        return NotFind;
    }


    private int check_Left(int r,int c,String player)   // r=row , c=col
    {
        if(c>TWO)
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r][c-ONE].equals(player))
                    &&(cell_arr[r][c-TWO].equals(player))&&(cell_arr[r][c-Three].equals(player)))
            {
                //return true; //find 4 cell Left of player
            }
        }
        return NotFind;
    }

    private int check_Diagonal_up_Right(int r,int c,String player)   // r=row , c=col
    {
        if((r>TWO)&&(c<Four))
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r-ONE][c+ONE].equals(player))
                    &&(cell_arr[r-TWO][c+TWO].equals(player))&&(cell_arr[r-Three][c+Three].equals(player)))
            {
               // return true; //find 4 cell Diagonal up Right of player
            }
        }
        return NotFind;
    }

    private int check_Diagonal_up_Left(int r,int c,String player)   // r=row , c=col
    {
        if((r>TWO)&&(c>TWO))
        {   if((cell_arr[r][c].equals(player))&&(cell_arr[r-ONE][c-ONE].equals(player))
                &&(cell_arr[r-TWO][c-TWO].equals(player))&&(cell_arr[r-Three][c-Three].equals(player)))
            {
            //return true; //find 4 cell Diagonal up Left of player
            }
        }
        return NotFind;
    }


    private int check_Diagonal_Down_Right(int r,int c,String player)   // r=row , c=col
    {
        if((r<Three)&&(c<Four))
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r+ONE][c+ONE].equals(player))
                    &&(cell_arr[r+TWO][c+TWO].equals(player))&&(cell_arr[r+Three][c+Three].equals(player)))
            {
                //return true; //find 4 cell Diagonal Down Right of player
            }
        }
        return NotFind;
    }

    private int check_Diagonal_Down_Left(int r,int c,String player)   // r=row , c=col
    {
        if((r<Three)&&(c>TWO))
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r+ONE][c-ONE].equals(player))
                    &&(cell_arr[r+TWO][c-TWO].equals(player))&&(cell_arr[r+Three][c-Three].equals(player)))
            {

                //return true; //find 4 cell Diagonal Down Left of player
            }
        }
        return NotFind;
    }





}
