package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "In onCreate()");
        Button sButton = findViewById(R.id.startchat);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_chat_window);
                Log.i("MainActivity","User clicked Start Chat");
                Intent cIntent = new Intent(MainActivity.this, ChatWindow.class);
                startActivity(cIntent);
            }
        });
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(intent,10);
            }

        });
    }
    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (requestCode == 10){
            Log.i("MainActivity", "Returned to MainActivity.OnActivityResult");
        }
        if (responseCode == Activity.RESULT_OK){
            String msg = data.getStringExtra("Response");
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, msg , duration); //this is the ListActivity
            toast.show(); //display your message box
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("MainActivity", "In onStart()");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("MainActivity", "In onResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("MainActivity", "In onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("MainActivity", "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("MainActivity", "In onDestroy()");
    }
}