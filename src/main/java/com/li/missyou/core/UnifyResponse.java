package com.li.missyou.core;

public class UnifyResponse {
    private int code;
    private String message;
    private String response;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getResponse() {
        return response;
    }

    public UnifyResponse(int code, String message, String response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

}
