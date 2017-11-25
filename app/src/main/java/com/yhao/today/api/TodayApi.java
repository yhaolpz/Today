package com.yhao.today.api;

import com.yhao.today.pojo.BingPic;
import com.yhao.today.pojo.BingPicBody;
import com.yhao.today.pojo.HistoryToday;
import com.yhao.today.pojo.HistoryTodayBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yhao on 2017/11/21.
 * https://github.com/yhaolpz
 */

public interface TodayApi {



    @GET("1287-1?showapi_sign=758d7ae717294581b8b44a4668d01e8b&showapi_appid=50325")
    Call<WrapResult<BingPicBody<BingPic>>> getBingPicCall();

    @GET("119-42?showapi_sign=758d7ae717294581b8b44a4668d01e8b&showapi_appid=50325")
    Call<WrapResult<HistoryTodayBody<List<HistoryToday>>>> getHistoryTodayBody();



}
