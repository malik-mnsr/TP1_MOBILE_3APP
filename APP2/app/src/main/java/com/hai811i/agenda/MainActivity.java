package com.hai811i.agenda;



import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Spinner spnFrom, spnTo;
    private Button btnSearch;
    private EditText etFirstName, etLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        spnFrom = findViewById(R.id.spnFrom);
        spnTo = findViewById(R.id.spnTo);
        btnSearch = findViewById(R.id.btnSearch);

        // Load Spinners with data (same as before)
        ArrayAdapter<CharSequence> sourceAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.source_stations,
                android.R.layout.simple_spinner_item
        );
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFrom.setAdapter(sourceAdapter);

        ArrayAdapter<CharSequence> destinationAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.destination_stations,
                android.R.layout.simple_spinner_item
        );
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTo.setAdapter(destinationAdapter);

        // Handle Search Button Click
        btnSearch.setOnClickListener(v -> {
            // Get user input
            String firstName = etFirstName.getText().toString().trim();
            String lastName = etLastName.getText().toString().trim();
            String source = spnFrom.getSelectedItem().toString();
            String destination = spnTo.getSelectedItem().toString();

            // Validate input
            if (firstName.isEmpty() || lastName.isEmpty() || source.equals("Source") || destination.equals("Destination")) {
                Toast.makeText(this, "Please fill all fields and select valid stations", Toast.LENGTH_SHORT).show();
            } else {
                // Launch the second activity and pass data
                Intent intent = new Intent(MainActivity.this, TrainScheduleActivity.class);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("source", source);
                intent.putExtra("destination", destination);
                startActivity(intent);
            }
        });
    }
}