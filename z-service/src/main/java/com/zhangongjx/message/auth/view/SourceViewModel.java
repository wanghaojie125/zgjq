package com.zhangongjx.message.auth.view;

import java.util.List;

public class SourceViewModel {
    private int id;
    private  String name;
    private  String url;
    private  int type;
    private  int sort;
    private  String css;
    private boolean checked;
    private List<SourceViewModel> sub;

    public SourceViewModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<SourceViewModel> getSub() {
        return sub;
    }

    public void setSub(List<SourceViewModel> sub) {
        this.sub = sub;
    }
}
