package com.example.myapplication;

import java.util.List;

public interface TaskListener {
    void onTasksFetchedFromDatabase(List<TaskModel> personList);
}
