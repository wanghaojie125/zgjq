package com.zhangongjx.message.auth.request;

import com.zhangongjx.message.Request;

/**
 * @author: wanghaojie
 * @description：
 * @date: 2018/10/21 22:37
 */
public class SavePicsRequest extends Request {
    /**
     * 上传图纸的路径
     */
    private String url;
    /**
     * 绘图比例1:1 ; 0:是，1:否
     */
    private String picScale;
    /**
     * 绘图视角；1：第一角；3:第三角
     */
    private String picAngular;
    /**
     * 图纸是否可以实际测量  0:是，1:否
     */
    private String picIsMeasure;
    /**
     * 留言备注
     */
    private String remark;

    public SavePicsRequest() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicScale() {
        return picScale;
    }

    public void setPicScale(String picScale) {
        this.picScale = picScale;
    }

    public String getPicAngular() {
        return picAngular;
    }

    public void setPicAngular(String picAngular) {
        this.picAngular = picAngular;
    }

    public String getPicIsMeasure() {
        return picIsMeasure;
    }

    public void setPicIsMeasure(String picIsMeasure) {
        this.picIsMeasure = picIsMeasure;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
