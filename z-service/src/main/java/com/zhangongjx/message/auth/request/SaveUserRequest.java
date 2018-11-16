package com.zhangongjx.message.auth.request;

import com.zhangongjx.message.Request;

public class SaveUserRequest extends Request {
    //用户名
    private String username;
    //真实姓名，暂时无用
    private String realname;
    //邮箱
    private String email;
    //手机号码
    private String phone;
    //部门名称
    private String dep;
    //部门id
    private int dep_id;
    //密码
    private String pwd;
    //确认密码
    private String confirmpwd;
    //备注
    private String remark;
    //验证码
    private String authenticode;

    public SaveUserRequest() {
    }

    public String getAuthenticode() {
        return authenticode;
    }

    public void setAuthenticode(String authenticode) {
        this.authenticode = authenticode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConfirmpwd() {
        return confirmpwd;
    }

    public void setConfirmpwd(String confirmpwd) {
        this.confirmpwd = confirmpwd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
