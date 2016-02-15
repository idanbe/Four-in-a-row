package com.example.administrator.game_4_in_a_row;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    private Button sound,vibriton;
    private String sound_state="on";
    private String vibration_state="on";
    private final String SOUND_OFF="Sound Off";
    private final String SOUND_ON="Sound On";
    private final String Vibrtion_OFF="Vibration Off";
    private final String Vibrtion_ON="Vibration On";
    private final String ON="on";
    private final String OFF="off";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sound = (Button) findViewById(R.id.button_sound);
        vibriton = (Button) findViewById(R.id.button_Vibration);



        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sound_state.equals(ON)) {
                    sound.setText(SOUND_OFF);
                }
                else
                {
                    sound.setText(SOUND_ON);
                }
            }
        });


        vibriton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vibration_state.equals(Vibrtion_ON))
                {
                    vibriton.setText(Vibrtion_OFF);
                }
                else
                {
                    vibriton.setText(Vibrtion_ON);
                }
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
