package com.example.admin.findahitchhiher;

import android.app.Application;
import android.arch.persistence.room.Room;

public class AppActivity extends Application {
    static AppDatabase db;

    @Override
    public void onCreate(){
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ny_app_db").allowMainThreadQueries().build();
        }

        public static AppDatabase getDatabase(){
        return db;
        }
}
