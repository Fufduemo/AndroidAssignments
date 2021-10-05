package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    protected static final String EMAIL_KEY="someEmailKey";
    protected static final String PREFERENCE_KEY = "somePreferenceKey";
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i("LoginActivity", "In onCreate()");
        mPrefs = getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
        String new_email_value = mPrefs.getString(EMAIL_KEY,"");
        ((EditText) findViewById(R.id.editTextlogin)).setText(new_email_value);

    }
    private void saveUserData(){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.clear();
        String new_email_entered = (String) ((EditText) findViewById(R.id.editTextlogin)).getText().toString();
        mEditor.putString(EMAIL_KEY, new_email_entered);

        mEditor.commit();
    }
    public void onSaveClicked(View view){
        saveUserData();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("LoginActivity", "In onStart()");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("LoginActivity", "In onResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("LoginActivity", "In onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("LoginActivity", "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("LoginActivity", "In onDestroy()");
    }

}