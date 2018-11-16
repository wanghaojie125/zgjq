package com.zhangongjx.message.goods.response;

import com.zhangongjx.message.Response;

public class UploadFileResponse {
    private String path;
    private String url;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
