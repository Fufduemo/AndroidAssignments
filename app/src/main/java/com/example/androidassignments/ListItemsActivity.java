package com.example.androidassignments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i("ListItemsActivity", "In onCreate()");

        //Onclick listener for imagebutton
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (picIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(picIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        //Onchanged listener for switch button
        Switch switchButton = findViewById(R.id.switch1);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setOncheckedChanged(switchButton);
            }
        });
        //Checkbox listener
        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                builder.setMessage(R.string.dialogue_message) //Add a dialog message to strings.xml
                        .setTitle(R.string.dialogue_title)
                        .setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
                                Intent checkIntent = new Intent();
                                checkIntent.putExtra("Response", getResources().getString(R.string.List_response));
                                setResult(Activity.RESULT_OK, checkIntent);
                                finish();                            }
                        })
                        .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.dismiss();
                            }
                        })
                        .show();            }

        });

    }

    private void setOncheckedChanged(Switch switchButton) {
        String text;
        int duration;

        if (switchButton.isChecked()){
            text = getResources().getString(R.string.switch_on);
            duration = Toast.LENGTH_SHORT;
        }else{
            text = getResources().getString(R.string.switch_off);
            duration = Toast.LENGTH_LONG;
        }
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }


    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && responseCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton btnImg = findViewById(R.id.imageButton);
            btnImg.setImageBitmap(imageBitmap);
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("ListItemsActivity", "In onStart()");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("ListItemsActivity", "In onResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("ListItemsActivity", "In onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("ListItemsActivity", "In onStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("ListItemsActivity", "In onDestroy()");
    }
}