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

import java.util.ArrayList;

public class Game_history extends AppCompatActivity {

    private Intent intent;
    private DAL dal;
    private TableLayout HistoryTable ;
    private TableRow tableRow;
    private ArrayList<Row> rowArrayList ;

    private static final int MAX_RESULT = 10;


    //object like struct
    static class RowHolder {
        TextView name;
        TextView win;
        TextView loss;
        TextView standoff;
        TextView percent_win;
    }

    public Game_history(DAL dal){
        this.dal = dal;
    }

    public Game_history(){

    }
    // add row
    public void addToHistoryTable(String name , int win , int loss , int draws , double percent ){

        RowHolder rowHolder = new RowHolder();
        tableRow = new TableRow(this);

        rowHolder.name = new TextView(this);
        rowHolder.win = new TextView(this);
        rowHolder.loss = new TextView(this);
        rowHolder.standoff = new TextView(this);
        rowHolder.percent_win = new TextView(this);

        // name col
        rowHolder.name.setText(name);
        rowHolder.name.setGravity(Gravity.LEFT);

        // win col
        rowHolder.win.setText(Integer.toString(win));
        rowHolder.win.setGravity(Gravity.CENTER);

        // loss col
        rowHolder.loss.setText(Integer.toString(loss));
        rowHolder.loss.setGravity(Gravity.CENTER);

        // standoff col
        rowHolder.standoff.setText(Integer.toString(draws));
        rowHolder.standoff.setGravity(Gravity.CENTER);

        // percent_win col
        rowHolder.percent_win.setText(percent + "%");
        rowHolder.percent_win.setGravity(Gravity.RIGHT);

        // add to row
        tableRow.addView(rowHolder.name);
        tableRow.addView(rowHolder.win);
        tableRow.addView(rowHolder.loss);
        tableRow.addView(rowHolder.standoff);
        tableRow.addView(rowHolder.percent_win);
        // Color yellow
        tableRow.setBackgroundColor(Color.YELLOW);

        // add row to history Table
        HistoryTable.addView(tableRow);
    }

    // update
    public void upDateHistoryTable(String name , boolean ifwin , boolean ifStendOff){
        dal.upDateWinOrLoss(name, ifwin, ifStendOff);
        rowArrayList = dal.getDb();
    }

    public void removeRowFromHistory(String name){
        dal.removeRow(name);
        rowArrayList = dal.getDb();
    }

    // TODO : remove this , only for test
    public void printArray(){
        for(int i = 0 ; i < rowArrayList.size() ; i++){
            Row row = rowArrayList.get(i);
            System.out.println(row.getName() + " , " + row.getWin() + " , " + row.getLoss() + " , " + row.getDraws() + " , " + row.getPercent_Win() + "%" );
        }
    }

    public int sortArrayList(){
        int indexOfMakValue = 0 ;
        double max = 0 ;
        for(int i = 0 ; i < rowArrayList.size() ; i++){
            if(rowArrayList.get(i).getPercent_Win() > max){
                indexOfMakValue = i ;
                max = rowArrayList.get(i).getPercent_Win() ;
            }
        }
        return indexOfMakValue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);

        dal = new DAL(this);

        HistoryTable = (TableLayout)findViewById(R.id.history_table);
        HistoryTable.setStretchAllColumns(true);


        // DB to array list
        rowArrayList = dal.getDb();
/*****************************************/
        // print DB for test
        printArray();
/*****************************************/
        // print to history table
        int j = 0 ;
        while ( !rowArrayList.isEmpty() && j < MAX_RESULT){
            int i = sortArrayList();
            Row row = rowArrayList.get(i) ;
            addToHistoryTable(row.getName(), row.getWin(), row.getLoss(), row.getDraws(), row.getPercent_Win());
            rowArrayList.remove(i);
            j++ ;
        }
/*****************************************/
         //print to test
        System.out.println("after !!");
        printArray();
/********************************/

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
