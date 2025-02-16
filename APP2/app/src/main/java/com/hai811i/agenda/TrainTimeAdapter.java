package com.hai811i.agenda;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import java.util.List;
public class TrainTimeAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> trainTimes;
    private int selectedPosition = -1; // Track the selected position

    public TrainTimeAdapter(@NonNull Context context, List<String> trainTimes) {
        super(context, R.layout.item_train_time, trainTimes);
        this.context = context;
        this.trainTimes = trainTimes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_train_time, parent, false);
        }

        // Get the time for this position
        String time = trainTimes.get(position);

        // Find views in the item layout
        TextView tvTime = convertView.findViewById(R.id.tvTime);
        CardView cardViewTime = convertView.findViewById(R.id.cardViewTime);

        // Set the time text
        tvTime.setText(time);

        // Change the background color if this item is selected
        if (position == selectedPosition) {
            cardViewTime.setCardBackgroundColor(Color.parseColor("#DC6A72")); // Selected color
        } else {
            cardViewTime.setCardBackgroundColor(Color.parseColor("#FFFFFF")); // Default color
        }

        // Handle item click
        cardViewTime.setOnClickListener(v -> {
            selectedPosition = position; // Update the selected position
            notifyDataSetChanged(); // Refresh the list
        });

        return convertView;
    }

    // Method to get the selected time
    public String getSelectedTime() {
        if (selectedPosition != -1) {
            return trainTimes.get(selectedPosition);
        }
        return null;
    }
}