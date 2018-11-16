package com.zhangongjx.model.d;

import com.zhangongjx.infrastructure.domain.BaseEntity;

public class PoleEntity extends BaseEntity {
    private  String  name;
    private  String type;
    private  double price;
    private int oper_id;
    private  String oper_name;
    private  double ladder_price;
    private  double axis_while_price;

    public double getLadder_price() {
        return ladder_price;
    }

    public void setLadder_price(double ladder_price) {
        this.ladder_price = ladder_price;
    }

    public double getAxis_while_price() {
        return axis_while_price;
    }

    public void setAxis_while_price(double axis_while_price) {
        this.axis_while_price = axis_while_price;
    }

    public PoleEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
