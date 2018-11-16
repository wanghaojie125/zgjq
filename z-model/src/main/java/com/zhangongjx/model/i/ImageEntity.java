package com.zhangongjx.model.i;

import com.zhangongjx.infrastructure.domain.BaseEntity;

import java.sql.Timestamp;

/*
 * 图片*/
public class ImageEntity extends BaseEntity {
    private String path;
    private int gallery_id;

    public ImageEntity(){
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getGallery_id() {
        return gallery_id;
    }

    public void setGallery_id(int gallery_id) {
        this.gallery_id = gallery_id;
    }
}
