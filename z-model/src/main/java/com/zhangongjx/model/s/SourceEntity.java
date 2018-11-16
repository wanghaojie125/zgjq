package com.zhangongjx.model.s;

import com.zhangongjx.infrastructure.domain.BaseEntity;

/*
* 权限资源表
* */
public class SourceEntity extends BaseEntity {
    private  String name;
    private  String url;
    private  int par_id;
    private  int type;
    private  String remark;
    private  int sort;
    private  String css;



    public SourceEntity(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPar_id() {
        return par_id;
    }

    public void setPar_id(int par_id) {
        this.par_id = par_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }
}
