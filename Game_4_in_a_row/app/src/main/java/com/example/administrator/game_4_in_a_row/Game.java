package com.example.administrator.game_4_in_a_row;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class Game extends AppCompatActivity {

    private Intent intent;
    private TextView player_turn;
    private boolean[] col_press;
    private String [][] cell_arr ;
    private final String EMPTY ="E";
    private final String PLAYER1="R";
    private final String PLAYER2="G";
    private final String PLAYER1_turn="Player1 turn";
    private final String PLAYER2_turn="Player2 turn";
    private String turn;
    private MyView myView ;
    private boolean Game_on;
    private View view ;
    private float witdh_cell,height_cell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Log.d("check414","onCreate Game");
        Game_on=true;
        cell_arr = new String[6][7];
        col_press = new boolean[7];
        clear_cell_Arr();
        view = findViewById(R.id.view);
        turn=PLAYER1_turn;
        myView= new MyView(this);


        player_turn = (TextView)findViewById(R.id.text_player_turn);


            if (turn.equals(PLAYER1_turn)) {
                player_turn.setText(PLAYER1_turn);
            } else {
                player_turn.setText(PLAYER2_turn);
            }


        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(Game_on) {
                    witdh_cell = v.getWidth() / 7;
                    height_cell = v.getHeight() / 6;

                    if (event.getX() < witdh_cell) {
                        Log.d("check767", "col 0");
                        insert_coin(turn, 0);
                    }
                    if ((event.getX() > (witdh_cell)) && (event.getX() < (witdh_cell * 2))) {
                        Log.d("check767", "col 1");
                        insert_coin(turn, 1);
                    }
                    if ((event.getX() > (witdh_cell * 2)) && (event.getX() < (witdh_cell * 3))) {
                        Log.d("check767", "col 2");
                        insert_coin(turn, 2);

                        //cell_arr[5][2]=PLAYER1;
                    }
                    if ((event.getX() > (witdh_cell * 3)) && (event.getX() < (witdh_cell * 4))) {
                        Log.d("check767", "col 3");
                        insert_coin(turn, 3);
                    }
                    if ((event.getX() > (witdh_cell * 4)) && (event.getX() < (witdh_cell * 5))) {
                        Log.d("check767", "col 4");
                        insert_coin(turn, 4);

                    }
                    if ((event.getX() > (witdh_cell * 5)) && (event.getX() < (witdh_cell * 6))) {
                        Log.d("check767", "col 5");
                        insert_coin(turn, 5);
                    }
                    if ((event.getX() > (witdh_cell * 6)) && (event.getX() < (witdh_cell * 7))) {
                        Log.d("check767", "col 6");
                        insert_coin(turn, 6);
                    }
                    v.invalidate();
                }
                return false;
            }
        });



    }


    private boolean check_board_full()
    {
        for(int i=0;i<6;i++)
        {
            for (int j=0;j<7;j++)
            {
               if(cell_arr[i][j].equals(EMPTY))
               {
                   return false;//there  is empty cell
               }
            }
        }

        return true;
    }


    //for test
    private void print_cell_arr()
    {
        for(int i=0;i<6;i++)
        {
            for (int j=0;j<7;j++)
            {
                Log.d("check1",i+""+""+j+":"+cell_arr[i][j].toString());
            }
        }
    }


    private void insert_coin(String player,int col)
    {
        Log.d("check1","in insert coin");

        if(check_board_full())
        {
          //  player_turn.setText("Game End it tie");
          //  Game_on=false;
            return;
        }

        int i=5;
            while ((i>=0)&&(!cell_arr[i][col].equals(EMPTY)))//check if empty cell in col
            {
                i=i-1;
            }
        if(i==-1)//col is full
        {
            return;
        }

        if(player.equals(PLAYER1_turn))
        {
            cell_arr[i][col]=PLAYER1;
        }
        else
        {
            cell_arr[i][col]=PLAYER2;
        }



        if(check_win(player))//win
        {
            Log.d("check21", "win");
            Game_on=false;
        }
        change_turn();
        //todo check case col is full no place for new coin
    }


    public String[][] getCell_arr()
    {
        return cell_arr;
    }


    private void change_turn()
    {
        if(Game_on)
        {
            if(turn.equals(PLAYER1_turn))
            {
                turn=PLAYER2_turn;
                player_turn.setText(PLAYER2_turn);
            }
            else
            {
                turn=PLAYER1_turn;
                player_turn.setText(PLAYER1_turn);
            }
        }
        else //game end
        {
            if(turn.equals(PLAYER1_turn))
            {
                player_turn.setText("Player1 Win");
            }
            else
            {
                player_turn.setText("Player2 Win");
            }
        }
        return;
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            intent = new Intent(Game.this, Settings.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void clear_cell_Arr()
    {
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 7; j++)
            {
            cell_arr[i][j]=EMPTY;
            }
        }
    }

    private boolean check_win(String player) // player1/player2
    {
        if(player.equals(PLAYER1_turn))
        {
            player=PLAYER1;
        }
        else
        {
            player=PLAYER2;
        }

            for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                if(check_up(i,j,player))
                {
                    return true;// win found 4 cell Up
                }
                if(check_Down(i, j, player))
                {
                    return true;// win found 4 cell Down
                }
                if(check_Right(i, j, player))
                {
                    return true;// win found 4 cell Right
                }
                if(check_Left(i, j, player))
                {
                    return true;//win found 4 cell Left
                }
                if(check_Diagonal_up_Right(i, j, player))
                {
                  return true; // win found 4 cell Diagonal Up Right
                }
                if(check_Diagonal_up_Left(i, j, player))
                {
                    return true; // win found 4 cell Diagonal Up Left
                }
                if(check_Diagonal_Down_Right(i, j, player))
                {
                    return true;// win found 4 cell Diagonal Down Right
                }
                if(check_Diagonal_Down_Left(i,j,player))
                {
                    return true;// win found 4 cell Diagonal Down Left
                }
            }
        }
        return false; // did not found 4 cell same color
    }




    private boolean check_up(int r,int c,String player)   // r=row , c=col
    {

        if(r>2)
        {
            Log.d("check999","in if Up ");
            if((cell_arr[r][c].equals(player))&&(cell_arr[r-1][c].equals(player))
            &&(cell_arr[r-2][c].equals(player))&&(cell_arr[r-3][c].equals(player)))
            {
                return true; //find 4 cell up of player
            }
        }
        return false;
    }


    private boolean check_Down(int r,int c,String player)   // r=row , c=col
    {
        if(r<3)
        {
            Log.d("check999","in if Down ");
            if((cell_arr[r][c].equals(player))&&(cell_arr[r+1][c].equals(player))
                    &&(cell_arr[r+2][c].equals(player))&&(cell_arr[r+3][c].equals(player)))
            {
                return true; //find 4 cell Down of player
            }
        }
        return false;
    }


    private boolean check_Right(int r,int c,String player)   // r=row , c=col
    {
        if(c<4)
        {
            Log.d("check999","in if  Right ");
            if((cell_arr[r][c].equals(player))&&(cell_arr[r][c+1].equals(player))
                    &&(cell_arr[r][c+2].equals(player))&&(cell_arr[r][c+3].equals(player)))
            {
                return true; //find 4 cell Right of player
            }
        }
        return false;
    }
        //todo aviram gay!!!

    private boolean check_Left(int r,int c,String player)   // r=row , c=col
    {
        if(c>2)
        {
            Log.d("check999","in if Left ");
            if((cell_arr[r][c].equals(player))&&(cell_arr[r][c-1].equals(player))
                    &&(cell_arr[r][c-2].equals(player))&&(cell_arr[r][c-3].equals(player)))
            {
                return true; //find 4 cell Left of player
            }
        }
        return false;
    }
            //// TODO: 10/02/2016 check this func have a bug
    private boolean check_Diagonal_up_Right(int r,int c,String player)   // r=row , c=col
    {
        if((r>2)&&(c<4))
        {
            Log.d("check999","in if diagonal up Right ");
            if((cell_arr[r][c].equals(player))&&(cell_arr[r-1][c+1].equals(player))
                    &&(cell_arr[r-2][c+2].equals(player))&&(cell_arr[r-3][c+3].equals(player)))
            {

                return true; //find 4 cell Diagonal up Right of player
            }
        }
        return false;
    }

    private boolean check_Diagonal_up_Left(int r,int c,String player)   // r=row , c=col
    {
        if((r>2)&&(c>2))
        {
            Log.d("check999","in if diagonal up left ");
            if((cell_arr[r][c].equals(player))&&(cell_arr[r-1][c-1].equals(player))
                    &&(cell_arr[r-2][c-2].equals(player))&&(cell_arr[r-3][c-3].equals(player)))
            {
                return true; //find 4 cell Diagonal up Left of player
            }
        }
        return false;
    }


    private boolean check_Diagonal_Down_Right(int r,int c,String player)   // r=row , c=col
    {
        if((r<3)&&(c<4))
        {
            Log.d("check999","in if diagonal down Right ");
            if((cell_arr[r][c].equals(player))&&(cell_arr[r+1][c+1].equals(player))
                    &&(cell_arr[r+2][c+2].equals(player))&&(cell_arr[r+3][c+3].equals(player)))
            {
                return true; //find 4 cell Diagonal Down Right of player
            }
        }
        return false;
    }

    private boolean check_Diagonal_Down_Left(int r,int c,String player)   // r=row , c=col
    {
        //Log.d("check999","in  diagonal down left ");
        if((r<3)&&(c>2))
        {
            Log.d("check999","in if diagonal down left ");
            if((cell_arr[r][c].equals(player))&&(cell_arr[r+1][c-1].equals(player))
                    &&(cell_arr[r+2][c-2].equals(player))&&(cell_arr[r+3][c-3].equals(player)))
            {

                return true; //find 4 cell Diagonal Down Left of player
            }
        }
        return false;
    }

}
