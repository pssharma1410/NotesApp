package com.example.notes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class listViewAdapter extends ArrayAdapter<detailsOfNotes> {
    ArrayList<detailsOfNotes> prevw;
    Context context;
    public listViewAdapter(Context context, ArrayList<detailsOfNotes> prevw) {
        super(context, R.layout.list_row,prevw);
        this.context = context;
        this.prevw = prevw;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row,null);
            TextView preview = (TextView) convertView.findViewById(R.id.textView);
            TextView date = (TextView) convertView.findViewById(R.id.textView2);
            date.setText(getItem(position).getDate());
            preview.setText(getItem(position).getPreviewOfNotes());
        }
        return convertView;
    }
}
