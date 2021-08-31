package com.romantulchak.bustransportation.utils.email;

import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.model.email.AbstractEmail;
import org.springframework.web.util.UriComponentsBuilder;

public class AccountVerificationEmail extends AbstractEmail {

    private String token;

    @Override
    public <T> void init(T context, String token) {
        User user = (User) context;
        initUserData(user);
        setTemplateLocation("emails/email-verification");
        setSubject("Activate your account");
        setFrom("KzzxD29@gmail.com");
        setTo(user.getEmail());
        setToken(token);
    }

    private void initUserData(User user) {
        put("firstName", user.getFirstName());
        put("lastName", user.getLastName());
        put("username", user.getUsername());
    }

    private void setToken(String token){
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(String baseUrl, String token){
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/register/verify")
                .queryParam("token", token)
                .toUriString();
        put("verificationURL", url);
    }
}
