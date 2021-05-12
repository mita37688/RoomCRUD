package com.example.sqliteandroid_roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract StudentDao studentDao();
}
