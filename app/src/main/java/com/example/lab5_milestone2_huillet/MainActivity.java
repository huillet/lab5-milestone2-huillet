package com.example.lab5_milestone2_huillet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE);
        if (!sharedPreferences.getString(usernameKey, "").equals("")){
            String str = sharedPreferences.getString(usernameKey, "");
            goToDisplayActivity();
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    public void onSubmit(View view)
    {
        EditText nameField = (EditText) findViewById(R.id.nameField);
        String str = nameField.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str).apply();

        //Toast.makeText(MainActivity.this, myTextField.getText().toString(), Toast.LENGTH_LONG.show());
        goToDisplayActivity();
    }

    public void goToDisplayActivity()
    {
        Intent intent = new Intent(this, DisplayActivity.class);
        // intent.putExtra("message",s);
        startActivity(intent);
    }
}