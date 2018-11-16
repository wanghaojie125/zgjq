package com.zhangongjx.message;

import com.zhangongjx.infrastructure.domain.ResutlMsg;

public class Request {
    private  int id;
    /**
     * 第几页
     */
    private int draw;
    /**
     * 每页多少条
     */
    private  int length;
    /**
     * 从第几条开始
     */
    private  int start;
    public Request()
    {
        this.id=0;
        this.draw=1;
        this.length=20;
        this.start=0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public  ResutlMsg verify()
    {
        return  ResutlMsg.success();
    }
}
