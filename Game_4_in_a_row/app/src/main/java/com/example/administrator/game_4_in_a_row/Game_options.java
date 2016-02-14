package com.example.administrator.game_4_in_a_row;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Game_options extends AppCompatActivity {

    private Intent intent;
    private Button next_button,back_button;
    private RadioButton r1,r2,radioButton_select;
    private int selectedId;
    private RadioGroup radioType;
    static final String gameType_key ="key3";
    private String str;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);


        back_button = (Button)findViewById(R.id.button_back_gameOp);
        next_button = (Button)findViewById(R.id.button_next_gameOp);
        r1 =(RadioButton)findViewById(R.id.radioButton_sameDvice);
        r2 =(RadioButton)findViewById(R.id.radioButton_different);
        radioType = (RadioGroup) findViewById(R.id.radioType);




        //
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                 selectedId = radioType.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                 radioButton_select = (RadioButton) findViewById(selectedId);
                Log.d("chose3434",radioButton_select.getText().toString());

                intent = new Intent(v.getContext(), Names.class);
                startActivity(intent);
            }
        });


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_options, menu);
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
