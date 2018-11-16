package com.zhangongjx.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "conf")
public class Conf {

    private   String upload_path;
    private   String img_url;
    private  String sms_accessKeyId;
    private  String sms_accessKeySecret;

    public String getSms_accessKeyId() {
        return sms_accessKeyId;
    }

    public void setSms_accessKeyId(String sms_accessKeyId) {
        this.sms_accessKeyId = sms_accessKeyId;
    }

    public String getSms_accessKeySecret() {
        return sms_accessKeySecret;
    }

    public void setSms_accessKeySecret(String sms_accessKeySecret) {
        this.sms_accessKeySecret = sms_accessKeySecret;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getUpload_path() {
        return upload_path;
    }

    public void setUpload_path(String upload_path) {
        this.upload_path = upload_path;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }



    
}
