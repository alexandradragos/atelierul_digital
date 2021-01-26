package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class TaskRecyclerAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    //The input of Adapter
    private List<TaskModel> todoList;
    private MainActivity activity;

    public TaskRecyclerAdapter(List<TaskModel> todoList, MainActivity activity) {
        this.todoList = todoList;
        this.activity = activity;
    }


    /**
     * @return the size of all the elements thar RecyclerView has to display
     */
    @Override
    public int getItemCount() {
        return todoList.size();
    }

    /**
     * @return the TaskViewHolder that we created
     */
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflate the view in parent
        View view = View.inflate(parent.getContext(), R.layout.task_item, null);
        return new TaskViewHolder(view);
    }

    /**
     * populates the viewHolders with our data
     */
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int position) {

        TaskModel task = todoList.get(position);

        //fill the elements of the task_item by using TaskViewHolder
        taskViewHolder.getContentText().setText(task.getContent());
        taskViewHolder.getDateText().setText(task.getDate().substring(0, task.getDate().length() - 9));
        taskViewHolder.getStatusCheckBox().setChecked(toBoolean(task.getStatus()));
        taskViewHolder.getStatusCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO: 26-Jan-21 Fix the update list after modifying the checkbox
                        todoList = Database.getDatabase(activity).taskDao().getTasks();
                        Collections.reverse(todoList);
                        if (isChecked) {
                            task.setStatus(1);
                        } else {
                            task.setStatus(0);
                        }
                        Database.getDatabase(activity).taskDao().update(task);
                    }
                }).start();
            }
        });
    }

    private boolean toBoolean(int n) {
        return n != 0;
    }

    public void setData(List<TaskModel> todoList) {
        this.todoList.clear();
        this.todoList.addAll(todoList);
        notifyDataSetChanged();
    }

    public void deleteTask(int position) {
        TaskModel task = todoList.get(position);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Database.getDatabase(activity).taskDao().delete(task);
                todoList = Database.getDatabase(activity).taskDao().getTasks();
                Collections.reverse(todoList);
            }
        }).start();
        activity.onTasksFetchedFromDatabase(todoList);
    }

    public Context getActivity() {
        return activity;
    }
}
