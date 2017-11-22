package com.yhao.today.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.yhao.today.commen.App;
import com.yhao.today.commen.AppExecutors;
import com.yhao.today.commen.net.NetworkBoundResource;
import com.yhao.today.commen.net.Resource;
import com.yhao.today.db.dao.BingPicDao;
import com.yhao.today.pojo.BingPic;
import com.yhao.today.pojo.Body;
import com.yhao.today.service.Webservice;
import com.yhao.today.service.WrapResult;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by yhao on 17-11-21.
 */

public class BingPicRepository {

    private final BingPicDao mBingPicDao;
    private final Webservice mWebservice;
    private final AppExecutors mAppExecutors;

    @Inject
    public BingPicRepository(BingPicDao bingPicDao, Webservice webservice, AppExecutors appExecutors) {
        mBingPicDao = bingPicDao;
        mWebservice = webservice;
        mAppExecutors = appExecutors;
    }

    public BingPicRepository() {
        this(App.get().getBingPicDao(), App.get().getWebservice(), App.get().getAppExecutors());
    }

    public LiveData<Resource<BingPic>> loadBingPic() {
        return new NetworkBoundResource<BingPic, Body<BingPic>>(mAppExecutors) {

            @Override
            protected void saveCallResult(Body<BingPic> item) {

                long dbRaw = mBingPicDao.insertBingPic(item.getData());
                Logger.d("saveCallResult " + item + " dbRaw=" + dbRaw);

            }

            @Override
            protected boolean shouldFetch(BingPic data) {
                Logger.d("shouldFetch");
                return true;
            }

            @NonNull
            @Override
            protected LiveData<BingPic> loadFromDb() {
                //TODO  失败
                LiveData<BingPic> dbData = mBingPicDao.load("20171123");
                Logger.d("loadFromDb dbData="+dbData.getValue());
                return dbData;
            }

            @NonNull
            @Override
            protected Call<WrapResult<Body<BingPic>>> createCall() {
                Logger.d("createCall");
                 return mWebservice.getBingPicCall();
            }

            @Override
            protected void onFetchFailed() {

                Logger.d("onFetchFailed");

            }

        }.getAsLiveData();
    }


}
