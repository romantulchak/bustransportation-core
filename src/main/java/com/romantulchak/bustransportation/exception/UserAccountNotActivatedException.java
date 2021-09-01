package com.romantulchak.bustransportation.exception;

public class UserAccountNotActivatedException extends RuntimeException {
    public UserAccountNotActivatedException() {
        super("User account is not activated unless you receive an email with an activation link" +
                " or a link that has expired, please enter the link below");
    }
}
