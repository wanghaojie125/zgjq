package com.zhangongjx.model.m;

import com.zhangongjx.infrastructure.domain.BaseEntity;

public class GoodsTypeEntity extends BaseEntity {
    private  String name;
    private  int par_id;
    private  String img;
    private  String remark;

    public GoodsTypeEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPar_id() {
        return par_id;
    }

    public void setPar_id(int par_id) {
        this.par_id = par_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
