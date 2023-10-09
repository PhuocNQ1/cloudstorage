package com.udacity.jwdnd.course1.cloudstorage.services.authen_service;

import java.util.ArrayList;

import com.udacity.jwdnd.course1.cloudstorage.services.signup_service.SignUpService;
import com.udacity.jwdnd.course1.cloudstorage.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;

@Service
public class AuthenticationService implements AuthenticationProvider {

    @Autowired
    private HashService hashService;

    @Autowired
    private UserService userService;

    @Autowired
    private SignUpService signUpService;
    
    @Autowired
    private UserMapper mapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        signUpService.setMsgErr(null);

        UserModel user = mapper.getByUserName(username);
        if (user != null) {
            String encodedSalt = user.getSalt();
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);
            if (user.getPassword().equals(hashedPassword)) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                return new UsernamePasswordAuthenticationToken(userDetails, password, new ArrayList<>());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
