package com.project.manage.exception;

public class WorkPlanNotFoundException extends RuntimeException{
    public WorkPlanNotFoundException() {
        super();
    }

    public WorkPlanNotFoundException(String message) {
        super(message);
    }

    public WorkPlanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkPlanNotFoundException(Throwable cause) {
        super(cause);
    }

    protected WorkPlanNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
