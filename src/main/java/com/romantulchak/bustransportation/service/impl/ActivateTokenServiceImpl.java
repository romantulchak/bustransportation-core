package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.exception.UserTokenNotFoundException;
import com.romantulchak.bustransportation.model.ActivateToken;
import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.repository.ActiveTokenRepository;
import com.romantulchak.bustransportation.service.ActivateTokenService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class ActivateTokenServiceImpl implements ActivateTokenService {

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = StandardCharsets.US_ASCII;
    private final ActiveTokenRepository activeTokenRepository;

    @Value("${jdj.secure.token.validity}")
    private int tokenValidationInSeconds;

    public ActivateTokenServiceImpl(ActiveTokenRepository activeTokenRepository) {
        this.activeTokenRepository = activeTokenRepository;
    }

    @Override
    public ActivateToken createActivateToken(User user) {
        String token = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII);
        ActivateToken activateToken = new ActivateToken(token, LocalDateTime.now().plusSeconds(getTokenValidationInSeconds()), user);
        saveActiveToken(activateToken);
        return activateToken;
    }

    @Override
    public ActivateToken findActivateToken(String token) {
        return activeTokenRepository.findByToken(token).orElseThrow(() -> new UserTokenNotFoundException(token));
    }

    @Override
    public void removeToken(ActivateToken token) {
        activeTokenRepository.delete(token);
    }

    @Override
    public void removeTokenByToken(String token) {
        long activateToken = activeTokenRepository.getTokenIdByToken(token)
                .orElseThrow(() -> new UserTokenNotFoundException(token));
        activeTokenRepository.deleteById(activateToken);
    }

    @Override
    public void saveActiveToken(ActivateToken activateToken) {
        activeTokenRepository.save(activateToken);
    }

    public int getTokenValidationInSeconds() {
        return tokenValidationInSeconds;
    }
}
