package com.project.manage.exception;

public class NutritionApiException extends RuntimeException{
    public NutritionApiException() {
        super();
    }

    public NutritionApiException(String message) {
        super(message);
    }

    public NutritionApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public NutritionApiException(Throwable cause) {
        super(cause);
    }

    protected NutritionApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
