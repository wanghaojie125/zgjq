package com.zhangongjx.repository.w;

import com.zhangongjx.model.w.MemberEntity;
import com.zhangongjx.repository.mapper.MemberMapper;
import com.zhangongjx.result.StatisticsMemberForGrowthEntity;
import com.zhangongjx.result.StatisticsMemberForTimeEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IMemberRepository {
    @SelectProvider(type = MemberMapper.class, method = "getDatas")
    List<MemberEntity> getDatas(int skip, int pageSize, String query);

    @SelectProvider(type = MemberMapper.class, method = "getDatasByQuery")
    List<MemberEntity> getDatasByQuery(String query);

    @SelectProvider(type = MemberMapper.class, method = "getCount")
    int getCount(String query);

    @SelectProvider(type = MemberMapper.class, method = "statisticsCountByTime")
    StatisticsMemberForTimeEntity statisticsCountByTime(String select, String query);

    @SelectProvider(type = MemberMapper.class, method = "statisticsMemGrowthByTime")
    List<StatisticsMemberForGrowthEntity> statisticsMemGrowthByTime(String select, String query, String group);

    @Select("select * from w_member where deleted=0")
    List<MemberEntity> getAllDatas();

    @Update("update w_member set enabled=#{enabled}  where id=#{id}")
    void updateStatus(MemberEntity item);

    @SelectProvider(type = MemberMapper.class, method = "updateInfo")
    void updateInfo(String set, String query);

    @Update("update w_member set deleted=1 where id=#{id}")
    void delete(int id);
}
