package com.yhao.today.api;

import android.arch.lifecycle.LiveData;

import com.yhao.today.pojo.BingPic;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yhao on 2017/11/21.
 * https://github.com/yhaolpz
 */

public interface TodayApi {


    @GET("1287-1?showapi_sign=758d7ae717294581b8b44a4668d01e8b&showapi_appid=50325")
    LiveData<WrapResult<BingPicBody<BingPic>>> getBingPic();

    @GET("1287-1?showapi_sign=758d7ae717294581b8b44a4668d01e8b&showapi_appid=50325")
    Call<WrapResult<BingPicBody<BingPic>>> getBingPicCall();


}
