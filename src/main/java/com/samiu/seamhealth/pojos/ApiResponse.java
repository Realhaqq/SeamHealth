package com.samiu.seamhealth.pojos;

import lombok.Data;

public @Data class ApiResponse<T> {
    private Boolean success;
    private String message;
    private int code;
    private T data;

    public ApiResponse(Boolean success, String message, int code, T data) {
        super();
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;


    }

    public ApiResponse(String message, boolean b, String s, T data) {
        super();
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }
}
