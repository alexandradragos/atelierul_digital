package com.example.myapplication;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Used to fill each row with data
 */
public class TaskViewHolder extends RecyclerView.ViewHolder {

    private final CheckBox statusCheckBox;
    private final TextView contentText;
    private final TextView dateText;


    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        statusCheckBox = itemView.findViewById(R.id.taskCheckBox);
        contentText = itemView.findViewById(R.id.taskContent);
        dateText = itemView.findViewById(R.id.dateText);
    }

    public CheckBox getStatusCheckBox() {
        return statusCheckBox;
    }

    public TextView getContentText() {
        return contentText;
    }

    public TextView getDateText() {
        return dateText;
    }
}
