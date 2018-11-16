package com.zhangongjx.repository.mapper;

import org.apache.ibatis.jdbc.SQL;

public class MemberMapper {

    public String getDatas(final int skip, final int pageSize, final String query) {
        return new SQL() {
            {
                SELECT("*");
                FROM("w_member");
                WHERE(query + " and deleted=0");
            }
        }.toString() + "limit " + skip + "," + pageSize;
    }

    public String getDatasByQuery(final String query) {
        return new SQL() {
            {
                SELECT("*");
                FROM("w_member");
                WHERE(query + " and deleted=0");
            }
        }.toString();
    }

    public String getCount(final String query) {
        return new SQL() {
            {
                SELECT("count(id)");
                FROM("w_member");
                WHERE(query + " and deleted=0");
            }
        }.toString();
    }

    public String statisticsCountByTime(final String select, final String query) {
        return new SQL() {
            {
                SELECT("count(id) as count," + select);
                FROM("w_member");
                WHERE(query + " and deleted=0");
            }
        }.toString();
    }

    public String statisticsMemGrowthByTime(final String select, final String query, final String group) {
        return new SQL() {
            {
                SELECT(select);
                FROM("w_member");
                WHERE(query + " and deleted=0");
            }
        }.toString() + " " + group;
    }

    public String updateInfo(final String set, final String query) {
        return new SQL() {
            {
                UPDATE("w_member");
                SET(set);
                WHERE(query + " and deleted=0");
            }
        }.toString();
    }


}
