package com.zhangongjx.model.s;

import com.zhangongjx.infrastructure.domain.BaseEntity;

import java.sql.Timestamp;

/*
 *关联表
 * */
public class SourceRelateEntity extends BaseEntity {
    private int source_id;
    private int relate_id;
    private int type;
    public  SourceRelateEntity(){

    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public int getRelate_id() {
        return relate_id;
    }

    public void setRelate_id(int relate_id) {
        this.relate_id = relate_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
