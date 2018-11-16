package com.zhangongjx.model.i;

import com.zhangongjx.infrastructure.domain.BaseEntity;

import java.sql.Timestamp;

/*
* 相册表*/
public class GalleryEntity extends BaseEntity {
    private   String name;
    private  String cover;
    private  String path;
    private  int count;
    private  int sort;
    private  String desp;

    public GalleryEntity(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }
}
