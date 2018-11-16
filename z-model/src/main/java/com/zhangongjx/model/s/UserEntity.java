package com.zhangongjx.model.s;

import com.zhangongjx.infrastructure.domain.BaseEntity;
import java.sql.Timestamp;

/*
* 系统用户表
* */
public class UserEntity extends BaseEntity {
    private int enabled;
    private  String user_name;
    private  String pass_word;
    private  String email;
    private  String remark;
    private int dep_id;
    private String dep_name;
    private  String real_name;
    private Timestamp last_login_at;
    private  String head_portraits;
    private String confirm_pass_word;
    //手机号码
    private String phone;

    public  UserEntity()
    {

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHead_portraits() {
        return head_portraits;
    }

    public void setHead_portraits(String head_portraits) {
        this.head_portraits = head_portraits;
    }

    public String getConfirm_pass_word() {
        return confirm_pass_word;
    }

    public void setConfirm_pass_word(String confirm_pass_word) {
        this.confirm_pass_word = confirm_pass_word;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public Timestamp getLast_login_at() {
        return last_login_at;
    }

    public void setLast_login_at(Timestamp last_login_at) {
        this.last_login_at = last_login_at;
    }
}
