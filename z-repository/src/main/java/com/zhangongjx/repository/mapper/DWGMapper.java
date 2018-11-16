package com.zhangongjx.repository.mapper;

import org.apache.ibatis.jdbc.SQL;

public class DWGMapper {

    public String getDatas(final int skip, final int pageSize, final String query) {
        return new SQL() {
            {
                SELECT("*");
                FROM("w_dwg");
                WHERE(query + " and deleted=0");
            }
        }.toString() + "limit " + skip + "," + pageSize;
    }

    public String getCount(final String query) {
        return new SQL() {
            {
                SELECT("count(id)");
                FROM("w_dwg");
                WHERE(query + " and deleted=0");
            }
        }.toString();
    }
    public String statisticsDWGStatus(final String query) {
        return new SQL() {
            {
                SELECT("count(id) as count,status");
                FROM("w_dwg");
                WHERE(query + " and deleted=0");
            }
        }.toString()+" group by status order by status";
    }
}
