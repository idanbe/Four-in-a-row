package com.example.administrator.game_4_in_a_row;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Vibrator;

public class Game extends AppCompatActivity {

    private final int Zero=0,ONE=1,TWO=2,Three=3,Four=4,Five=5,Six=6,Seven=7;
    private int VIB_TIME=400;
    private int i,j;
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
    private final String PLAYER_1 = "Player1";
    private final String PLAYER_2 = "Player2";
    private final String TURN=" turn";
    static final String ONE_PLAYER ="one_player";
    static final String TWO_PLAYER ="two_player";
    static final String COMPUTER ="Computer";
    static final String TIE ="It's a Tie";
    static final String WIN =" Win";
    private String turn;
    private static MyView myView ;
    private boolean Game_on;
    private View view ;
    private float width_cell;
    private Vibrator v;
    private Bundle bundle;
    static final String p1_key ="key1";
    static final String p2_key ="key2";
    static final String gameType_key ="gameType_key";
    private String gameType;
    private Button back_button,reset_button;
    private AiMove Ai;
    DAL dal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        dal = new DAL(this);

//****************************************************************************************//

        // function map for idan !!!
        // all what you need is to put the correct function in the correct place .
        // function map for Avirahm gay !!!


       // create user
       /* dal.addUser("user name");

        // add one **WIN** to user according to user name
        dal.upDateWinOrLoss("user name", true, false);

        // // add one **LOSS** to user according to user name
        dal.upDateWinOrLoss("user name" , false , false);

        // add one **DRAW** to user according to user name
        dal.upDateWinOrLoss("user name" , false , true);

        // remove all DB  and all history layout !!
        //dal.removeAll();

        // remove user name from DB and all his statistics
        //dal.removeRow("user name");*/


        bundle = getIntent().getExtras();

        // get names of players
        if (bundle != null) {
            if(!bundle.getString(p1_key).toString().equals("")) //check not empty
            {
                player1_name=bundle.getString(p1_key).toString();
                PLAYER1_turn=player1_name + TURN;
            }
            if(!bundle.getString(p2_key).toString().equals(""))
            {
                player2_name=bundle.getString(p2_key).toString();
                PLAYER2_turn=player2_name + TURN;
            }
            if(player1_name.equals(player2_name)){
                PLAYER1_turn = PLAYER_1 + TURN;
                PLAYER2_turn = PLAYER_2 + TURN;

                player1_name = PLAYER_1;
                player2_name = PLAYER_2 ;
            }
            if(!(bundle.getString(gameType_key)==null))
            {
                gameType=bundle.getString(gameType_key);
            }
        }

        if(gameType.equals(ONE_PLAYER))
        {
            Ai = new AiMove();
            player2_name=COMPUTER;
            PLAYER2_turn=COMPUTER + TURN;
        }

        Game_on = true;
        cell_arr = new String[Six][Seven];
        clear_cell_Arr();
        win_cell = new int[Four][TWO];
        view = findViewById(R.id.view);
        turn = PLAYER1_turn;
        myView = new MyView(this);
        myView.set_find_win(false);
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        player_turn = (TextView) findViewById(R.id.text_player_turn);

        back_button = (Button)findViewById(R.id.button_backGame);
        reset_button = (Button)findViewById(R.id.button_Reset);

        if (turn.equals(PLAYER1_turn)) {
            player_turn.setText(PLAYER1_turn);
        } else {
            player_turn.setText(PLAYER2_turn);
        }



        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(v.getContext(), Game.class);
                intent.putExtra(p1_key, player1_name);
                intent.putExtra(p2_key, player2_name);
                intent.putExtra(gameType_key,gameType);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(Game_on )
                {
                    width_cell = v.getWidth() / Seven;

                    if (event.getX() < width_cell) {
                        insert_coin(turn, Zero);
                    }
                    if ((event.getX() > (width_cell)) && (event.getX() < (width_cell * TWO))) {
                        insert_coin(turn, ONE);
                    }
                    if ((event.getX() > (width_cell * TWO)) && (event.getX() < (width_cell * Three))) {
                        insert_coin(turn, TWO);
                    }
                    if ((event.getX() > (width_cell * Three)) && (event.getX() < (width_cell * Four))) {
                        insert_coin(turn, Three);
                    }
                    if ((event.getX() > (width_cell * Four)) && (event.getX() < (width_cell * Five))) {
                        insert_coin(turn, Four);
                    }
                    if ((event.getX() > (width_cell * Five)) && (event.getX() < (width_cell * Six))) {
                        insert_coin(turn, Five);
                    }
                    if ((event.getX() > (width_cell * Six)) && (event.getX() < (width_cell * Seven))) {
                        insert_coin(turn, Six);
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
        for(i=Zero;i<Six;i++)
        {
            for ( j=Zero;j<Seven;j++)
            {
               if(cell_arr[i][j].equals(EMPTY))
               {
                   return false;//there  is empty cell
               }
            }
        }

        return true;
    }


    private void comp_move() //computer move
    {
        Ai.setCell_arr(cell_arr);
        insert_coin(turn, Ai.Ai_move());
        return;
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




    private void insert_coin(String player,int col)
    {
        //todo aviram adiot here is draw
        if(check_board_full())//Draw
        {
            dal.addUser(player1_name);
            dal.addUser(player2_name);
            dal.upDateWinOrLoss(player1_name , false , true); //set draw
            if(gameType.equals(TWO_PLAYER))
            {
                dal.upDateWinOrLoss(player2_name , false , true);//set draw
            }
            player_turn.setText(TIE);
            Game_on=false;
            return;
        }


        int i=Five;
            while ((i>=Zero)&&(!cell_arr[i][col].equals(EMPTY)))//check if empty cell in col
            {
                i=i-ONE;
            }
        if(i==-ONE)//col is full
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


    //todo aviram adiot here is WIN
        if(check_win(player))//win
        {
            dal.addUser(player1_name);
            dal.addUser(player2_name);
            if(player.equals(PLAYER1_turn)) //player 1 win
            {
                dal.upDateWinOrLoss(player1_name, true, false); //add win
              //  if(gameType.equals(TWO_PLAYER)) {
                    dal.upDateWinOrLoss(player2_name, false, false); //add loss
                //}
            }
            else //player 2 win
            {
                dal.upDateWinOrLoss(player2_name, true, false); //add win
              //  if(gameType.equals(TWO_PLAYER)) {
                    dal.upDateWinOrLoss(player1_name, false, false); //add loss
                //}
            }
            Game_on=false;
        }
        change_turn();

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
                if(gameType.equals(ONE_PLAYER)) {
                    comp_move();
                }

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
                myView.set_winer(PLAYER1);
                v.vibrate(VIB_TIME);
                player_turn.setText(player1_name + WIN);

            }
            else
            {
                myView.set_winer(PLAYER2);
                v.vibrate(VIB_TIME);
                player_turn.setText(player2_name + WIN);
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
        for (i = Zero; i < Six; i++)
        {
            for ( j = Zero; j < Seven; j++)
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

            for(i=Zero;i<Six;i++)
        {
            for(j=Zero;j<Seven;j++)
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

        if(r>TWO)
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r-ONE][c].equals(player))
            &&(cell_arr[r-TWO][c].equals(player))&&(cell_arr[r-Three][c].equals(player)))
            {
                win_cell[Zero][Zero]=r;
                win_cell[Zero][ONE]=c;
                win_cell[ONE][Zero]=r-ONE;
                win_cell[ONE][ONE]=c;
                win_cell[TWO][Zero]=r-TWO;
                win_cell[TWO][ONE]=c;
                win_cell[Three][Zero]=r-Three;
                win_cell[Three][ONE]=c;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
                return true; //find 4 cell up of player
            }
        }
        return false;
    }


    private boolean check_Down(int r,int c,String player)   // r=row , c=col
    {
        if(r<Three)
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r+ONE][c].equals(player))
                    &&(cell_arr[r+TWO][c].equals(player))&&(cell_arr[r+Three][c].equals(player)))
            {
                win_cell[Zero][Zero]=r;
                win_cell[Zero][ONE]=c;
                win_cell[ONE][Zero]=r+ONE;
                win_cell[ONE][ONE]=c;
                win_cell[TWO][Zero]=r+TWO;
                win_cell[TWO][ONE]=c;
                win_cell[Three][Zero]=r+Three;
                win_cell[Three][ONE]=c;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
                return true; //find 4 cell Down of player
            }
        }
        return false;
    }


    private boolean check_Right(int r,int c,String player)   // r=row , c=col
    {
        if(c<Four)
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r][c+ONE].equals(player))
                    &&(cell_arr[r][c+TWO].equals(player))&&(cell_arr[r][c+Three].equals(player)))
            {
                win_cell[Zero][Zero]=r;
                win_cell[Zero][ONE]=c;
                win_cell[ONE][Zero]=r;
                win_cell[ONE][ONE]=c+ONE;
                win_cell[TWO][Zero]=r;
                win_cell[TWO][ONE]=c+TWO;
                win_cell[Three][Zero]=r;
                win_cell[Three][ONE]=c+Three;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
                return true; //find 4 cell Right of player
            }
        }
        return false;
    }


    private boolean check_Left(int r,int c,String player)   // r=row , c=col
    {
        if(c>TWO)
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r][c-ONE].equals(player))
                    &&(cell_arr[r][c-TWO].equals(player))&&(cell_arr[r][c-Three].equals(player)))
            {
                win_cell[Zero][Zero]=r;
                win_cell[Zero][ONE]=c;
                win_cell[ONE][Zero]=r;
                win_cell[ONE][ONE]=c-ONE;
                win_cell[TWO][Zero]=r;
                win_cell[TWO][ONE]=c-TWO;
                win_cell[Three][Zero]=r;
                win_cell[Three][ONE]=c-Three;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
                return true; //find 4 cell Left of player
            }
        }
        return false;
    }

    private boolean check_Diagonal_up_Right(int r,int c,String player)   // r=row , c=col
    {
        if((r>TWO)&&(c<Four))
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r-ONE][c+ONE].equals(player))
                    &&(cell_arr[r-TWO][c+TWO].equals(player))&&(cell_arr[r-Three][c+Three].equals(player)))
            {
                win_cell[Zero][Zero]=r;
                win_cell[Zero][ONE]=c;
                win_cell[ONE][Zero]=r-ONE;
                win_cell[ONE][ONE]=c+ONE;
                win_cell[TWO][Zero]=r-TWO;
                win_cell[TWO][ONE]=c+TWO;
                win_cell[Three][Zero]=r-Three;
                win_cell[Three][ONE]=c+Three;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
                return true; //find 4 cell Diagonal up Right of player
            }
        }
        return false;
    }

    private boolean check_Diagonal_up_Left(int r,int c,String player)   // r=row , c=col
    {
        if((r>TWO)&&(c>TWO))
        {   if((cell_arr[r][c].equals(player))&&(cell_arr[r-ONE][c-ONE].equals(player))
                    &&(cell_arr[r-TWO][c-TWO].equals(player))&&(cell_arr[r-Three][c-Three].equals(player)))
            {
                win_cell[Zero][Zero]=r;
                win_cell[Zero][ONE]=c;
                win_cell[ONE][Zero]=r-ONE;
                win_cell[ONE][ONE]=c-ONE;
                win_cell[TWO][ONE]=r-TWO;
                win_cell[TWO][ONE]=c-TWO;
                win_cell[Three][Zero]=r-Three;
                win_cell[Three][ONE]=c-Three;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
                return true; //find 4 cell Diagonal up Left of player
            }
        }
        return false;
    }


    private boolean check_Diagonal_Down_Right(int r,int c,String player)   // r=row , c=col
    {
        if((r<Three)&&(c<Four))
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r+ONE][c+ONE].equals(player))
                    &&(cell_arr[r+TWO][c+TWO].equals(player))&&(cell_arr[r+Three][c+Three].equals(player)))
            {
                win_cell[Zero][Zero]=r;
                win_cell[Zero][ONE]=c;
                win_cell[ONE][Zero]=r+ONE;
                win_cell[ONE][ONE]=c+ONE;
                win_cell[TWO][Zero]=r+TWO;
                win_cell[TWO][ONE]=c+TWO;
                win_cell[Three][Zero]=r+Three;
                win_cell[Three][ONE]=c+Three;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
                return true; //find 4 cell Diagonal Down Right of player
            }
        }
        return false;
    }

    private boolean check_Diagonal_Down_Left(int r,int c,String player)   // r=row , c=col
    {
        if((r<Three)&&(c>TWO))
        {
            if((cell_arr[r][c].equals(player))&&(cell_arr[r+ONE][c-ONE].equals(player))
                    &&(cell_arr[r+TWO][c-TWO].equals(player))&&(cell_arr[r+Three][c-Three].equals(player)))
            {
                win_cell[Zero][Zero]=r;
                win_cell[Zero][ONE]=c;
                win_cell[ONE][Zero]=r+ONE;
                win_cell[ONE][ONE]=c-ONE;
                win_cell[TWO][Zero]=r+TWO;
                win_cell[TWO][ONE]=c-TWO;
                win_cell[Three][Zero]=r+Three;
                win_cell[Three][ONE]=c-Three;
                myView.setwin_Cell(win_cell);
                myView.set_find_win(true);
                return true; //find 4 cell Diagonal Down Left of player
            }
        }
        return false;
    }

    // cancel back button
    @Override
    public void onBackPressed() {
    }


}
