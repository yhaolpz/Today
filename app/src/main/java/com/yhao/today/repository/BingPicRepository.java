package com.yhao.today.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.yhao.today.api.BingPicBody;
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
    private RateLimiter<String> mBingPicRateLimit = new RateLimiter<>(1, TimeUnit.MINUTES);


    @Inject
    public BingPicRepository(BingPicDao bingPicDao, TodayApi todayApi, AppExecutors appExecutors) {
        mBingPicDao = bingPicDao;
        mTodayApi = todayApi;
        mAppExecutors = appExecutors;
    }

    public BingPicRepository() {
        this(App.get().getBingPicDao(), App.get().getTodayservice(), App.get().getAppExecutors());
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
                        (mBingPicRateLimit.shouldFetch(data.getClass().getSimpleName())
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
