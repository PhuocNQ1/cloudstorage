package com.udacity.jwdnd.course1.cloudstorage.exception;

public class NoteException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NoteException(String message) {
        super(message);
    }
}
