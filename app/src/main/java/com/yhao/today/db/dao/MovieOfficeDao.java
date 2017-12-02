package com.yhao.today.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.yhao.today.pojo.BingPic;
import com.yhao.today.pojo.MovieOffice;

import java.util.List;

/**
 * Created by yhao on 17-11-21.
 * room dao
 */
@Dao
public interface MovieOfficeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(List<MovieOffice> movieOffices);


    @Query("SELECT * FROM MovieOffice WHERE date = :date")
    LiveData<List<MovieOffice>> load(String date);


}
