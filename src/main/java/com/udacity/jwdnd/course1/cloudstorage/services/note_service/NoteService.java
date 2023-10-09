package com.udacity.jwdnd.course1.cloudstorage.services.note_service;

import com.udacity.jwdnd.course1.cloudstorage.exception.NoteException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.generic_service.T_Service;
import com.udacity.jwdnd.course1.cloudstorage.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService extends T_Service<NoteModel> {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private UserService userService;

    private String msgError;

    /**
     * Retrieves a list of all note records from the database.
     * @return
     */
    @Override
    public List<NoteModel> getAll() {
        return noteMapper.getAll();
    }

    /**
     * Retrieves a note record from the database based on the provided ID.
     * @param id
     * @return
     */
    @Override
    public NoteModel get(int id) {
        return noteMapper.get(id);
    }

    /**
     * Adds a new note record to the database with the provided NoteModel.
     * @param t
     */
    @Override
    public void add(NoteModel t) {
        if (isDuplicateTitle(this.getAll(), t)) {
            throw new NoteException("Error: Title Already Exists, Please try again!");
        }
        t.setUserId(userService.getCurrentUserId());
        noteMapper.add(t);
    }

    /**
     * Updates a file record in the database with the provided NoteModel.
     * @param t
     */
    @Override
    public void update(NoteModel t) {
        t.setUserId(userService.getCurrentUserId());
        noteMapper.update(t);
    }

    /**
     * Deletes a record from the database based on the provided ID.
     * @param id
     */
    @Override
    public void deleteByID(int id) {
        noteMapper.deleteByID(id);
    }

    /**
     * Retrieves a list of note records associated with a specific user ID from the database.
     * @param id
     * @return
     */
    @Override
    public List<NoteModel> getAllByUserId(int id) {
        return noteMapper.getAllByUserId(id);
    }

    /**
     * Checks if a given title already exists within a list of NoteModel objects.
     * @param list
     * @param noteModel
     * @return
     */
    private boolean isDuplicateTitle(List<NoteModel> list, NoteModel noteModel) {
        return list.stream().anyMatch(i -> i.getNotetitle().equals(noteModel.getNotetitle()));
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }
}
