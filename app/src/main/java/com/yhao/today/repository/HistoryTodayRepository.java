package com.yhao.today.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.yhao.today.api.NetworkBoundResource;
import com.yhao.today.api.Resource;
import com.yhao.today.api.TodayApi;
import com.yhao.today.api.WrapResult;
import com.yhao.today.commen.App;
import com.yhao.today.commen.AppExecutors;
import com.yhao.today.db.dao.HistoryTodayDao;
import com.yhao.today.pojo.HistoryToday;
import com.yhao.today.pojo.HistoryTodayBody;
import com.yhao.today.util.TimeUtil;

import java.util.List;

import retrofit2.Call;

/**
 * Created by yhao on 2017/11/25.
 * https://github.com/yhaolpz
 */

public class HistoryTodayRepository {

    private HistoryTodayDao mHistoryTodayDao;
    private TodayApi mTodayApi;
    private AppExecutors mAppExecutors;


    public HistoryTodayRepository() {
        mHistoryTodayDao = App.getMyDatabase().historyTodayDao();
        mTodayApi = App.getTodayApi();
        mAppExecutors = App.getAppExecutors();
    }

    public LiveData<Resource<List<HistoryToday>>> loadHistoryToday() {
        return new NetworkBoundResource<List<HistoryToday>, HistoryTodayBody<List<HistoryToday>>>(mAppExecutors) {

            @Override
            protected void saveCallResult(HistoryTodayBody<List<HistoryToday>> item) {
                mHistoryTodayDao.insertHistoryToday(item.getList());
            }

            @Override
            protected boolean shouldFetch(List<HistoryToday> data) {
                return data == null || data.isEmpty() ||
                        (App.getOneMinRateLimit().shouldFetch(data.getClass().getSimpleName())
                                && !(TimeUtil.getDay() == data.get(0).getDay()
                                && TimeUtil.getMonth() == data.get(0).getMonth())
                        );
            }

            @NonNull
            @Override
            protected LiveData<List<HistoryToday>> loadFromDb() {
                return mHistoryTodayDao.load(TimeUtil.getMonth(), TimeUtil.getDay());
            }

            @NonNull
            @Override
            protected Call<WrapResult<HistoryTodayBody<List<HistoryToday>>>> createCall() {
                return mTodayApi.getHistoryTodayBody();
            }

            @Override
            protected void onFetchFailed() {
                Logger.e("onFetchFailed");
            }

        }.getAsLiveData();
    }

}
