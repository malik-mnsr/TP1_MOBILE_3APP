package com.HAI811I.tp1_web;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LanguageAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> languages;
    private final int[] flagImages;

    public LanguageAdapter(@NonNull Context context, List<String> languages, int[] flagImages) {
        super(context, R.layout.spinner_item, languages);
        this.context = context;
        this.languages = languages;
        this.flagImages = flagImages;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.spinner_item, parent, false);
        }


        ImageView flagImageView = convertView.findViewById(R.id.flagImageView);
        TextView languageTextView = convertView.findViewById(R.id.languageTextView);

        flagImageView.setImageResource(flagImages[position]);
        languageTextView.setText(languages.get(position));

        return convertView;
    }
}