package com.kq.entity;

/**
 * @author kq
 * @date 2021-07-16 9:40
 * @since 2020-0630
 */
public class MyResponse {

    private int code;
    private int type;
    private Object data;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
