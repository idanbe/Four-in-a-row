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

    // not complete !!!!!!!!!!!!!!!!!!!
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

        //dal = new DAL(this);

        // TODO : remove this line
        //dal.removeAll();

        /*dal.addUser("1", 0);
        dal.addUser("2" , 1);
        dal.addUser("3" , 10);
        dal.addUser("4" , 70);
        dal.addUser("5" , 30);
        dal.addUser("6" , 40);
        dal.addUser("7" , 50);
        dal.addUser("13" , 50);
        dal.addUser("12" , 50);
        dal.addUser("11" , 50);
        dal.addUser("10" , 50);
        /*dal.addUser("8");
        dal.addUser("9");
        dal.addUser("10");
        dal.addUser("11");
        dal.addUser("12");*/

        HistoryTable = (TableLayout)findViewById(R.id.history_table);
        HistoryTable.setStretchAllColumns(true);

        rowArrayList = dal.getDb();
        printArray();

        int j = 0 ;
        // print in history table
        while ( !rowArrayList.isEmpty() && j < MAX_RESULT){
            int i = sortArrayList();
            Row row = rowArrayList.get(i) ;
            addToHistoryTable(row.getName(), row.getWin(), row.getLoss(), row.getDraws(), row.getPercent_Win());
            rowArrayList.remove(i);
            j++ ;
        }

        System.out.println("after !!");
        printArray();







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
