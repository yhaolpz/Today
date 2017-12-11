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
import com.yhao.today.db.dao.MovieOfficeDao;
import com.yhao.today.pojo.MovieOffice;
import com.yhao.today.pojo.MovieOfficeBody;
import com.yhao.today.util.TimeUtil;

import java.util.List;

import retrofit2.Call;

/**
 * Created by yhao on 17-12-2.
 *
 */

public class MovieOfficeRepository {
    private final MovieOfficeDao mMovieOfficeDao;
    private final TodayApi mTodayApi;
    private final AppExecutors mAppExecutors;

    public MovieOfficeRepository() {
        mMovieOfficeDao = App.getMyDatabase().movieOfficeDao();
        mTodayApi = App.getTodayApi();
        mAppExecutors = App.getAppExecutors();
    }

    public LiveData<Resource<List<MovieOffice>>> loadMovieOffice() {
        return new NetworkBoundResource<List<MovieOffice>, MovieOfficeBody<List<MovieOffice>>>(mAppExecutors) {

            @Override
            protected void saveCallResult(MovieOfficeBody<List<MovieOffice>> item) {
                String dateStr = TimeUtil.getDateStr();
                List<MovieOffice> data = item.getDatalist();
                for (MovieOffice movieOffice : data) {
                    movieOffice.setDate(dateStr);
                }
                mMovieOfficeDao.insert(data);


            }

            @Override
            protected boolean shouldFetch(List<MovieOffice> data) {
                return data == null || data.isEmpty() ||
                        (App.getOneMinRateLimit().shouldFetch(data.getClass().getSimpleName())
                                && (TimeUtil.getDateStr() != data.get(0).getDate()));
            }

            @NonNull
            @Override
            protected LiveData<List<MovieOffice>> loadFromDb() {
                return mMovieOfficeDao.load(TimeUtil.getDateStr());
            }

            @NonNull
            @Override
            protected Call<WrapResult<MovieOfficeBody<List<MovieOffice>>>> createCall() {
                return mTodayApi.getMovieOffice();
            }

            @Override
            protected void onFetchFailed() {
                Logger.e("onFetchFailed");
            }
        }.getAsLiveData();
    }
}
