
package com.example.notes;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Arraylist will be of a class which stores notes and whole preview
    //make different activity for on click for listView
    static ArrayList<detailsOfNotes> preview;
    static SharedPreferences sp;
    static ListView listv;
    static  listViewAdapter listAdapter;
    static detailsOfNotes currClickedNotes;
    static int currClicked;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.addNote){
            Intent intent = new Intent(MainActivity.this, addNotes.class);
            startActivity(intent);
            Log.i("main Activity", ""+preview.size());
            return true;
        }else if(item.getItemId() == R.id.info){
            Intent intent = new Intent(MainActivity.this, Info.class);
            startActivity(intent);
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        listv = (ListView) findViewById(R.id.list);
        preview = new ArrayList<>();
        sp = this.getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        try {
            preview = (ArrayList<detailsOfNotes>)ObjectSerializer.deserialize(sp.getString("list",ObjectSerializer.serialize(new ArrayList<detailsOfNotes>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if(preview == null)
//            preview = new ArrayList<>();
        listAdapter = new listViewAdapter(this, preview);
        listv.setAdapter(listAdapter);
        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currClicked = position;
                currClickedNotes = preview.get(position);
                Intent intent  = new Intent(MainActivity.this, edit.class);
                startActivity(intent);
            }
        });
        listv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_menu_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this note ? ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                preview.remove(position);
                                try {
                                    MainActivity.sp.edit().putString("list" ,ObjectSerializer.serialize(preview)).apply();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                listv.setAdapter(listAdapter);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).show();
                return true;
            }
        });
        //add Info button also in menu
    }
}