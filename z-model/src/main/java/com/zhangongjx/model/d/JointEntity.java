package com.zhangongjx.model.d;

import com.zhangongjx.infrastructure.domain.BaseEntity;

public class JointEntity extends BaseEntity {
    private  String  name;
    private  String type;
    private  double out_price;
    private  double in_price;
    private  double mm_price;
    private  double base_length;
    private int oper_id;
    private  String oper_name;

    public JointEntity() {
    }

    public double getOut_price() {
        return out_price;
    }

    public void setOut_price(double out_price) {
        this.out_price = out_price;
    }

    public double getIn_price() {
        return in_price;
    }

    public void setIn_price(double in_price) {
        this.in_price = in_price;
    }

    public double getMm_price() {
        return mm_price;
    }

    public void setMm_price(double mm_price) {
        this.mm_price = mm_price;
    }

    public double getBase_length() {
        return base_length;
    }

    public void setBase_length(double base_length) {
        this.base_length = base_length;
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
