package com.example.week4_code_challenge_4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week4_code_challenge_4.adapter.StudentAdapter;
import com.example.week4_code_challenge_4.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the Data Source
        List<Student> students = getStudents();

        //how the list will be displayes
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //create the adapter
        StudentAdapter adapter = new StudentAdapter(students);

        //get the recyclerViewElement from the layout
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        //connect all the elements together
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * @return a List of Data Source
     */
    private List<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("1", "1"));
        students.add(new Student("2", "2"));
        students.add(new Student("3", "3"));
        students.add(new Student("4", "4"));
        students.add(new Student("5", "5"));
        students.add(new Student("6", "6"));
        students.add(new Student("7", "7"));
        students.add(new Student("8", "8"));
        students.add(new Student("9", "9"));
        students.add(new Student("10", "10"));
        return students;
    }

}