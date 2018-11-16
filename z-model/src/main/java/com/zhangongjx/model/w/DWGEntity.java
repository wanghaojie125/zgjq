package com.zhangongjx.model.w;

import com.zhangongjx.infrastructure.domain.BaseEntity;

import java.sql.Timestamp;

public class DWGEntity extends BaseEntity {
    private int status;
    private String user_name;
    private int member_id;
    private String contact_name;
    private String company;
    private String path;
    private String phone;
    private String message;
    private int oper_id;
    private String oper_name;
    private String oper_log;
    private String quotation_no;
    private String confirm_path;
    private String size;
    private String type;
    private String file_no;
    private  String deal_at;


    public DWGEntity() {
    }

    public String getDeal_at() {
        return deal_at;
    }

    public void setDeal_at(String deal_at) {
        this.deal_at = deal_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOper_id() {
        return oper_id;
    }

    public void setOper_id(int oper_id) {
        this.oper_id = oper_id;
    }

    public String getOper_name() {
        return oper_name;
    }

    public void setOper_name(String oper_name) {
        this.oper_name = oper_name;
    }

    public String getOper_log() {
        return oper_log;
    }

    public void setOper_log(String oper_log) {
        this.oper_log = oper_log;
    }

    public String getQuotation_no() {
        return quotation_no;
    }

    public void setQuotation_no(String quotation_no) {
        this.quotation_no = quotation_no;
    }

    public String getConfirm_path() {
        return confirm_path;
    }

    public void setConfirm_path(String confirm_path) {
        this.confirm_path = confirm_path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFile_no() {
        return file_no;
    }

    public void setFile_no(String file_no) {
        this.file_no = file_no;
    }
}
