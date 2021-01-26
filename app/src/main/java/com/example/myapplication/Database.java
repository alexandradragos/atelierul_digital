package com.example.myapplication;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {TaskModel.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static Database INSTANCE;

    public static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class,
                    "taskDatabase")
                    .build();
        }

        return INSTANCE;
    }
}
