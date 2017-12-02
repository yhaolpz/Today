package com.yhao.today.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by yhao on 17-12-2.
 */

@Entity()
public class MovieOffice {
    @NonNull
    @PrimaryKey
    private String date;
    private String AvgPrice;
    private String MovieDay;
    private String Rank;
    private String WomIndex;
    private String SumBoxOffice;
    private String BoxOffice_Up;
    private String MovieName;
    private String AvpPeoPle;
    private String BoxOffice;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvgPrice() {
        return AvgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        AvgPrice = avgPrice;
    }

    public String getMovieDay() {
        return MovieDay;
    }

    public void setMovieDay(String movieDay) {
        MovieDay = movieDay;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public String getWomIndex() {
        return WomIndex;
    }

    public void setWomIndex(String womIndex) {
        WomIndex = womIndex;
    }

    public String getSumBoxOffice() {
        return SumBoxOffice;
    }

    public void setSumBoxOffice(String sumBoxOffice) {
        SumBoxOffice = sumBoxOffice;
    }

    public String getBoxOffice_Up() {
        return BoxOffice_Up;
    }

    public void setBoxOffice_Up(String boxOffice_Up) {
        BoxOffice_Up = boxOffice_Up;
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        MovieName = movieName;
    }

    public String getAvpPeoPle() {
        return AvpPeoPle;
    }

    public void setAvpPeoPle(String avpPeoPle) {
        AvpPeoPle = avpPeoPle;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }


}
