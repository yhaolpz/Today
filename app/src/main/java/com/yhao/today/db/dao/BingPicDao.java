package com.yhao.today.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.yhao.today.pojo.BingPic;

import java.util.List;

/**
 * Created by yhao on 17-11-21.
 * room dao
 */
@Dao
public interface BingPicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertBingPic(BingPic... bingPics);



    /**
     * 返回 LiveData 就在主线程 ，
     * 否则必须在子线程，不然就会报错
     */
    @Query("SELECT * FROM BingPic WHERE date = :date")
    LiveData<BingPic> load(String date);



}
