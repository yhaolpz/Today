package com.yhao.today.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.yhao.today.db.MyDatabase;
import com.yhao.today.pojo.BingPicBody;
import com.yhao.today.api.TodayApi;
import com.yhao.today.commen.App;
import com.yhao.today.commen.AppExecutors;
import com.yhao.today.api.NetworkBoundResource;
import com.yhao.today.api.Resource;
import com.yhao.today.db.dao.BingPicDao;
import com.yhao.today.pojo.BingPic;
import com.yhao.today.api.WrapResult;
import com.yhao.today.util.RateLimiter;
import com.yhao.today.util.TimeUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by yhao on 17-11-21.
 *
 */

public class BingPicRepository {

    private final BingPicDao mBingPicDao;
    private final TodayApi mTodayApi;
    private final AppExecutors mAppExecutors;


    public BingPicRepository() {
        mBingPicDao = App.getMyDatabase().bingPicDao();
        mTodayApi = App.getTodayApi();
        mAppExecutors = App.getAppExecutors();
    }

    public LiveData<Resource<BingPic>> loadBingPic() {
        return new NetworkBoundResource<BingPic, BingPicBody<BingPic>>(mAppExecutors) {

            @Override
            protected void saveCallResult(BingPicBody<BingPic> item) {
                mBingPicDao.insertBingPic(item.getData());
            }

            @Override
            protected boolean shouldFetch(BingPic data) {
                return data == null ||
                        (App.getOneMinRateLimit().shouldFetch(data.getClass().getSimpleName())
                                && !TimeUtil.getDateStr().equals(data.getDate()));
            }

            @NonNull
            @Override
            protected LiveData<BingPic> loadFromDb() {
                return mBingPicDao.load(TimeUtil.getDateStr());
            }

            @NonNull
            @Override
            protected Call<WrapResult<BingPicBody<BingPic>>> createCall() {
                return mTodayApi.getBingPicCall();
            }

            @Override
            protected void onFetchFailed() {
                Logger.e("onFetchFailed");
            }

        }.getAsLiveData();
    }


}
