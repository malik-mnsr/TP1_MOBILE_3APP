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
    private int selectedPosition = -1;

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


        String time = trainTimes.get(position);


        TextView tvTime = convertView.findViewById(R.id.tvTime);
        CardView cardViewTime = convertView.findViewById(R.id.cardViewTime);

        tvTime.setText(time);


        if (position == selectedPosition) {
            cardViewTime.setCardBackgroundColor(Color.parseColor("#DC6A72"));
        } else {
            cardViewTime.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }


        cardViewTime.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
        });

        return convertView;
    }


    public String getSelectedTime() {
        if (selectedPosition != -1) {
            return trainTimes.get(selectedPosition);
        }
        return null;
    }
}