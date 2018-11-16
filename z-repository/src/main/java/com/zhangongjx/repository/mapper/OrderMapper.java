package com.zhangongjx.repository.mapper;

import org.apache.ibatis.jdbc.SQL;

public class OrderMapper {

    public String getDatas(final int skip, final int pageSize, final String query) {
        return new SQL() {
            {
                SELECT("*");
                FROM("o_order");
                WHERE(query + " and deleted=0");
            }
        }.toString() + "limit " + skip + "," + pageSize;
    }

    public String getCount(final String query) {
        return new SQL() {
            {
                SELECT("count(id)");
                FROM("o_order");
                WHERE(query + " and deleted=0");
            }
        }.toString();
    }

    public String getPayCount(final String query) {
        return new SQL() {
            {
                SELECT("count(id)");
                FROM("o_order");
                WHERE(query + " and status in (1,2,3,4) and deleted=0");
            }
        }.toString();
    }

    public String getPayAmount(final String query) {
        return new SQL() {
            {
                SELECT("IFNULL(sum(amount),0)");
                FROM("o_order");
                WHERE(query + " and status in (1,2,3,4) and deleted=0");
            }
        }.toString();
    }

    public String statisticsOrderCountForTime(final String query) {
        return new SQL() {
            {
                SELECT("DATE_FORMAT(create_at,'%Y-%c-%e') as date,count(id) as count");
                FROM("o_order");
                WHERE(query + " and deleted=0");
            }
        }.toString() + " group by date order by date desc";
    }

    public String statisticsOrderAmountForTime(final String query) {
        return new SQL() {
            {
                SELECT("DATE_FORMAT(create_at,'%Y-%c-%e') as date,sum(amount) as amount");
                FROM("o_order");
                WHERE(query + " and deleted=0");
            }
        }.toString() + " group by date order by date desc";
    }

    public String statisticsOrderCountAndAmountForTime(final String query) {
        return new SQL() {
            {
                SELECT("DATE_FORMAT(create_at,'%Y-%c-%e') as date,count(id) as count,sum(amount) as amount");
                FROM("o_order");
                WHERE(query + " and status in (1,2,3,4) and deleted=0");
            }
        }.toString() + " group by date order by date desc";
    }

    public String statisticsOrderStatus(final String query) {
        return new SQL() {
            {
                SELECT("count(id) as count,sum(amount) as amount,status");
                FROM("o_order");
                WHERE(query + " and deleted=0");
            }
        }.toString() + " group by status order by status";
    }

    public String statisticsMemberOrder(final String query) {
        return new SQL() {
            {
                SELECT("count(id) as count,sum(amount) as amount,member_id");
                FROM("o_order");
                WHERE(query + " and deleted=0");
            }
        }.toString() + " group by member_id order by amount desc";
    }

    public String statisticsOrderGrowth(final String query) {
        return new SQL() {
            {
                SELECT("DATE_FORMAT(create_at,'%Y-%u') weeks, DATE_FORMAT(create_at,'%Y-%c') months,count(id) count,sum(amount) amount");
                FROM("o_order");
                WHERE(query + " and deleted=0");
            }
        }.toString() + " group by weeks,months";
    }

    public String statisticsTranForMem(final String query) {
        return new SQL() {
            {
                SELECT("DATE_FORMAT(o.create_at,'%Y-%c') months,count(o.id) count,sum(o.amount) amount,DATE_FORMAT(m.create_at,'%Y-%c') m_months,count(DISTINCT m.id) m_count");
                FROM("o_order o LEFT JOIN w_member m on o.member_id=m.id");
                WHERE(query + " and o.deleted=0 and m.deleted=0");
            }
        }.toString() + " group by m_months,months";
    }

    public String statisticsTranForAmountRange(final String query) {
        return new SQL() {
            {
                SELECT("COUNT(CASE WHEN IFNULL(amount, 0) >=0 and IFNULL(amount, 0) <=50 THEN id END ) AS 'r0_50', \n" +
                        "COUNT(CASE WHEN IFNULL(amount, 0) >=51 and IFNULL(amount, 0) <=100 THEN id END ) AS 'r51_100',\n" +
                        "COUNT(CASE WHEN IFNULL(amount, 0) >=101 and IFNULL(amount, 0) <=200 THEN id END ) AS 'r101_200', \n" +
                        "COUNT(CASE WHEN IFNULL(amount, 0) >=201 and IFNULL(amount, 0) <=500 THEN id END ) AS 'r201_500', \n" +
                        "COUNT(CASE WHEN IFNULL(amount, 0) >=501 and IFNULL(amount, 0) <=1000 THEN id END ) AS 'r501_1000',\n" +
                        "COUNT(CASE WHEN IFNULL(amount, 0) >=1001 and IFNULL(amount, 0) <=5000 THEN id END ) AS 'r1001_5000',\n" +
                        "COUNT(CASE WHEN IFNULL(amount, 0) >=5001 and IFNULL(amount, 0) <=10000 THEN id END ) AS 'r5001_10000',\n" +
                        "COUNT(CASE WHEN IFNULL(amount, 0) >=10001 THEN id END ) AS 'r10001'");
                FROM("o_order");
                WHERE(query + " and deleted=0");
            }
        }.toString() + "";
    }

    public String statisticsTranMenmForAmountRange(final String query) {
        return new SQL() {
            {
                SELECT("COUNT(CASE WHEN IFNULL(a.amount, 0) >=0 and IFNULL(a.amount, 0) <=50 THEN a.member_id END ) AS 'r0_50', \n" +
                        "COUNT(CASE WHEN IFNULL(a.amount, 0) >=51 and IFNULL(a.amount, 0) <=100 THEN a.member_id END ) AS 'r51_100',\n" +
                        "COUNT(CASE WHEN IFNULL(a.amount, 0) >=101 and IFNULL(a.amount, 0) <=200 THEN a.member_id END ) AS 'r101_200', \n" +
                        "COUNT(CASE WHEN IFNULL(a.amount, 0) >=201 and IFNULL(a.amount, 0) <=500 THEN a.member_id END ) AS 'r201_500', \n" +
                        "COUNT(CASE WHEN IFNULL(a.amount, 0) >=501 and IFNULL(a.amount, 0) <=1000 THEN a.member_id END ) AS 'r501_1000',\n" +
                        "COUNT(CASE WHEN IFNULL(a.amount, 0) >=1001 and IFNULL(a.amount, 0) <=5000 THEN a.member_id END ) AS 'r1001_5000',\n" +
                        "COUNT(CASE WHEN IFNULL(a.amount, 0) >=5001 and IFNULL(a.amount, 0) <=10000 THEN a.member_id END ) AS 'r5001_10000',\n" +
                        "COUNT(CASE WHEN IFNULL(a.amount, 0) >=10001 THEN member_id END ) AS 'r10001'");
                FROM("(select sum(amount) as amount,member_id from o_order where member_id in(select id\n" +
                        "from w_member where " + query + " and deleted=0)\n" +
                        "and deleted=0 and status in (1,2,3,4) GROUP BY member_id) as a");
            }
        }.toString() + "";
    }
}
