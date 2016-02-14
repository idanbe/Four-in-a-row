package com.example.administrator.game_4_in_a_row;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Names extends AppCompatActivity {

    private Intent intent;
    private Button back_button,next_button;
    private EditText p1,p2;
    static final String p1_key ="key1";
    static final String p2_key ="key2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);


        back_button = (Button)findViewById(R.id.button_back);
        next_button = (Button)findViewById(R.id.button_next);
        p1 = (EditText)findViewById(R.id.player1_textv);
        p2 = (EditText)findViewById(R.id.player2_text);

        //
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), Game.class);
                intent.putExtra(p1_key, p1.getText().toString());
                intent.putExtra(p2_key, p2.getText().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                startActivity(intent);
            }
        });


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(),Game_options.class);
                startActivity(intent);
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
