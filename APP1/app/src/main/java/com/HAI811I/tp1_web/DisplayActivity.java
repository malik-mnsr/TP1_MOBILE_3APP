package com.HAI811I.tp1_web;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        Intent intent = getIntent();
        String nom = intent.getStringExtra("NOM");
        String prenom = intent.getStringExtra("PRENOM");
        String age = intent.getStringExtra("AGE");
        String domaine = intent.getStringExtra("DOMAINE");
        String telephone = intent.getStringExtra("TELEPHONE");


        TextView textViewData = findViewById(R.id.textViewData);


        SpannableString data = new SpannableString(
                getString(R.string.nom) + ": " + nom + "\n" +
                        getString(R.string.prenom) + ": " + prenom + "\n" +
                        getString(R.string.age) + ": " + age + "\n" +
                        getString(R.string.domaine) + ": " + domaine + "\n" +
                        getString(R.string.telephone) + ": " + telephone
        );


        int nomStart = getString(R.string.nom).length() + 2;
        data.setSpan(new StyleSpan(Typeface.BOLD), nomStart, nomStart + nom.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int prenomStart = nomStart + nom.length() + getString(R.string.prenom).length() + 3;
        data.setSpan(new StyleSpan(Typeface.BOLD), prenomStart, prenomStart + prenom.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int ageStart = prenomStart + prenom.length() + getString(R.string.age).length() + 3;
        data.setSpan(new StyleSpan(Typeface.BOLD), ageStart, ageStart + age.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int domaineStart = ageStart + age.length() + getString(R.string.domaine).length() + 3;
        data.setSpan(new StyleSpan(Typeface.BOLD), domaineStart, domaineStart + domaine.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int telephoneStart = domaineStart + domaine.length() + getString(R.string.telephone).length() + 3;
        data.setSpan(new StyleSpan(Typeface.BOLD), telephoneStart, telephoneStart + telephone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textViewData.setText(data);


        Button buttonOk = findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(v -> {
            Intent thirdIntent = new Intent(DisplayActivity.this, ThirdActivity.class);
            thirdIntent.putExtra("TELEPHONE", telephone);
            startActivity(thirdIntent);
        });


        Button buttonRetour = findViewById(R.id.buttonRetour);
        buttonRetour.setOnClickListener(v -> finish());
    }
}
