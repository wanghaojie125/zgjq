package com.zhangongjx.model.d;

import com.zhangongjx.infrastructure.domain.BaseEntity;

public class MaterialsEntity extends BaseEntity {
    private  String  name;
    private  String sg;
    private  double price;
    private int oper_id;
    private  String oper_name;

    public MaterialsEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSg() {
        return sg;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOper_id() {
        return oper_id;
    }

    public void setOper_id(int oper_id) {
        this.oper_id = oper_id;
    }

    public String getOper_name() {
        return oper_name;
    }

    public void setOper_name(String oper_name) {
        this.oper_name = oper_name;
    }
}
