package com.example.administrator.game_4_in_a_row;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by AviramAlkobi on 16/02/2016.
 */
public class HistoryTable extends Activity{

    private DAL dal;
    private ArrayList<Row> rowArrayList ;
    private static final int MAX_RESULT = 10;


    public HistoryTable(){
        this.dal = new DAL(this);
        rowArrayList = new ArrayList<Row>();
    }

    public ArrayList<Row> getRowArrayList(){
        return this.rowArrayList;
    }

    public DAL getDal(){
        return this.dal;
    }



    // update
    public void upDateHistoryTable(String name , boolean ifWin , boolean ifStendOff){
        dal.upDateWinOrLoss(name, ifWin, ifStendOff);
        rowArrayList = dal.getDb();
    }

    // remove
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

}
