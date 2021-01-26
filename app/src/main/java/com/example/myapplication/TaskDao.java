package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    public void insert(TaskModel taskModel);

    @Update
    public void update(TaskModel taskModel);

    @Delete
    public void delete(TaskModel taskModel);

    @Query("SELECT * FROM Task")
    public List<TaskModel> getTasks();
}
