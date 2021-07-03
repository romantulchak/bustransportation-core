package com.romantulchak.bustransportation.utility;

import com.romantulchak.bustransportation.exception.UserNotFoundException;
import com.romantulchak.bustransportation.service.impl.UserDetailsImpl;
import org.springframework.security.core.Authentication;

public final class UserUtility {
    public static UserDetailsImpl userInSystem(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userDetails == null){
            throw new UserNotFoundException();
        }
        return userDetails;
    }
}
