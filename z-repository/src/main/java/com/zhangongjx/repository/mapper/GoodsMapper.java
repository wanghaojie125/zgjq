package com.zhangongjx.repository.mapper;

import org.apache.ibatis.jdbc.SQL;

public class GoodsMapper {

    public String getDatas(final int skip, final int pageSize, final String query) {
        return new SQL() {
            {
                SELECT("*");
                FROM("m_goods");
                WHERE(query + " and deleted=0");
            }
        }.toString() + "limit " + skip + "," + pageSize;
    }

    public String getCount(final String query) {
        return new SQL() {
            {
                SELECT("count(id)");
                FROM("m_goods");
                WHERE(query + " and deleted=0");
            }
        }.toString();
    }

    public String statisticsGoodsStatus(final String query) {
        return new SQL() {
            {
                SELECT("status,count(id) as count,\n" +
                        "(select count(id)  from m_goods \n" +
                        "where deleted=0 and store<=store_alarm_value) as alarm_count");
                FROM("m_goods");
                WHERE(query + " and deleted=0");
            }
        }.toString() + " group by status order by status";
    }
}
