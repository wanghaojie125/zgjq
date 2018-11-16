package com.zhangongjx.model.s;

import com.zhangongjx.infrastructure.domain.BaseEntity;

/**
 * @author: wanghaojie
 * @description：
 * @date: 2018/10/18 17:44
 */
public class TableData extends BaseEntity {
    private String tKey;
    private String tValue;

    public TableData() {
    }



    public String gettKey() {
        return tKey;
    }

    public void settKey(String tKey) {
        this.tKey = tKey;
    }

    public String gettValue() {
        return tValue;
    }

    public void settValue(String tValue) {
        this.tValue = tValue;
    }
}
