package com.example.week3_code_challenge_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_spinner);

        //Our Data Source
        List<String> data = getSweets();

        //Our Adaptor - converts the Data Source in Views
        ArrayAdapter<String> sweetsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);

        Spinner spinner = findViewById(R.id.spinner);

        //The arrow between Adapter and Spinner
        spinner.setAdapter(sweetsAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = sweetsAdapter.getItem(position);
                Toast.makeText(SpinnerActivity.this, "Selected: "+selectedItem, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * Creates the Data Source for the Spinner
     */
    private List<String> getSweets() {
        List<String> sweets = new ArrayList<>();
        sweets.add("Cupcake");
        sweets.add("Donut");
        sweets.add("Eclair");
        sweets.add("KitKat");
        sweets.add("Pie");
        return sweets;
    }
}