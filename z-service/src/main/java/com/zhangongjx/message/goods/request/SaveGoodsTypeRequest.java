package com.zhangongjx.message.goods.request;

import com.zhangongjx.message.Request;
import com.zhangongjx.message.Request;

public class SaveGoodsTypeRequest extends Request {
    private  String add_type_name;
    private  int add_type_parent;

    public String getAdd_type_name() {
        return add_type_name;
    }

    public void setAdd_type_name(String add_type_name) {
        this.add_type_name = add_type_name;
    }

    public int getAdd_type_parent() {
        return add_type_parent;
    }

    public void setAdd_type_parent(int add_type_parent) {
        this.add_type_parent = add_type_parent;
    }
}
