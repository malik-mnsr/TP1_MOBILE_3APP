package com.hai811i.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
public class TrainScheduleActivity extends AppCompatActivity {

    private LinearLayout calendarLayout;
    private ListView trainTimesList;
    private TextView tvReservationMessage;
    private Button btnMakeReservation;

    private String selectedDate = ""; // Track the selected date
    private String selectedTime = ""; // Track the selected time

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule);

        // Initialize views
        calendarLayout = findViewById(R.id.calendarLayout);
        trainTimesList = findViewById(R.id.trainTimesList);
        tvReservationMessage = findViewById(R.id.tvReservationMessage);
        btnMakeReservation = findViewById(R.id.btnMakeReservation);

        // Retrieve data from the intent
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String source = intent.getStringExtra("source");
        String destination = intent.getStringExtra("destination");

        // Display the reservation message
        String reservationMessage = "Train from " + source + " to " + destination + " - Reservation";
        tvReservationMessage.setText(reservationMessage);

        // Display the user's name and journey details in the title
        setTitle(firstName + " " + lastName + "'s Journey");

        // Populate the horizontal calendar
        populateCalendar();

        // Populate the train times list
        populateTrainTimes(source, destination);

        btnMakeReservation.setOnClickListener(v -> {
            if (selectedDate.isEmpty() || selectedTime == null || selectedTime.isEmpty()) {
                Toast.makeText(this, "Please select a date and time", Toast.LENGTH_SHORT).show();
            } else {


                // Create an intent to start the ConfirmationActivity
                Intent confirmationIntent = new Intent(this, ConfirmationActivity.class);

                // Pass data to the ConfirmationActivity
                confirmationIntent.putExtra("firstName", firstName);
                confirmationIntent.putExtra("lastName", lastName);
                confirmationIntent.putExtra("source", source);
                confirmationIntent.putExtra("destination", destination);
                confirmationIntent.putExtra("date", selectedDate);
                confirmationIntent.putExtra("time", selectedTime);

                // Start the ConfirmationActivity
                startActivity(confirmationIntent);
            }
        });
    }
    private void populateTrainTimes(String source, String destination) {
        // Example: Simulate train times based on source and destination
        List<String> trainTimes = new ArrayList<>();
        trainTimes.add("08:00 AM");
        trainTimes.add("10:00 AM");
        trainTimes.add("12:00 PM");
        trainTimes.add("02:00 PM");
        trainTimes.add("04:00 PM");

        // Initialize the custom adapter
        TrainTimeAdapter adapter = new TrainTimeAdapter(this, trainTimes);

        // Set the adapter to the ListView
        trainTimesList.setAdapter(adapter);

        // Handle item selection
        trainTimesList.setOnItemClickListener((parent, view, position, id) -> {
            selectedTime = adapter.getSelectedTime();
            Toast.makeText(this, "Selected Time: " + selectedTime, Toast.LENGTH_SHORT).show(); // Debugging
        });
    }

    private void populateCalendar() {
        // Get today's date
        Calendar calendar = Calendar.getInstance();

        // Add 7 days to the calendar (today + next 6 days)
        for (int i = 0; i < 7; i++) {
            // Format the date (e.g., "Mon, Oct 23")
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d", Locale.getDefault());
            String day = dateFormat.format(calendar.getTime());

            // Create a CardView for the day box
            CardView dayBox = new CardView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(8, 0, 8, 0); // Add margins between boxes
            dayBox.setLayoutParams(layoutParams);
            dayBox.setCardBackgroundColor(getResources().getColor(android.R.color.white)); // Default background color
            dayBox.setRadius(16); // Rounded corners
            dayBox.setCardElevation(4); // Add shadow

            // Create a TextView for the day
            TextView dayView = new TextView(this);
            dayView.setText(day);
            dayView.setPadding(32, 16, 32, 16); // Add padding inside the box
            dayView.setTextSize(16);
            dayView.setGravity(Gravity.CENTER);

            // Add the TextView to the CardView
            dayBox.addView(dayView);

            // Add the CardView to the horizontal calendar layout
            calendarLayout.addView(dayBox);

            // Set an OnClickListener to change the background color when selected
            dayBox.setOnClickListener(v -> {
                // Reset the background color of all boxes
                for (int j = 0; j < calendarLayout.getChildCount(); j++) {
                    View child = calendarLayout.getChildAt(j);
                    if (child instanceof CardView) {
                        ((CardView) child).setCardBackgroundColor(getResources().getColor(android.R.color.white));
                    }
                }

                // Change the background color of the selected box
                dayBox.setCardBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

                // Save the selected date
                selectedDate = day;
                Toast.makeText(this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show(); // Debugging
            });

            // Move to the next day
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
}