package com.yhao.today.api;

/**
 * Created by yhao on 2017/11/22.
 * https://github.com/yhaolpz
 */

public class BingPicBody<T> {
    private int ret_code;
    private String ret_message;
    private T data;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_message() {
        return ret_message;
    }

    public void setRet_message(String ret_message) {
        this.ret_message = ret_message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BingPicBody{" +
                "ret_code=" + ret_code +
                ", ret_message='" + ret_message + '\'' +
                ", data=" + data +
                '}';
    }
}
