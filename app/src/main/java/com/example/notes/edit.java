package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class edit extends AppCompatActivity {
    EditText previewtext;
    EditText notes;
    public void saveChanges(View view) throws IOException {
        if(previewtext.getText().toString().equals("")) {
            Toast.makeText(this, "Preview Cannot be Empty !!! ", Toast.LENGTH_SHORT).show();
            previewtext.setError("Preview cannot be empty !!");
            if(notes.getText().toString().equals("")) {
                Toast.makeText(this, "You didn't added any notes !!!", Toast.LENGTH_SHORT).show();
                notes.setError("Notes cannot be empty !!");
                return;
            }
        }
        if(notes.getText().toString().equals("")) {
            Toast.makeText(this, "You didn't added any notes !!!", Toast.LENGTH_SHORT).show();
            notes.setError("Notes cannot be empty !!");
            return;
        }
        detailsOfNotes temp = new detailsOfNotes();
        temp.setPreviewOfNotes(previewtext.getText().toString());
        temp.setFullNotes(notes.getText().toString());
        temp.setDate(MainActivity.preview.get(MainActivity.currClicked).date);
        MainActivity.preview.set(MainActivity.currClicked,temp);
        MainActivity.sp.edit().putString("list" ,ObjectSerializer.serialize(MainActivity.preview)).apply();
        MainActivity.listv.setAdapter(MainActivity.listAdapter);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        notes = (EditText) findViewById(R.id.editTextTextMultiLine);
        previewtext = (EditText) findViewById(R.id.editTextTextPersonName);
        previewtext.setText(MainActivity.currClickedNotes.getPreviewOfNotes());
        notes.setText(MainActivity.currClickedNotes.getFullNotes());
    }
}