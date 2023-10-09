package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exception.CredentialException;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.services.credential_service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;
    
    /**
     * Get CredentialModel by id
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<CredentialModel> getCredential(@PathVariable String id) {
        CredentialModel credentialModel = credentialService.get(Integer.parseInt(id));
        return new ResponseEntity<>(credentialModel, HttpStatus.OK);
    }

    /**
     * Create a credential
     * @param credentialModel
     * @return
     */
    @PostMapping("/add")
    public String addCredential(@ModelAttribute CredentialModel credentialModel) {
        try {
            CredentialModel credentialModel1 = credentialService.get(credentialModel.getCredentialId());
            if (credentialModel1 != null) {
                credentialService.update(credentialModel);
                credentialService.setMsgError(null);
            }else {
                credentialService.add(credentialModel);
                credentialService.setMsgError(null);
            }
        } catch (CredentialException e) {
            credentialService.setMsgError(e.getMessage());
        } catch (Exception e) {
            credentialService.setMsgError("Error: An unexpected error has occurred during the insertion process.");
        }
        return "redirect:/home";
    }

    /**
     * Delete credential with id
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteCredential(@PathVariable String id) {
        credentialService.deleteByID(Integer.parseInt(id));
        return "redirect:/home";
    }

}
