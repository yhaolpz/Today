package com.yhao.today.pojo;

import java.util.List;

/**
 * Created by yhao on 2017/11/25.
 * https://github.com/yhaolpz
 */

public class HistoryTodayBody {
    private int ret_code;
    private List<HistoryToday> list;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public List<HistoryToday> getList() {
        return list;
    }

    public void setList(List<HistoryToday> list) {
        this.list = list;
    }
}
