package com.yhao.today.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by yhao on 17-11-21.
 */


@Entity
public class BingPic {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String subtitle;
    private String description;
    private String copyright;
    private String date;
    private String img_1366;
    private String img_1920;





}
