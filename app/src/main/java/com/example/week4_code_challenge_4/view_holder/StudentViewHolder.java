package com.example.week4_code_challenge_4.view_holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week4_code_challenge_4.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    private final TextView firstNameText;
    private final TextView lastNameText;

    /**
     * itemView este fisierul de layaout pentru ITEM(student_row.xml)
     *
     * @param itemView
     */
    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        lastNameText = itemView.findViewById(R.id.firstName);
        firstNameText = itemView.findViewById(R.id.lastName);
    }

    public TextView getFirstNameText() {
        return firstNameText;
    }

    public TextView getLastNameText() {
        return lastNameText;
    }
}
