package com.udacity.jwdnd.course1.cloudstorage.services.user_service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    /**
     * Retrieves a list of all user records.
     *
     * @return
     */
    public List<UserModel> getAll() {
        return userMapper.getAll();
    }

    /**
     * Retrieves a user record based on the provided ID.
     *
     * @param id
     * @return
     */
    public UserModel get(int id) {
        return userMapper.get(id);
    }

    /**
     * Adds a new user record to the database.
     *
     * @param userModel
     */
    public void add(UserModel userModel) {
        userMapper.add(userModel);
    }

    /**
     * Updates an existing user record in the database.
     *
     * @param userModel
     */
    public void update(UserModel userModel) {
        userMapper.update(userModel);
    }

    /**
     * Deletes a user record from the database based on the provided ID.
     *
     * @param id
     */
    public void deleteByID(int id) {
        userMapper.deleteByID(id);
    }

    /**
     * Loads user details by username for authentication purposes.
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.getByUserName(username);
    }

    /**
     * Retrieves the current user's ID from the security context.
     *
     * @return
     */
    public Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserModel user = (UserModel) authentication.getPrincipal();
            return user.getUserId();
        }
        return null;
    }

}
