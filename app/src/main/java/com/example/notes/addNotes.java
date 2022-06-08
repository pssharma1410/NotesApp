package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class addNotes extends AppCompatActivity {
    EditText previewtext;
    EditText notes;
    String fullnotes = "";
    String fullpreview = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        //add condition for empty preview or empty notes
        notes = (EditText) findViewById(R.id.editTextTextMultiLine);
        previewtext = (EditText) findViewById(R.id.editTextTextPersonName);
    }
    public void add(View view) throws IOException {
        //add condition for empty preview or empty notes
        fullnotes = notes.getText().toString();
        fullpreview = previewtext.getText().toString();
        if(fullpreview.equals("")) {
            Toast.makeText(this, "Preview Cannot be Empty !!! ", Toast.LENGTH_SHORT).show();
            previewtext.setError("Preview cannot be empty !!");
            if(fullnotes.equals("")) {
                Toast.makeText(this, "You didn't added any notes !!!", Toast.LENGTH_SHORT).show();
                notes.setError("Notes cannot be empty !!");
                return;
            }
        }
        if(fullnotes.equals("")) {
            Toast.makeText(this, "You didn't added any notes !!!", Toast.LENGTH_SHORT).show();
            notes.setError("Notes cannot be empty !!");
            return;
        }
        detailsOfNotes don = new detailsOfNotes();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy | HH:mm");
        String currdateandtime = sdf.format(new Date());
        don.setPreviewOfNotes(fullpreview);
        don.setFullNotes(fullnotes);
        don.setDate(currdateandtime);
        MainActivity.preview.add(don);
        MainActivity.sp.edit().putString("list",ObjectSerializer.serialize(MainActivity.preview)).apply();
        MainActivity.listAdapter.notifyDataSetChanged();
        finish();
    }
}