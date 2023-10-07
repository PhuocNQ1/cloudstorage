package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.note_service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    private static int idTemp = 0;

    @PostMapping("/add")
    public String addNote(@ModelAttribute NoteModel note) {
        note.setNoteId(idTemp);
        noteService.add(note);
        
        // emulator id generator
        idTemp++;
        return "redirect:/home";
    }
}
