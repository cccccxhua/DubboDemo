package com.timor.domain;

/**
 * @author CXH
 * @description
 * @create 2022-07-10 14:08
 */
public class ValidError {
    private String field;
    private String message;

    public ValidError() {
    }

    public ValidError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ValidError{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
