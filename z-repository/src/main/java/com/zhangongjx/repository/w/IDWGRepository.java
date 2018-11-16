package com.zhangongjx.repository.w;

import com.zhangongjx.repository.mapper.DWGMapper;
import com.zhangongjx.model.w.DWGEntity;
import com.zhangongjx.result.StatisticsDWGStatusEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IDWGRepository {
    @SelectProvider(type = DWGMapper.class, method = "getDatas")
    List<DWGEntity> getDatas(int skip, int pageSize, String query);

    @SelectProvider(type = DWGMapper.class, method = "getCount")
    int getCount(String query);

    @Select("select count(id) from w_dwg where deleted=0 and member_id=#{member_id}")
    int getCountByMemberId(@Param("member_id") int member_id);

    @SelectProvider(type = DWGMapper.class, method = "statisticsDWGStatus")
    List<StatisticsDWGStatusEntity> statisticsDWGStatus(String query);

    @Select("select * from w_dwg where deleted=0")
    List<DWGEntity> getAllDatas();

    @Select("select * from w_dwg where deleted=0 and id=#{id}")
    DWGEntity getData(int id);

    @Update("update w_dwg set status=#{status},oper_id=#{oper_id},oper_name=#{oper_name},oper_log=#{oper_log}  where id=#{id}")
    void updateStatus(DWGEntity item);

    @Update("update w_dwg set confirm_path=#{confirm_path}  where id=#{id}")
    void updateConfirmPath(DWGEntity item);

    @Update("update w_dwg set deleted=1 where id=#{id}")
    void delete(int id);
}
