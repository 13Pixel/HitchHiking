package com.example.admin.findahitchhiher;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PersonDAO personDAO();
}
