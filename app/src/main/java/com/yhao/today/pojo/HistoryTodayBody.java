package com.yhao.today.pojo;

/**
 * Created by yhao on 2017/11/25.
 * https://github.com/yhaolpz
 */

public class HistoryTodayBody<T> {
    private int ret_code;
    private T list;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "HistoryTodayBody{" +
                "ret_code=" + ret_code +
                ", list=" + list +
                '}';
    }
}
