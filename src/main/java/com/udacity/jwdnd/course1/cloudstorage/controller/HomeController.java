package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.credential_service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.file_service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.note_service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private CredentialService credentialService;

    @GetMapping("home")
    public String homePage(Model model) {

        int userId = userService.getCurrentUserId();

        model.addAttribute("fileList", fileService.getAllByUserId(userId));
        model.addAttribute("noteList", noteService.getAllByUserId(userId));
        model.addAttribute("credentialList", credentialService.getAllByUserId(userId));


        model.addAttribute("fileErr", fileService.getMsgError());
        model.addAttribute("noteErr", noteService.getMsgError());
        model.addAttribute("creErr", credentialService.getMsgError());

        model.addAttribute("noteModel", new NoteModel());
        model.addAttribute("creModel", new CredentialModel());

        return "home";
    }
}
