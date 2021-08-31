package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.model.ActivateToken;
import com.romantulchak.bustransportation.model.User;

public interface ActivateTokenService {

    ActivateToken createActivateToken(User user);

    ActivateToken findActivateToken(String token);

    void removeToken(ActivateToken token);

    void removeTokenByToken(String token);

    void saveActiveToken(ActivateToken activateToken);
}

