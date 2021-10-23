package com.example.lab5_milestone2_huillet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_activity);
    }

    public void goToMainActivity()
    {
        Intent intent = new Intent(this, com.example.lab5_milestone2_huillet.MainActivity.class);
        startActivity(intent);
    }
}