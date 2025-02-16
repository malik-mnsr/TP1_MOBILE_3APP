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

    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule);


        calendarLayout = findViewById(R.id.calendarLayout);
        trainTimesList = findViewById(R.id.trainTimesList);
        tvReservationMessage = findViewById(R.id.tvReservationMessage);
        btnMakeReservation = findViewById(R.id.btnMakeReservation);


        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String source = intent.getStringExtra("source");
        String destination = intent.getStringExtra("destination");


        String reservationMessage = "Train from " + source + " to " + destination + " - Reservation";
        tvReservationMessage.setText(reservationMessage);


        setTitle(firstName + " " + lastName + "'s Journey");


        populateCalendar();


        populateTrainTimes(source, destination);

        btnMakeReservation.setOnClickListener(v -> {
            if (selectedDate.isEmpty() || selectedTime == null || selectedTime.isEmpty()) {
                Toast.makeText(this, "Please select a date and time", Toast.LENGTH_SHORT).show();
            } else {



                Intent confirmationIntent = new Intent(this, ConfirmationActivity.class);


                confirmationIntent.putExtra("firstName", firstName);
                confirmationIntent.putExtra("lastName", lastName);
                confirmationIntent.putExtra("source", source);
                confirmationIntent.putExtra("destination", destination);
                confirmationIntent.putExtra("date", selectedDate);
                confirmationIntent.putExtra("time", selectedTime);


                startActivity(confirmationIntent);
            }
        });
    }
    private void populateTrainTimes(String source, String destination) {

        List<String> trainTimes = new ArrayList<>();
        trainTimes.add("08:00 AM");
        trainTimes.add("10:00 AM");
        trainTimes.add("12:00 PM");
        trainTimes.add("02:00 PM");
        trainTimes.add("04:00 PM");


        TrainTimeAdapter adapter = new TrainTimeAdapter(this, trainTimes);


        trainTimesList.setAdapter(adapter);


        trainTimesList.setOnItemClickListener((parent, view, position, id) -> {
            selectedTime = adapter.getSelectedTime();
            Toast.makeText(this, "Selected Time: " + selectedTime, Toast.LENGTH_SHORT).show(); // Debugging
        });
    }

    private void populateCalendar() {

        Calendar calendar = Calendar.getInstance();


        for (int i = 0; i < 7; i++) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d", Locale.getDefault());
            String day = dateFormat.format(calendar.getTime());


            CardView dayBox = new CardView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(8, 0, 8, 0);
            dayBox.setLayoutParams(layoutParams);
            dayBox.setCardBackgroundColor(getResources().getColor(android.R.color.white));
            dayBox.setRadius(16);
            dayBox.setCardElevation(4);


            TextView dayView = new TextView(this);
            dayView.setText(day);
            dayView.setPadding(32, 16, 32, 16);
            dayView.setTextSize(16);
            dayView.setGravity(Gravity.CENTER);


            dayBox.addView(dayView);


            calendarLayout.addView(dayBox);


            dayBox.setOnClickListener(v -> {

                for (int j = 0; j < calendarLayout.getChildCount(); j++) {
                    View child = calendarLayout.getChildAt(j);
                    if (child instanceof CardView) {
                        ((CardView) child).setCardBackgroundColor(getResources().getColor(android.R.color.white));
                    }
                }


                dayBox.setCardBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));


                selectedDate = day;
                Toast.makeText(this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show(); // Debugging
            });


            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
}