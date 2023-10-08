package com.udacity.jwdnd.course1.cloudstorage.model;

public class NoteModel {

    private int noteId;
    private String notetitle;
    private String notedescription;
    private int userId;

    public NoteModel() {
    }

    public NoteModel(int noteId, String notetitle, String notedescription) {
        this.noteId = noteId;
        this.notetitle = notetitle;
        this.notedescription = notedescription;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
