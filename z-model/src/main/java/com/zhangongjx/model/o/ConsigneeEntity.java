package com.zhangongjx.model.o;

import com.zhangongjx.infrastructure.domain.BaseEntity;

public class ConsigneeEntity extends BaseEntity {
    private String name;
    private String phone;
    private  String province;
    private  String city;
    private  String county;
    private  String address;
    private  int defaulted;
    private int member_id;
    private  String post_code;

    public ConsigneeEntity() {
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(int defaulted) {
        this.defaulted = defaulted;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }
}
