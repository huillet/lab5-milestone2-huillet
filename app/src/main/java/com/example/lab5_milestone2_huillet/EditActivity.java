package com.example.lab5_milestone2_huillet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private int noteID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        // Get EditText view
        EditText titleText = findViewById(R.id.titleText);
        EditText editText = findViewById(R.id.editText);
        // Get Intent
        Intent intent = getIntent();
        // Get the value of integer "noteid" from intent
        // Initialize class variable "noteid" with the value from intent
        noteID = intent.getIntExtra("noteID", -1);

        if (noteID != -1) {
            Note note = DisplayActivity.notes.get(noteID);
            String noteTitle = note.getTitle();
            String noteContent = note.getContent();
            titleText.setText(noteTitle);
            editText.setText(noteContent);
        }
    }

    public void goToMainActivity()
    {
        Intent intent = new Intent(this, com.example.lab5_milestone2_huillet.MainActivity.class);
        startActivity(intent);
    }

    public void onSave(View view)
    {
        // Get editText view and the content that user entered
        EditText titleText = findViewById(R.id.titleText);
        EditText editText = findViewById(R.id.editText);
        String textTitle = titleText.getText().toString();
        String textContent = editText.getText().toString();

        // initialize SQLiteDatabase instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",
                Context.MODE_PRIVATE, null);

        // initialize DBHelper class
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // Set username in the following variable by fetching it from SharedPreferences
        sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE);
        String usernameKey = "username";
        String usernameStr = sharedPreferences.getString(usernameKey, "");

        // Save information to the database
        Date currTime = Calendar.getInstance().getTime();
        if (noteID == -1) {
            dbHelper.saveNotes(usernameStr, " " + textTitle, textContent, " " + currTime.toString());
        }
        else
        {
            dbHelper.updateNote(usernameStr, " " + textTitle, textContent, " " + currTime.toString());
        }

        // Go to display activity using intents
        goToDisplayActivity();
    }

    public void goToDisplayActivity()
    {
        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);
    }
}