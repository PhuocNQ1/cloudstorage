package com.udacity.jwdnd.course1.cloudstorage.services.note_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.repository.NoteRepository;
import com.udacity.jwdnd.course1.cloudstorage.services.generic_service.T_Service;

@Service
public class NoteService extends T_Service<NoteModel> {
    
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<NoteModel> getAll() {
        return noteRepository.getAll();
    }

    @Override
    public NoteModel get(int id) {
        return noteRepository.get(id);
    }

    @Override
    public void add(NoteModel t) {
        noteRepository.add(t);
    }

    @Override
    public void update(NoteModel t) {
        noteRepository.update(t);
    }

    @Override
    public void deleteByID(int id) {
        noteRepository.deleteByID(id);
    }

    @Override
    public void delete(NoteModel t) {
        noteRepository.delete(t);
    }

}
