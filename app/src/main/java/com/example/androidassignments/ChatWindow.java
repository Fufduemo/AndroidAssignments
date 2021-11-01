package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ChatWindow";
    static final String GET_MESSAGES = "SELECT KEY_MESSAGE FROM MESSAGES";
    protected SQLiteDatabase database;
    Button button;
    EditText editText;
    ListView listView;
    ArrayList<String> sentMessages = new ArrayList<>();

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        editText = findViewById(R.id.userMessage);
        button = findViewById(R.id.sendButton);
        listView = findViewById(R.id.list);
        ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        ChatDataBaseHelper dbHelper = new ChatDataBaseHelper(this);
        database = dbHelper.getWritableDatabase();

        final Cursor cursor = database.rawQuery(GET_MESSAGES,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(ChatDataBaseHelper.KEY_MESSAGE) ) );
            sentMessages.add(cursor.getString(cursor.getColumnIndex(ChatDataBaseHelper.KEY_MESSAGE)));
            cursor.moveToNext();
        }
        Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + cursor.getColumnCount() );
        for (int i = 0; i <cursor.getColumnCount();i++){
            Log.i(ACTIVITY_NAME, "Column Name: "+ cursor.getColumnName(i));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editText.getText().toString();
                sentMessages.add(editText.getText().toString());
                messageAdapter.notifyDataSetChanged();
                ContentValues values = new ContentValues();
                values.put(ChatDataBaseHelper.KEY_MESSAGE, editText.getText().toString());
                database.insert(ChatDataBaseHelper.TABLE_NAME, "NullPlaceHolder",values);
                messageAdapter.notifyDataSetChanged(); //this will restart the process of getCount()and getView()
                editText.setText("");


            }
        });
        }
    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }
        public int getCount() {
            return sentMessages.size();
        }
        public String getItem(int position){
            return sentMessages.get(position);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater= ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position%2==0)
                result = inflater.inflate(R.layout.chat_row_incoming,null);
            else
                result= inflater.inflate(R.layout.chat_row_outgoing,null);
            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        database.close();
        Log.i(ACTIVITY_NAME,"In onDestroy()");
    }

}








