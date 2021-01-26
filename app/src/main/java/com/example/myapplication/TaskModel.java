package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Task")
public class TaskModel {

    @SerializedName("taskId")
    @PrimaryKey(autoGenerate = true)
    private int taskId;

    @ColumnInfo(name = "status")
    @SerializedName("status")
    private int status;

    @ColumnInfo(name = "content")
    @SerializedName("content")
    private String content;

    @ColumnInfo(name = "date")
    @SerializedName("date")
    private String date;

    public TaskModel() {
    }

    public TaskModel(int id, int status, String content, String date) {
        this.taskId = id;
        this.status = status;
        this.content = content;
        this.date = date;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
