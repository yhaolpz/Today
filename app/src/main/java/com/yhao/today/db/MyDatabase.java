package com.yhao.today.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.yhao.today.db.dao.BingPicDao;
import com.yhao.today.pojo.BingPic;

/**
 * Created by yhao on 2017/11/21.
 * https://github.com/yhaolpz
 */

@Database(entities = {BingPic.class}, version = 2 )
public abstract class MyDatabase extends RoomDatabase {

    public abstract BingPicDao bingPicDao();

}
