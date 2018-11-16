package com.zhangongjx.repository.mapper;

import org.apache.ibatis.jdbc.SQL;

public class LogMapper {
    public String getInterviewCount(final String query) {
        return new SQL() {
            {
                SELECT("IFNULL(sum(count),0)");
                FROM("l_log_interview");
                WHERE(query + " and deleted=0");
            }
        }.toString();
    }
}
