package com.yhao.today.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.yhao.today.pojo.HistoryToday;

import java.util.List;

/**
 * Created by yhao on 2017/11/25.
 * https://github.com/yhaolpz
 */
@Dao
public interface HistoryTodayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertHistoryToday(List<HistoryToday> historyTodayList);

    @Query("SELECT * FROM HistoryToday WHERE month = :month AND day = :day")
    LiveData<List<HistoryToday>> load(int month, int day);


}
