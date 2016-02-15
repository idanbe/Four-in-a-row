package com.example.administrator.game_4_in_a_row;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Game_history extends AppCompatActivity {

    private Intent intent;
    private DAL dal;
    TableLayout HistoryTable ;
    TableRow Row ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);
        dal = new DAL(this);

        HistoryTable = (TableLayout)findViewById(R.id.history_table);
        HistoryTable.setStretchAllColumns(true);

        
        TextView t0 = new TextView(this);
        TextView t1 = new TextView(this);
        TextView t2 = new TextView(this);
        TextView t3 = new TextView(this);
        TextView t4 = new TextView(this);
        t0.setText("0");
        t0.setTextSize(10);
        t0.setGravity(Gravity.CENTER);

        t1.setText("0");
        t1.setTextSize(10);
        t1.setGravity(Gravity.CENTER);

        t2.setText("0");
        t2.setTextSize(10);
        t2.setGravity(Gravity.CENTER);

        t3.setText("0");
        t3.setTextSize(10);
        t3.setGravity(Gravity.CENTER);

        t4.setText("0");
        t4.setTextSize(10);
        t4.setGravity(Gravity.CENTER);

        Row = new TableRow(this);

        Row.addView(t0);
        Row.addView(t1);
        Row.addView(t2);
        Row.addView(t3);
        Row.addView(t4);

        HistoryTable.addView(Row);

        /// TODO : test DB
       /* dal.removeAll();
        dal.addUser("aviram");
        dal.upDateWinOrLoss("aviram", true, true);
        System.out.println(dal.getDb().toString());
        dal.upDateWinOrLoss("aviram", true, true);
        System.out.println(dal.getDb().toString());
        dal.upDateWinOrLoss("aviram", true, false);
        System.out.println(dal.getDb().toString());
        dal.upDateWinOrLoss("aviram", true, false);
        System.out.println(dal.getDb().toString());
        dal.upDateWinOrLoss("aviram", true, false);
        System.out.println(dal.getDb().toString());
        dal.upDateWinOrLoss("aviram", true, false);
        System.out.println(dal.getDb().toString());
        dal.upDateWinOrLoss("aviram", false, false);
        System.out.println(dal.getDb().toString());
        dal.upDateWinOrLoss("aviram", false, false);
        System.out.println(dal.getDb().toString());
        dal.addUser("alkobi");
        System.out.println(dal.getDb().toString());
        dal.removeRow("alkobi");
        System.out.println(dal.getDb().toString());
        dal.removeRow("aviram");
        System.out.println(dal.getDb().toString());*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_history, menu);
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

            intent = new Intent(Game_history.this, Settings.class);
            startActivity(intent);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
