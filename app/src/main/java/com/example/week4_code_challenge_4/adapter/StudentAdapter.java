package com.example.week4_code_challenge_4.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week4_code_challenge_4.R;
import com.example.week4_code_challenge_4.model.Student;
import com.example.week4_code_challenge_4.view_holder.StudentViewHolder;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    //the Adaptor converts the Data Source, in our case a List of Students
    private final List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    /**
     * @return How many elements we want to display in total
     */
    @Override
    public int getItemCount() {
        return students.size();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //convert a xml into a wiew object by using INFLATE method
        View view = View.inflate(parent.getContext(), R.layout.student_row, null);
        return new StudentViewHolder(view);
    }

    /**
     * populates the ViewHolders with our data
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        //get the element to display
        Student student = students.get(position);
        //populates the elements associated to the ITEM elements with our date
        // by using the help of ViewHolder
        holder.getFirstNameText().setText("FirstName: " + student.getFirstName());
        holder.getLastNameText().setText("LastName: " + student.getLastName());
        if (position % 2 == 0) {
            holder.itemView.findViewById(R.id.item).setBackgroundColor(Color.LTGRAY);
        }
    }

}
