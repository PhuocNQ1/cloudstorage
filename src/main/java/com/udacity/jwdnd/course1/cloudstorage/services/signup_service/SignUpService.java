package com.udacity.jwdnd.course1.cloudstorage.services.signup_service;

import com.udacity.jwdnd.course1.cloudstorage.exception.SignUpException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.authen_service.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignUpService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private HashService hashService;

    private String msgErr;

    public String getMsgErr() {
        return msgErr;
    }

    public void setMsgErr(String msgErr) {
        this.msgErr = msgErr;
    }

    public List<UserModel> getAll() {
        return mapper.getAll();
    }

    public UserModel get(int id) {
        return mapper.get(id);
    }

    public UserModel getByUserName(String userName) {
        return mapper.getByUserName(userName);
    }

    public void add(UserModel t) {
        UserModel userModel = this.getByUserName(t.getUserName());
        if (userModel != null) {
            throw new SignUpException("Error: UserName Already Exists, Please try again!");
        }
        t.setSalt(hashService.encodedSalt());
        t.setPassword(hashService.getHashedValue(t.getPassword(), t.getSalt()));
        mapper.add(t);
    }

    public void update(UserModel t) {
        mapper.update(t);
    }

    public void deleteByID(int id) {
        mapper.deleteByID(id);
    }

}
