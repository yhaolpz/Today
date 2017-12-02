package com.yhao.today.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.yhao.today.api.Resource;
import com.yhao.today.pojo.BingPic;
import com.yhao.today.pojo.HistoryToday;
import com.yhao.today.pojo.MovieOffice;
import com.yhao.today.repository.BingPicRepository;
import com.yhao.today.repository.HistoryTodayRepository;
import com.yhao.today.repository.MovieOfficeRepository;

import java.util.List;

/**
 * Created by yhao on 2017/11/21.
 * https://github.com/yhaolpz
 */

public class HomeViewModel extends ViewModel {

    private LiveData<Resource<BingPic>> mBingPicData;
    private BingPicRepository mBingPicRepository;

    private LiveData<Resource<List<HistoryToday>>> mHistoryTodayData;
    private HistoryTodayRepository mHistoryTodayRepository;

    private LiveData<Resource<List<MovieOffice>>> mMovieOfficeData;
    private MovieOfficeRepository mMovieOfficeRepository;


    public HomeViewModel() {
        mBingPicRepository = new BingPicRepository();
        mHistoryTodayRepository = new HistoryTodayRepository();
        mMovieOfficeRepository = new MovieOfficeRepository();
    }

    public LiveData<Resource<BingPic>> getBingPicData() {
        if (mBingPicData == null) {
            mBingPicData = new MediatorLiveData<>();
        }
        mBingPicData = mBingPicRepository.loadBingPic();
        return mBingPicData;
    }

    public LiveData<Resource<List<HistoryToday>>> getHistoryTodayData() {
        if (mHistoryTodayData == null) {
            mHistoryTodayData = new MediatorLiveData<>();
        }
        mHistoryTodayData = mHistoryTodayRepository.loadHistoryToday();
        return mHistoryTodayData;
    }

    public LiveData<Resource<List<MovieOffice>>> getMovieOfficeData() {
        if (mMovieOfficeData == null) {
            mMovieOfficeData = new MediatorLiveData<>();
        }
        mMovieOfficeData = mMovieOfficeRepository.loadMovieOffice();
        return mMovieOfficeData;
    }



}
