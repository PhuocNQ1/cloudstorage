package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.file_service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.note_service.NoteService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private FileService fileService;

    @Autowired
    private NoteService noteService;

//    @Autowired
//    private FileService fileService;

    @GetMapping("home")
    public String homePage(Model model) {
        model.addAttribute("fileList", fileService.getAll());
        model.addAttribute("noteList", noteService.getAll());
        model.addAttribute("noteModel", new NoteModel());
        model.addAttribute("fileError", fileService.getMsgError());
        return "home";
    }
}
