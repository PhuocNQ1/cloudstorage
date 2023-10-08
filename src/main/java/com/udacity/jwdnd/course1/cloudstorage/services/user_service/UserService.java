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
public class UserService  implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    public List<UserModel> getAll() {
        return userMapper.getAll();
    }

    public UserModel get(int id) {
        return userMapper.get(id);
    }

    public void add(UserModel userModel) {
        userMapper.add(userModel);
    }

    public void update(UserModel userModel) {
        userMapper.update(userModel);
    }

    public void deleteByID(int id) {
        userMapper.deleteByID(id);
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userMapper.getByUserName(s);
    }

    public Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserModel user = (UserModel) authentication.getPrincipal();
            return user.getUserId();
        }
        return null;
    }
}
