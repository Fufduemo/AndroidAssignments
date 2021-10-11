package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    Button button;
    EditText editText;
    ListView listView;
    ArrayList<String> sentMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        editText = findViewById(R.id.userMessage);
        button = findViewById(R.id.sendButton);
        listView = findViewById(R.id.list);
        ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editText.getText().toString();
                sentMessages.add(editText.getText().toString());
                messageAdapter.notifyDataSetChanged();
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

}








