package com.yhao.today.service;


/**
 * Created by yhao on 17-11-21.
 */

public class Resource<T> {

    static int SUCCESS = 0;
    static int ERROR = 1;
    static int LOADING = 2;


    public final int showapi_res_code;
    public final T showapi_res_body;
    public final String showapi_res_error;

    private Resource(int showapi_res_code, T showapi_res_body, String showapi_res_error) {
        this.showapi_res_code = showapi_res_code;
        this.showapi_res_body = showapi_res_body;
        this.showapi_res_error = showapi_res_error;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String showapi_res_error, T showapi_res_body) {
        return new Resource<>(ERROR, showapi_res_body, showapi_res_error);
    }

    public static <T> Resource<T> loading(T showapi_res_body) {
        return new Resource<>(LOADING, showapi_res_body, null);
    }
}
