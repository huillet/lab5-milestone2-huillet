package com.example.lab5_milestone2_huillet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";
        setContentView(R.layout.display_activity);

        // Fetch username
        TextView welcomeText = findViewById(R.id.welcomeText);
        Intent intent = getIntent();
        sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE);
        String usernameStr = sharedPreferences.getString(usernameKey, "");
        welcomeText.setText("Welcome " + usernameStr + "!");

        // Get SQLiteDatabase instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",
                Context.MODE_PRIVATE, null);

        // Initialize "notes" using readNotes method implemented in DBHelper class. Use the username you
        //      got from SharedPreferences as param for the readNotes method
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        notes = dbHelper.readNotes(usernameStr);

        // Create an ArrayList<String> object by iterating over notes object.
        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note : notes) {
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        // Use ListView view to display notes on screen
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.notesListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("noteID", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.display_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.logout:
                sharedPreferences.edit().remove("username").apply();
                goToMainActivity();
                break;
            case R.id.add:
                goToEditActivity();
                return true;
        }
        return false;
    }

    public void goToMainActivity()
    {
        Intent intent = new Intent(this, com.example.lab5_milestone2_huillet.MainActivity.class);
        startActivity(intent);
    }

    public void goToEditActivity()
    {
        Intent intent = new Intent(this, com.example.lab5_milestone2_huillet.EditActivity.class);
        startActivity(intent);
    }
}