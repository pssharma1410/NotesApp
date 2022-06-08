package com.example.notes;

import java.io.Serializable;

public class detailsOfNotes implements Serializable {
    String previewOfNotes;
    String fullNotes;
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPreviewOfNotes() {
        return previewOfNotes;
    }

    public void setPreviewOfNotes(String previewOfNotes) {
        this.previewOfNotes = previewOfNotes;
    }

    public String getFullNotes() {
        return fullNotes;
    }

    public void setFullNotes(String fullNotes) {
        this.fullNotes = fullNotes;
    }

}
