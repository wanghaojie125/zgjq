package com.zhangongjx.model.c;

import com.zhangongjx.infrastructure.domain.BaseEntity;

import java.sql.Timestamp;

public class ConfInfoEntity extends BaseEntity {
    private  String name;
    private  String title;
    private String desp;
    private  String  keyword;
    private  String  logo;
    private  String service_tel;
    private  String service_email;
    private String remark;

    public ConfInfoEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getService_tel() {
        return service_tel;
    }

    public void setService_tel(String service_tel) {
        this.service_tel = service_tel;
    }

    public String getService_email() {
        return service_email;
    }

    public void setService_email(String service_email) {
        this.service_email = service_email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
