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

    /**
     * Retrieves a list of all user records.
     *
     * @return
     */
    public List<UserModel> getAll() {
        return mapper.getAll();
    }

    /**
     * Retrieves a user record based on the provided ID.
     *
     * @param id
     * @return
     */
    public UserModel get(int id) {
        return mapper.get(id);
    }

    /**
     * Retrieves a user record based on the provided username.
     *
     * @param userName
     * @return
     */
    public UserModel getByUserName(String userName) {
        return mapper.getByUserName(userName);
    }

    /**
     * Adds a new user record to the database after checking for username uniqueness.
     *
     * @param t
     */
    public void add(UserModel t) {
        UserModel userModel = this.getByUserName(t.getUserName());
        if (userModel != null) {
            throw new SignUpException("Error: UserName Already Exists, Please try again!");
        }
        t.setSalt(hashService.encodedSalt());
        t.setPassword(hashService.getHashedValue(t.getPassword(), t.getSalt()));
        mapper.add(t);
    }


    /**
     * Updates an existing user record in the database.
     *
     * @param t
     */
    public void update(UserModel t) {
        mapper.update(t);
    }

    /**
     * Deletes a user record from the database based on the provided ID.
     *
     * @param id
     */
    public void deleteByID(int id) {
        mapper.deleteByID(id);
    }


}
