package com.zhangongjx.message.goods.view;

import java.util.List;

public class GoodsTypeViewModel {
    private int id;
    private  String name;
    private List<GoodsTypeViewModel> sub;

    public GoodsTypeViewModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GoodsTypeViewModel> getSub() {
        return sub;
    }

    public void setSub(List<GoodsTypeViewModel> sub) {
        this.sub = sub;
    }
}
