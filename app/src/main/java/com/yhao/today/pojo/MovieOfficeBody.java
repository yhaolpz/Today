package com.yhao.today.pojo;

/**
 * Created by yhao on 17-12-2.
 */

public class MovieOfficeBody<T> {
    private String ret_code;
    private T datalist;
    private String remark;

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public T getDatalist() {
        return datalist;
    }

    public void setDatalist(T datalist) {
        this.datalist = datalist;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MovieOfficeBody{" +
                "ret_code='" + ret_code + '\'' +
                ", datalist=" + datalist +
                ", remark='" + remark + '\'' +
                '}';
    }
}
