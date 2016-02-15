package com.example.administrator.game_4_in_a_row;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.os.Vibrator;
import android.view.ViewGroup.LayoutParams;



public class Game extends AppCompatActivity {

    private Intent intent;
    private TextView player_turn;
    private String [][] cell_arr ;
    private int win_cell[][];
    private final String EMPTY ="E";
    private final String PLAYER1="R";
    private final String PLAYER2="Y";
    private  String PLAYER1_turn="Player1 turn";
    private  String PLAYER2_turn="Player2 turn";
    private String player1_name="Player1";
    private String player2_name="Player2";
    private String turn;
    private MyView myView ;
    private boolean Game_on;
    private View view ;
    private float witdh_cell;
    private Vibrator/*btoh tahat shel idan*/ v;
    private Bundle bundle;
    static final String p1_key ="key1";
    static final String p2_key ="key2";
    static final String gameType_key ="key3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        bundle = getIntent().getExtras();

        // get names of players
        if (bundle != null) {
            if(!bundle.getString(p1_key).toString().equals("")) //check not empty
            {
                player1_name=bundle.getString(p1_key).toString();
                PLAYER1_turn=player1_name + " turn";
            }
            if(!bundle.getString(p2_key).toString().equals(""))
            {
                player2_name=bundle.getString(p2_key).toString();
                PLAYER2_turn=player2_name + " turn";
            }
           // Log.d("gameType",bundle.getString(gameType_key).toString());


        }

        Log.d("check414", "onCreate Game");
        Game_on = true;
        cell_arr = new String[6][7];
        clear_cell_Arr();
        win_cell = new int[4][2];
        view = findViewById(R.id.view);
        turn = PLAYER1_turn;
        myView = new MyView(this);
        myView.set_find_win(false);
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        player_turn = (TextView) findViewById(R.id.text_player_turn);


        if (turn.equals(PLAYER1_turn)) {
            player_turn.setText(PLAYER1_turn);
        } else {
            player_turn.setText(PLAYER2_turn);
        }


        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(Game_on)
                {
                    witdh_cell = v.getWidth() / 7;

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
                    myView.setCell_arr(cell_arr);
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



    private void insert_coin(String player,int col)
    {
        Log.d("check1","in insert coin");
        if(check_board_full())
        {
          //  player_turn.setText("Game End it tie");
            Game_on=false;
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

                v.vibrate(400);
                player_turn.setText(player1_name + " Win");

            }
            else
            {
                v.vibrate(400);
                player_turn.setText(player2_name + " Win");
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
                    Log.d("CheckWin:","win up");
                    return true;// win found 4 cell Up
                }
                if(check_Down(i, j, player))
                {
                    Log.d("CheckWin:","win Down");
                    return true;// win found 4 cell Down
                }
                if(check_Right(i, j, player))
                {
                    Log.d("CheckWin:","win Right");
                    return true;// win found 4 cell Right
                }
                if(check_Left(i, j, player))
                {
                    Log.d("CheckWin:","win LEFT");
                    return true;//win found 4 cell Left
                }
                if(check_Diagonal_up_Right(i, j, player))
                {
                    Log.d("CheckWin:","win Diagnoal up right");
                  return true; // win found 4 cell Diagonal Up Right
                }
                if(check_Diagonal_up_Left(i, j, player))
                {
                    Log.d("CheckWin:","win Diagnoal up Left");
                    return true; // win found 4 cell Diagonal Up Left
                }
                if(check_Diagonal_Down_Right(i, j, player))
                {
                    Log.d("CheckWin:","win Diagnoal Down right");
                    return true;// win found 4 cell Diagonal Down Right
                }
                if(check_Diagonal_Down_Left(i,j,player))
                {
                    Log.d("CheckWin:","win Diagnoal Down left");
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
                win_cell[0][0]=r;
                win_cell[0][1]=c;
                win_cell[1][0]=r-1;
                win_cell[1][1]=c;
                win_cell[2][0]=r-2;
                win_cell[2][1]=c;
                win_cell[3][0]=r-3;
                win_cell[3][1]=c;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
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
                win_cell[0][0]=r;
                win_cell[0][1]=c;
                win_cell[1][0]=r+1;
                win_cell[1][1]=c;
                win_cell[2][0]=r+2;
                win_cell[2][1]=c;
                win_cell[3][0]=r+3;
                win_cell[3][1]=c;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
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
                win_cell[0][0]=r;
                win_cell[0][1]=c;
                win_cell[1][0]=r;
                win_cell[1][1]=c+1;
                win_cell[2][0]=r;
                win_cell[2][1]=c+2;
                win_cell[3][0]=r;
                win_cell[3][1]=c+3;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
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
                win_cell[0][0]=r;
                win_cell[0][1]=c;
                win_cell[1][0]=r;
                win_cell[1][1]=c-1;
                win_cell[2][0]=r;
                win_cell[2][1]=c-2;
                win_cell[3][0]=r;
                win_cell[3][1]=c-3;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
                return true; //find 4 cell Left of player
            }
        }
        return false;
    }

    private boolean check_Diagonal_up_Right(int r,int c,String player)   // r=row , c=col
    {
        if((r>2)&&(c<4))
        {
            Log.d("check999","in if diagonal up Right ");
            if((cell_arr[r][c].equals(player))&&(cell_arr[r-1][c+1].equals(player))
                    &&(cell_arr[r-2][c+2].equals(player))&&(cell_arr[r-3][c+3].equals(player)))
            {
                win_cell[0][0]=r;
                win_cell[0][1]=c;
                win_cell[1][0]=r-1;
                win_cell[1][1]=c+1;
                win_cell[2][0]=r-2;
                win_cell[2][1]=c+2;
                win_cell[3][0]=r-3;
                win_cell[3][1]=c+3;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
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
                win_cell[0][0]=r;
                win_cell[0][1]=c;
                win_cell[1][0]=r-1;
                win_cell[1][1]=c-1;
                win_cell[2][0]=r-2;
                win_cell[2][1]=c-2;
                win_cell[3][0]=r-3;
                win_cell[3][1]=c-3;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
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
                win_cell[0][0]=r;
                win_cell[0][1]=c;
                win_cell[1][0]=r+1;
                win_cell[1][1]=c+1;
                win_cell[2][0]=r+2;
                win_cell[2][1]=c+2;
                win_cell[3][0]=r+3;
                win_cell[3][1]=c+3;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
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
                win_cell[0][0]=r;
                win_cell[0][1]=c;
                win_cell[1][0]=r+1;
                win_cell[1][1]=c-1;
                win_cell[2][0]=r+2;
                win_cell[2][1]=c-2;
                win_cell[3][0]=r+3;
                win_cell[3][1]=c-3;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
                return true; //find 4 cell Diagonal Down Left of player
            }
        }
        return false;
    }

}
