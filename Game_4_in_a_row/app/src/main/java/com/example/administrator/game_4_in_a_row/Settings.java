package com.example.administrator.game_4_in_a_row;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    private Button sound,vibriton;

    private final String SOUND_OFF="Sound Off";
    private final String SOUND_ON="Sound On";
    private final String Vibrtion_OFF="Vibration Off";
    private final String Vibrtion_ON="Vibration On";
    private final String SETTING_KEY_SOUND = "SETTING_KEY_SOUND";
    private final String SETTING_KEY_VIBRITON = "SETTING_KEY_VIBRITON";

    private final String SHARED_PREFERENCES_NAME = "ShardPreferences_setting";

    private final String ON = "on";
    private final String OFF = "off";

    private SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sound = (Button) findViewById(R.id.button_sound);
        vibriton = (Button) findViewById(R.id.button_Vibration);

        sharedpreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();


        if(sharedpreferences.getString(SETTING_KEY_SOUND, null) == null){
            sound.setText(SOUND_ON);
            editor.putString(SETTING_KEY_SOUND, ON);
            editor.commit();
        }
        else if(sharedpreferences.getString(SETTING_KEY_SOUND, null).equals(OFF)){
            sound.setText(SOUND_OFF);
        }


        if(sharedpreferences.getString(SETTING_KEY_VIBRITON , null) == null){
            vibriton.setText(Vibrtion_ON);
            editor.putString(SETTING_KEY_VIBRITON, ON);
            editor.commit();
        }
        else if(sharedpreferences.getString(SETTING_KEY_VIBRITON , null).equals(OFF)){
            vibriton.setText(Vibrtion_OFF);
        }


        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = sharedpreferences.getString(SETTING_KEY_SOUND , null);
                if( s.equals(ON) ) {
                    sound.setText(SOUND_OFF);
                    editor.putString(SETTING_KEY_SOUND, OFF);
                }
                else
                {
                    sound.setText(SOUND_ON);
                    editor.putString(SETTING_KEY_SOUND, ON);
                }
                editor.commit();
            }
        });


        vibriton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = sharedpreferences.getString(SETTING_KEY_VIBRITON , null);
                if( s.equals(ON) ) {
                    vibriton.setText(Vibrtion_OFF);
                    editor.putString(SETTING_KEY_VIBRITON , OFF);
                }
                else
                {
                    vibriton.setText(Vibrtion_ON);
                    editor.putString(SETTING_KEY_VIBRITON, ON);
                }
                editor.commit();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
