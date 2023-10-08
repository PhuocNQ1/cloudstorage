package com.udacity.jwdnd.course1.cloudstorage.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.repository.generic_repository.T_Repository;

@Repository
public class NoteRepository extends T_Repository<NoteModel> {

    private List<NoteModel> listModel = new ArrayList<>();

    @Override
    public List<NoteModel> getAll() {
        return listModel;
    }

    @Override
    public NoteModel get(int id) {
        return listModel.get(id);
    }

    @Override
    public void add(NoteModel t) {
        listModel.add(t);

    }

    @Override
    public void update(NoteModel t) {
        listModel = listModel.stream().map(model -> {
            if (model.getNoteId() == t.getNoteId()) {
                model.setNotedescription(t.getNotedescription());
                model.setNotetitle(t.getNotetitle());
            }
            return model;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteByID(int id) {
       NoteModel note = listModel.stream().filter(i->i.getNoteId()==id).findFirst().orElse(null);
        listModel.remove(note);

    }

    @Override
    public void delete(NoteModel t) {
        listModel.remove(t);

    }

}
