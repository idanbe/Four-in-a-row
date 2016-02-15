package com.example.administrator.game_4_in_a_row;

// TODO : maybe should this class in the future , not delete !!!
public class Row {

    private String Name;
    private int Win;
    private int Loss;
    private int Standoff;
    private double Percent_Win;


    public String getName() {
            return this.Name;
        }
    public void setName(String name) {
        this.Name = name;
    }


    public int getWin() {
        return this.Win;
    }
    public void setWin(int win) {
        this.Win = win;
    }


    public int getLoss() {
        return this.Loss;
    }
    public void setTime(int loss) {
        this.Loss = loss;
    }


    public int getStandoff() {
        return this.Standoff;
    }
    public void setInfo(int standoff) {
        this.Standoff = standoff;
    }

    public double getPercent_Win() {
        return this.Percent_Win;
    }
    public void setPercent_Win(double percent_win) {
        this.Percent_Win = percent_win;
    }

}
