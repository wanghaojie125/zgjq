package com.zhangongjx.model.l;

import com.zhangongjx.infrastructure.domain.BaseEntity;

public class LogInterviewEntity extends BaseEntity {
    private int count;
    private String url;
    private String source;
    public LogInterviewEntity() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
