package com.petersen.academitdb.exceptions;

public class UserExistExepcetion extends Exception {

    public UserExistExepcetion() {
    }

    public UserExistExepcetion(String message) {
        super(message);
    }

    public UserExistExepcetion(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistExepcetion(Throwable cause) {
        super(cause);
    }

    public UserExistExepcetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
