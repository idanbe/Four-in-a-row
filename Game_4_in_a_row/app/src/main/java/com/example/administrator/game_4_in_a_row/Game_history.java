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
    private TableLayout HistoryTable ;
    private TableRow Row ;


     //object like struct
    static class RowHolder {
        TextView name;
        TextView win;
        TextView loss;
        TextView standoff;
        TextView percent_win;
    }

    // add row
    private void addToHistoryTable(String name){

        RowHolder rowHolder = new RowHolder();
        Row = new TableRow(this);

        rowHolder.name = new TextView(this);
        rowHolder.win = new TextView(this);
        rowHolder.loss = new TextView(this);
        rowHolder.standoff = new TextView(this);
        rowHolder.percent_win = new TextView(this);

        // name col
        rowHolder.name.setText(name);
        rowHolder.name.setGravity(Gravity.LEFT);

        // win col
        rowHolder.win.setText("0");
        rowHolder.win.setGravity(Gravity.CENTER);

        // loss col
        rowHolder.loss.setText("0");
        rowHolder.loss.setGravity(Gravity.CENTER);

        // standoff col
        rowHolder.standoff.setText("0");
        rowHolder.standoff.setGravity(Gravity.CENTER);

        // percent_win col
        rowHolder.percent_win.setText("0%");
        rowHolder.percent_win.setGravity(Gravity.RIGHT);

        // add to row
        Row.addView(rowHolder.name);
        Row.addView(rowHolder.win);
        Row.addView(rowHolder.loss);
        Row.addView(rowHolder.standoff);
        Row.addView(rowHolder.percent_win);
        // Color yellow
        Row.setBackgroundColor(Color.YELLOW);

        // add row to history Table
        HistoryTable.addView(Row);

        // add to DB
        dal.addUser(name);

        // TODO : to remove test !!
        System.out.println(dal.getDb().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);
        dal = new DAL(this);

        HistoryTable = (TableLayout)findViewById(R.id.history_table);
        HistoryTable.setStretchAllColumns(true);


        // test history table
        addToHistoryTable("maximum 15 Char");



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
