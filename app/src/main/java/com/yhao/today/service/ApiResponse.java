package com.yhao.today.service;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yhao on 17-11-21.
 */

public class ApiResponse<RequestType> implements Callback {


    public String errorMessage;
    public RequestType body;

    @Override
    public void onResponse(Call call, Response response) {

    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }

    public boolean isSuccessful() {
        return true;
    }
}
