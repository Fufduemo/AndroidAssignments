package com.example.androidassignments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.androidassignments.databinding.ActivityTestToolbarBinding;

public class TestToolbar extends AppCompatActivity {

    Snackbar snackbar;
    String message = "You have selected Item 1";
    private AppBarConfiguration appBarConfiguration;
    private ActivityTestToolbarBinding binding;
    //AppCompatActivity testToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestToolbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_test_toolbar);
        //appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "I love android programming", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
   // @Override
   // public boolean onSupportNavigateUp() {
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_test_toolbar);
        //return NavigationUI.navigateUp(navController, appBarConfiguration)
                //|| super.onSupportNavigateUp();
   // }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem mi){
        int id = mi.getItemId();
        switch(id) {
            case R.id.choice_One:
                Log.d("Toolbar", "Choice One selected");
                Snackbar.make(findViewById(R.id.toolbar), message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.choice_Two:
                Log.d("Toolbar", "Choice Two selected");
                message = "You selected item 2";
                Snackbar.make(findViewById(R.id.toolbar), message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(this);
                builder.setTitle("Do you want to go back?");
                builder.setPositiveButton(R.string.ok,
                        (dialog, id1) -> {
                            // User clicked OK button
                            this.finish();
                        });
                builder.setNegativeButton(R.string.cancel,
                        (dialog, id12) -> {
                            // User cancelled the dialog
                            //do nothing

                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.choice_Three:
                Log.d("Toolbar", "Choice Three selected");
                message = "You selected Item 3";
                Snackbar.make(findViewById(R.id.toolbar), message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                AlertDialog.Builder customDialog =
                        new AlertDialog.Builder(this);
                // Get the layout inflater
                LayoutInflater inflater = this.getLayoutInflater();
                final View view = inflater.inflate(R.layout.dialog_signin, null);
                customDialog.setView(view)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                EditText edit = view.findViewById(R.id.new_message);
                                message = edit.getText().toString();
                                //snackbar.setText(message);
                            }
                        })
                        .setNegativeButton(R.string.cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                dialog = customDialog.create();
                dialog.show();
                break;
            case R.id.about:
                Toast.makeText(this, "Version 1.0, by Femi Ogedengbe", Toast.LENGTH_LONG).show();

        }
        return true;
        }

    }
