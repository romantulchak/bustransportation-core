package com.romantulchak.bustransportation.utils;

import com.romantulchak.bustransportation.exception.UserNotFoundException;
import com.romantulchak.bustransportation.service.impl.UserDetailsImpl;
import org.springframework.security.core.Authentication;

public final class UserUtils {
    private UserUtils(){}

    public static UserDetailsImpl userInSystem(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userDetails == null){
            throw new UserNotFoundException();
        }
        return userDetails;
    }
}
