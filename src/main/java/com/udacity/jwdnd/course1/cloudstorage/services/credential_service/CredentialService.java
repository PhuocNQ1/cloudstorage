package com.udacity.jwdnd.course1.cloudstorage.services.credential_service;

import com.udacity.jwdnd.course1.cloudstorage.exception.CredentialException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.services.authen_service.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.generic_service.T_Service;
import com.udacity.jwdnd.course1.cloudstorage.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService extends T_Service<CredentialModel> {

    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserService userService;

    private String msgError;

    @Override
    public List<CredentialModel> getAll() {
        return credentialMapper.getAll();
    }

    @Override
    public List<CredentialModel> getAllByUserId(int id) {
       return credentialMapper.getAllByUserId(id);
    }

    @Override
    public CredentialModel get(int id) {
        CredentialModel cre = credentialMapper.get(id);
        if(cre != null){
            cre.setPassword(encryptionService.decryptValue(cre.getPassword(), cre.getKey()));
            return cre;
        }else{
            return null;
        }
    }

    @Override
    public void add(CredentialModel t) {
        t.setUserId(userService.getCurrentUserId());
        if(isDuplicateURL(t.getUrl(),t.getUserId())){
            throw new CredentialException("Error: URL is duplicate, Please try again!");
        }
        t.setKey(encryptionService.generateKey());
        t.setPassword(encryptionService.encryptValue(t.getPassword(), t.getKey()));
        t.setUserId(userService.getCurrentUserId());
        credentialMapper.add(t);
    }

    @Override
    public void update(CredentialModel t) {
        t.setUserId(userService.getCurrentUserId());
        t.setKey(encryptionService.generateKey());
        t.setPassword(encryptionService.encryptValue(t.getPassword(), t.getKey()));
        t.setUserId(userService.getCurrentUserId());
        credentialMapper.update(t);
    }

    @Override
    public void deleteByID(int id) {
        credentialMapper.deleteByID(id);
    }

    private boolean isDuplicateURL(String url,int id){
        List<CredentialModel> credentialModel = this.getAllByUserId(id);
        return credentialModel.stream().anyMatch(cre -> cre.getUrl().equals(url));
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }
}
