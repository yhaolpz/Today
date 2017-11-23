package com.yhao.today.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by yhao on 17-11-21.
 */


@Entity()
public class BingPic {

    @NonNull
    @PrimaryKey
    private String date;
    private String title;
    private String subtitle;
    private String description;
    private String copyright;
    private String img_1366;
    private String img_1920;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg_1366() {
        return img_1366;
    }

    public void setImg_1366(String img_1366) {
        this.img_1366 = img_1366;
    }

    public String getImg_1920() {
        return img_1920;
    }

    public void setImg_1920(String img_1920) {
        this.img_1920 = img_1920;
    }

    @Override
    public String toString() {
        return "BingPic{" +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", description='" + description + '\'' +
                ", copyright='" + copyright + '\'' +
                ", date='" + date + '\'' +
                ", img_1366='" + img_1366 + '\'' +
                ", img_1920='" + img_1920 + '\'' +
                '}';
    }
}
