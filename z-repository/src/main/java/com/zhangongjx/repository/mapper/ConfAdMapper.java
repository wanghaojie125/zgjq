package com.zhangongjx.repository.mapper;

import org.apache.ibatis.jdbc.SQL;

public class ConfAdMapper {

    public String getDatas(final int skip, final int pageSize, final String query) {
        return new SQL() {
            {
                SELECT("*");
                FROM("c_sys_conf_ad");
                WHERE(query + " and deleted=0");
            }
        }.toString() + "limit " + skip + "," + pageSize;
    }

    public String getCount(final String query) {
        return new SQL() {
            {
                SELECT("count(id)");
                FROM("c_sys_conf_ad");
                WHERE(query + " and deleted=0");
            }
        }.toString();
    }
}
