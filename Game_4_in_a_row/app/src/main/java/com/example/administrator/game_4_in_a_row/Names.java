package com.example.administrator.game_4_in_a_row;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Names extends AppCompatActivity {

    private Intent intent;
    private Button back_button,next_button;
    private EditText p1,p2;
    static final String p1_key ="key1";
    static final String p2_key ="key2";
    static final String onePlayer_key ="player1";
    static final String twoPlayer_key ="player2";
    static final String Game_Type_key ="gameType_key";
    private Bundle bundle;
    private String non_player_name ="";
    private DAL dal;
    private View view ;
    private Context context;
    private ProgressDialog progress ;
    private AlertDialog alertDialog;
    static final String COMPUTER ="Computer";
    private final String SETTING_KEY_SOUND = "SETTING_KEY_SOUND";
    private final String SHARED_PREFERENCES_NAME = "ShardPreferences_setting";
    private final String ON = "on";
    private final String NAME_PLAYERS = "Names of players:";
    private final String VS = "\n VS \n";


    public Context get_Context(){
        return context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);

        bundle = getIntent().getExtras();


        SharedPreferences sharedpreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if(sharedpreferences.getString(SETTING_KEY_SOUND, null) == null || sharedpreferences.getString(SETTING_KEY_SOUND, null).equals(ON) ){
            MainActivity.getMusic().start();
            System.out.println("sound true !");
        }


        /*if(bundle != null) {
            int b = bundle.getInt(MUSIC_STATUS);
            System.out.println("b = " + b);
            music = MediaPlayer.create(Names.this, R.raw.to_smuck);
            music.seekTo(bundle.getInt(MUSIC_STATUS));
            music.start();

        }*/



        context = this;
        dal = new DAL(this);

        back_button = (Button)findViewById(R.id.button_back);
        next_button = (Button)findViewById(R.id.button_next);
        p1 = (EditText)findViewById(R.id.player1_textv);
        p2 = (EditText)findViewById(R.id.player2_text);



        // get game type



        if (bundle != null)
        {
            if(!(bundle.getString(onePlayer_key)==null))
            {
                non_player_name =bundle.getString(onePlayer_key).toString();
                p2.setVisibility(View.INVISIBLE);
                p2.setText(COMPUTER);
            }
            if(!(bundle.getString(twoPlayer_key)==null))
            {
                non_player_name =bundle.getString(twoPlayer_key).toString();
            }
        }

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = v ;
                alertDialog = new AlertDialog.Builder(Names.this).create();
                String s  = "";
                alertDialog.setTitle(NAME_PLAYERS);
                if (p1.getText().toString().isEmpty()) {
                    s += onePlayer_key;
                }
                else {
                    s += p1.getText().toString();
                }
                s += VS;
                if (p2.getText().toString().isEmpty()){
                        s += twoPlayer_key;
                }
                else {
                    s += p2.getText().toString();
                }

                // try to aligment text
                /*TextView messageText = (TextView)alertDialog.findViewById(android.R.id.message);
                messageText.setGravity(Gravity.CENTER);*/
                if(p1.getText().toString().equals(p2.getText().toString())){
                    s = onePlayer_key + "\n VS \n" + twoPlayer_key ;
                }
                alertDialog.setMessage(s);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                progress = ProgressDialog.show(Names.this, "Pleas wait", "Load Game..", true);
                                // go to game
                                intent = new Intent(view.getContext(), Game.class);
                                intent.putExtra(p1_key, p1.getText().toString());
                                intent.putExtra(p2_key, p2.getText().toString());
                                intent.putExtra(Game_Type_key, non_player_name.toString());

                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                p1.setText("");
                                p2.setText("");

                                progress.dismiss();
                                // go to game
                                startActivity(intent);
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();



            }
        });


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = ProgressDialog.show(get_Context(), "Pleas wait", "Load Game..", true);
                progress.show();
                p1.setText("");
                p2.setText("");
                progress.cancel();
                onBackPressed();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_names, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
