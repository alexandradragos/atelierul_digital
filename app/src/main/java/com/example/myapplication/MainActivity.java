package com.example.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener, TaskListener {

    private RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;

    private TaskRecyclerAdapter taskRecyclerAdapter;

    private List<TaskModel> todoList = new ArrayList<>();

    private FloatingActionButton addTaskButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configure the behaviour of AddTask Button
        addTaskButton = findViewById(R.id.addButton);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display the add task fragment
                AddTask.newInstance().show(getSupportFragmentManager(), AddTask.TAG);
            }
        });

        //Hide the ActionBar which contains the name of the project
        getSupportActionBar().hide();

        //Get the RecyclerView from activity_main.xml
        recyclerView = findViewById(R.id.recyclerView);

        //organize the views(horizontally or verically) by creating a LayoutManager
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //Set the LayoutManager of RecyclerView
        recyclerView.setLayoutManager(layoutManager);

        //Get the Data Source
        getDataFromDatabase(this);
        //Create the adapter for the RecyclerView
        taskRecyclerAdapter = new TaskRecyclerAdapter(todoList, this);

        //Set the adapter for out RecyclerView
        recyclerView.setAdapter(taskRecyclerAdapter);

        //Define the TouchHelper for swiping items from recycler
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new TaskTouchHelper(taskRecyclerAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }

    private List<TaskModel> getMockData() {
        List<TaskModel> todoList = new ArrayList<>();

        todoList.add(new TaskModel(1, 0, "First task", getCurrentDate()));
        todoList.add(new TaskModel(2, 0, "Second task", getCurrentDate()));
        todoList.add(new TaskModel(3, 0, "Third task", getCurrentDate()));
        todoList.add(new TaskModel(4, 0, "Fourth task", getCurrentDate()));
        todoList.add(new TaskModel(5, 0, "Fifth task", getCurrentDate()));
        todoList.add(new TaskModel(6, 0, "Sixth task", getCurrentDate()));

        return todoList;
    }

    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    @Override
    public void handleDialogClose(DialogInterface dialogInterface) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                todoList = Database.getDatabase(getApplicationContext()).taskDao().getTasks();
                Collections.reverse(todoList);
                onTasksFetchedFromDatabase(todoList);
            }
        }).start();

    }

    @Override
    public void onTasksFetchedFromDatabase(List<TaskModel> todoList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                taskRecyclerAdapter.setData(todoList);
            }
        });
    }

    private void getDataFromDatabase(Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                todoList = Database.getDatabase(activity).taskDao().getTasks();
                Collections.reverse(todoList);
                onTasksFetchedFromDatabase(todoList);
            }
        }).start();
    }

}