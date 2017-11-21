package com.yhao.today.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.yhao.today.commen.App;
import com.yhao.today.commen.AppExecutors;
import com.yhao.today.commen.net.NetworkBoundResource;
import com.yhao.today.commen.net.Resource;
import com.yhao.today.db.MyDatabase;
import com.yhao.today.db.dao.BingPicDao;
import com.yhao.today.pojo.BingPic;
import com.yhao.today.service.ApiResponse;
import com.yhao.today.service.Webservice;

import javax.inject.Inject;

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
        return new NetworkBoundResource<BingPic, BingPic>(mAppExecutors) {

            @Override
            protected void saveCallResult(BingPic item) {
                mBingPicDao.insertBingPic(item);
            }

            @Override
            protected boolean shouldFetch(BingPic data) {
                return data==null;
            }

            @NonNull
            @Override
            protected LiveData<BingPic> loadFromDb() {
                return mBingPicDao.loadAllBingPic();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<BingPic>> createCall() {
                return mWebservice.getBingPic();
            }

            @Override
            protected void onFetchFailed() {

            }
        }.getAsLiveData();
    }


}
