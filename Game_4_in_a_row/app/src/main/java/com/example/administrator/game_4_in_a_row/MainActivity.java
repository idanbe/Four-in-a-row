package com.example.administrator.game_4_in_a_row;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Intent intent;
    private Button single_button,two_button,setting_button,game_history_button,exit_button;
    static final String onePlayer_key ="player1";
    static final String twoPlayer_key ="player2";
    static final String ONE_PLAYER ="one_player";
    static final String TWO_PLAYER ="two_player";
    private static final String MUSIC_STATUS = "get_status_music";
    private MediaPlayer music;
    private static Boolean MusicOff = true ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("@on", "onCreate");

        music = MediaPlayer.create(MainActivity.this, R.raw.to_smuck);
        music.setLooping(true);


        if(MusicOff) {
            music.start();
            MusicOff = false;
        }
        DAL dal = new DAL(this);

        ArrayList<Row> rows = dal.getDb();
        for(int i = 0 ; i < rows.size() ; i++){
            Row row = rows.get(i);
            System.out.println(row.getName() + " , " + row.getWin() + " , " + row.getLoss() + " , " + row.getDraws() + " , " + row.getPercent_Win() + "%" );
        }


        single_button = (Button)findViewById(R.id.button_single);
        two_button = (Button)findViewById(R.id.button_two);
        setting_button = (Button)findViewById(R.id.button_setting);
        game_history_button = (Button)findViewById(R.id.button_history);
        exit_button = (Button)findViewById(R.id.button_Exit);


        // single player
        single_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debug","single player");
                intent = new Intent(v.getContext(), Names.class);
                intent.putExtra(onePlayer_key,ONE_PLAYER.toString());
                intent.putExtra( MUSIC_STATUS , music.getCurrentPosition() );
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // Two players
        two_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debug","two players");
                intent = new Intent(v.getContext(), Names.class);
                intent.putExtra(twoPlayer_key,TWO_PLAYER.toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        // setting
        setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debug","settings");
                intent = new Intent(v.getContext(), Settings.class);
                startActivity(intent);
            }
        });


        // game history
        game_history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debug","game history");
                intent = new Intent(v.getContext(), Game_history.class);
                startActivity(intent);

            }
        });

        // exit
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
                Log.d("debug", "exit");
            }
        });






    }





    @Override
    protected void onResume() {
        super.onResume();
        music.start();
        Log.d("@on", "onResume");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("@on", "onStop");

    }


    @Override
    protected void onPause() {
        super.onPause();
        music.pause();
        Log.d("@on", "onPause");
    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
