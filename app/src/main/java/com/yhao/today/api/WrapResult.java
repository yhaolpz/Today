package com.yhao.today.api;

import retrofit2.Response;

/**
 * Created by yhao on 2017/11/22.
 * https://github.com/yhaolpz
 */

public class WrapResult<T> {
    private int showapi_res_code;
    private T showapi_res_body;
    private String showapi_res_error;


    public WrapResult(Throwable throwable) {
        showapi_res_error = throwable.getMessage();

    }


    public WrapResult(Response<WrapResult<T>> response) {
        showapi_res_body = response.body().getShowapi_res_body();
        showapi_res_code = response.body().getShowapi_res_code();

    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public T getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(T showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public boolean isSuccessFul() {
        return true;
    }


    @Override
    public String toString() {
        return "WrapResult{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_body=" + showapi_res_body +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                '}';
    }
}
