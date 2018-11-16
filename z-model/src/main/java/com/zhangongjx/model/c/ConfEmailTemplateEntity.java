package com.zhangongjx.model.c;

import com.zhangongjx.infrastructure.domain.BaseEntity;

public class ConfEmailTemplateEntity extends BaseEntity {
    private  String type;
    private  String title;
    private String template;

    public ConfEmailTemplateEntity() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
