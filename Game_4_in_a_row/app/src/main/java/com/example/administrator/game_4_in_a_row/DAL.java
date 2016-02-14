package com.example.administrator.game_4_in_a_row;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Created by AviramAlkobi on 14/02/2016.
 */



public class DAL {

    private DBHelper helper;
    private final int init = 0;

    // object like struct
    private static class WinnersHolder {
        int win;
        int loss;
    }

    public DAL(Context context){
        helper = new DBHelper(context);
    }

    // add row to DB
    public void addUser(String name){
        SQLiteDatabase db = helper.getWritableDatabase();

        //values to save
        ContentValues values = new ContentValues();

        // add to table
        values.put(Tables.winnersTable.NAME, name);
        values.put(Tables.winnersTable.WINS, 0);
        values.put(Tables.winnersTable.LOSSES, 0);
        values.put(Tables.winnersTable.PERCENT_OF_WINS , 0);

        //save the values
        db.insert(Tables.winnersTable.TABLE_NAME, null, values);
        db.close();

    }

    // upDate DB
    public void upDateWinOrLoss(String name , Boolean thereIsWin){
        SQLiteDatabase db = helper.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();

        // Holder for 2 integer
        WinnersHolder info  = getPrevResult(name);

        // if player win
        if (thereIsWin) {
            values.put(Tables.winnersTable.WINS, info.win+1);
            values.put(Tables.winnersTable.PERCENT_OF_WINS , info.win+1 / (info.loss + info.win+1) );
        }
        // if player loss
        else {
            values.put(Tables.winnersTable.LOSSES , info.loss+1);
            values.put(Tables.winnersTable.PERCENT_OF_WINS , info.win / (info.loss + info.win+1) );
        }

        // Which row to update, based on the ID
        String selection = Tables.winnersTable.NAME + " = " + name ;
        //String[] selectionArgs = { String.valueOf(rowId) };

        int count = db.update(Tables.winnersTable.TABLE_NAME, values, selection, null);
    }


    // get number of wins and loss according to user name
    public WinnersHolder getPrevResult(String name){

        WinnersHolder info = new WinnersHolder() ;

        //get cursor on start row
        Cursor c = getRow(name);

        if (c != null) {
            while (c.moveToNext()) {

                //get column index
                int lossColumnIndex = c.getColumnIndex(Tables.winnersTable.WINS);
                int winColumnIndex = c.getColumnIndex(Tables.winnersTable.WINS);

                try{
                    info.win = c.getInt(winColumnIndex);
                    info.loss = c.getInt(lossColumnIndex);
                }
                catch (Exception e){
                }
            }
        }
        return info;
    }

    // if there is row with this complexity and level
    private Boolean thereIsRow(String name){
        Cursor c = getRow(name);
        if(c.moveToNext()){
            return true;
        }
        return false;
    }

    // get row according to complexity and level
    private Cursor getRow(String name){
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + Tables.winnersTable.TABLE_NAME + " WHERE "
                + Tables.winnersTable.NAME + " = " + name, null);

        return c;
    }

 //**********************************************************************//
        // TODO : remove this function  , only for test
    // remove all rows
    public  void removeAll(){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(Tables.winnersTable.TABLE_NAME, null, null);
    }

    // remove one row
    public  void removeRow(String name){
        SQLiteDatabase db = helper.getWritableDatabase();

        // search the correct row
        String selection = Tables.winnersTable.NAME + " = " + name ;

        // remove from DB
        db.delete(Tables.winnersTable.TABLE_NAME, selection, null);
    }

    // TODO : remove this function  , only for test
    public Cursor getAllDBCursor(){
        SQLiteDatabase db = helper.getWritableDatabase();
        // get all DB
        Cursor c = db.rawQuery("SELECT * FROM " + Tables.winnersTable.TABLE_NAME, null);

        return c;
    }

    // TODO : remove this function , only for test
    // write all DB in ArrayList
    public ArrayList getDb(){

        ArrayList entryTimes = new ArrayList();

        //get cursor
        Cursor c = getAllDBCursor();

        if (c != null) {
            while (c.moveToNext()) {
                //get column index

                int entryTimeColumnIndex = c.getColumnIndex(Tables.winnersTable.NAME);

                //get entry
                try{
                    String entryTime = c.getString(entryTimeColumnIndex);
                    entryTimes.add(entryTime);
                }
                //save in array
                catch (Exception e){
                }

                entryTimeColumnIndex = c.getColumnIndex(Tables.winnersTable.WINS);

                //get entry
                try{
                    String entryTime = c.getString(entryTimeColumnIndex);
                    entryTimes.add(entryTime);
                }
                //save in array
                catch (Exception e){
                }

                entryTimeColumnIndex = c.getColumnIndex(Tables.winnersTable.LOSSES);

                //get entry
                try{
                    String entryTime = c.getString(entryTimeColumnIndex);
                    entryTimes.add(entryTime);
                }
                //save in array
                catch (Exception e){

                }

                entryTimeColumnIndex = c.getColumnIndex(Tables.winnersTable.PERCENT_OF_WINS);

                //get entry
                try{
                    String entryTime = c.getString(entryTimeColumnIndex);
                    entryTimes.add(entryTime);
                }
                //save in array
                catch (Exception e){

                }

            }

        }
        return entryTimes;
    }

}
