package com.example.administrator.game_4_in_a_row;

import android.provider.BaseColumns;

/**
 * Created by AviramAlkobi on 14/02/2016.
 */
public class Tables {

    public Tables() {};

    public static abstract class winnersTable implements BaseColumns {

        // table name
        public static final String TABLE_NAME = "Winners";

        // ID column automatically
        public static final String NAME = "name";
        public static final String WINS = "wins";
        public static final String LOSSES = "losses";
        public static final String PERCENT_OF_WINS = "percents";
    }
}


