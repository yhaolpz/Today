package com.yhao.today.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.yhao.today.db.dao.BingPicDao;
import com.yhao.today.db.dao.HistoryTodayDao;
import com.yhao.today.db.dao.MovieOfficeDao;
import com.yhao.today.pojo.BingPic;
import com.yhao.today.pojo.HistoryToday;
import com.yhao.today.pojo.MovieOffice;

/**
 * Created by yhao on 2017/11/21.
 * https://github.com/yhaolpz
 */

@Database(entities = {BingPic.class, HistoryToday.class,MovieOffice.class}, version = 6 ,exportSchema = true)
public abstract class MyDatabase extends RoomDatabase {

    public abstract BingPicDao bingPicDao();

    public abstract HistoryTodayDao historyTodayDao();

    public abstract MovieOfficeDao movieOfficeDao();

}
