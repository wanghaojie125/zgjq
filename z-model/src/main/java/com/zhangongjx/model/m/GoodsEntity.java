package com.zhangongjx.model.m;

import com.zhangongjx.infrastructure.domain.BaseEntity;

public class GoodsEntity extends BaseEntity {
    private  String name;
    private  String type_id;
    private  String type_name;
    private  String sub_title;
    private int brand_id;
    private String brand_name;
    private String  introduce;
    private String goods_no;
    private double selling_price;
    private double market_price;
    private  int store;
    private int store_alarm_value;
    private  String unit;
    private double weight;
    private int status;
    private String detail_title;
    private String detail_introduce;
    private  String keyword;
    private  String remark;
    private String img;
    private  String label;
    private  int selling_count;

    public GoodsEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getGoods_no() {
        return goods_no;
    }

    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public int getStore_alarm_value() {
        return store_alarm_value;
    }

    public void setStore_alarm_value(int store_alarm_value) {
        this.store_alarm_value = store_alarm_value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail_title() {
        return detail_title;
    }

    public void setDetail_title(String detail_title) {
        this.detail_title = detail_title;
    }

    public String getDetail_introduce() {
        return detail_introduce;
    }

    public void setDetail_introduce(String detail_introduce) {
        this.detail_introduce = detail_introduce;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getSelling_count() {
        return selling_count;
    }

    public void setSelling_count(int selling_count) {
        this.selling_count = selling_count;
    }
}
