package com.hai811i.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView tvName, tvRoute, tvDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        tvName = findViewById(R.id.tvName);
        tvRoute = findViewById(R.id.tvRoute);
        tvDateTime = findViewById(R.id.tvDateTime);


        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String source = intent.getStringExtra("source");
        String destination = intent.getStringExtra("destination");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        // Display the data
        tvName.setText("Name: " + firstName + " " + lastName);
        tvRoute.setText("From " + source + " to " + destination);
        tvDateTime.setText("Date: " + date + " | Time: " + time);
    }
}