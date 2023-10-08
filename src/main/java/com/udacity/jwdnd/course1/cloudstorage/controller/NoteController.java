package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exception.NoteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.note_service.NoteService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/add")
    public String addNote(@ModelAttribute NoteModel note) {
        try {
            NoteModel noteModel = noteService.get(note.getNoteId());
            if(noteModel != null){
                noteService.update(note);
                noteService.setMsgError(null);
            }else {
                noteService.add(note);
                noteService.setMsgError(null);
            }
        } catch (NoteException e) {
            noteService.setMsgError(e.getMessage());
        } catch (Exception e) {
            noteService.setMsgError("Error: An unexpected error has occurred during the insertion process.");
        }
        
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable String id) {
        noteService.deleteByID(Integer.parseInt(id));
        return "redirect:/home";
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<NoteModel> getNote(@PathVariable String id) {
       NoteModel noteModel = noteService.get(Integer.parseInt(id));
        return new ResponseEntity<>(noteModel,HttpStatus.OK);
    }

}
