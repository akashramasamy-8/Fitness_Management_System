package com.project.manage.exception;

public class ContentNotHavingException extends RuntimeException{
    public ContentNotHavingException() {
        super();
    }

    public ContentNotHavingException(String message) {
        super(message);
    }

    public ContentNotHavingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContentNotHavingException(Throwable cause) {
        super(cause);
    }

    protected ContentNotHavingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
