package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.credential_service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.file_service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.note_service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.user_service.UserService;

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

    /**
     * Main controller, receiver data of 3 model and display them
     * 
     * @param model
     * @return
     */
    @GetMapping("home")
    public String homePage(Model model) {
        // Get current userId
        int userId = userService.getCurrentUserId();

        // Load and add user-specific file, note, and credential lists to the model.
        model.addAttribute("fileList", fileService.getAllByUserId(userId));
        model.addAttribute("noteList", noteService.getAllByUserId(userId));
        model.addAttribute("credentialList", credentialService.getAllByUserId(userId));

        // Load and set file-error, note-error, and credential-error to the model.
        model.addAttribute("fileNotification", fileService.getFileNotification());
        model.addAttribute("noteNotification", noteService.getMsgNotification());
        model.addAttribute("creNotification", credentialService.getMsgNotification());
        
        // Instance model for form 
        model.addAttribute("noteModel", new NoteModel());
        model.addAttribute("creModel", new CredentialModel());

        return "home";
    }
}
