package com.example.lab5_milestone2_huillet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        setContentView(R.layout.display_activity);

        TextView welcomeText = findViewById(R.id.welcomeText);
        Intent intent = getIntent();
        //String str = intent.getStringExtra("message");
        sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE);
        String str = sharedPreferences.getString(usernameKey, "");
        welcomeText.setText("Welcome " + str + "!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.display_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                sharedPreferences.edit().remove("username").apply();
                goToMainActivity();
                break;
            case R.id.add:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToMainActivity()
    {
        Intent intent = new Intent(this, com.example.lab5_milestone2_huillet.MainActivity.class);
        startActivity(intent);
    }
}