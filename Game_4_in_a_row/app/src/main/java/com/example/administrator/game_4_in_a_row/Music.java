package com.example.administrator.game_4_in_a_row;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by AviramAlkobi on 17/02/2016.
 */
public class Music extends Service {
    private MediaPlayer mp;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }



    public void playMusic(Context context) {

    }

    public void onDestroy() {
        mp.stop();
    }

    public void onStart(Intent intent,int startId){
        mp.start();
    }
}

