package com.zhangongjx.infrastructure.domain;

public class ResutlMsg<T> extends DataTablePaging {
    private int code;
    private Boolean success;
    private String msg;
    private  T data;

    public ResutlMsg(int code, Boolean success, String msg,T data) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data=data;
    }

    public  static ResutlMsg success()
    {
        return  new ResutlMsg(0,true,"操作成功",null);
    }
    public  static ResutlMsg error()
    {
        return  new ResutlMsg(9999,false,"操作失败",null);
    }
    public  static ResutlMsg error(int code,String msg)
    {
        return  new ResutlMsg(code,false,msg,null);
    }
    public  static ResutlMsg error(String msg)
    {
        return  new ResutlMsg(9999,false,msg,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
