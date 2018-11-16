package com.zhangongjx.repository.l;

import com.zhangongjx.model.i.GalleryEntity;
import com.zhangongjx.model.l.LogLoginEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ILogLoginRepository {
    @Select("select * from l_log_login where deleted=0 and type=#{type} limit #{skip},#{pageSize}")
    List<LogLoginEntity> getDatas(@Param("skip") int skip, @Param("pageSize") int pageSize, @Param("type") int type);

    @Select("select count(id) from l_log_login where deleted=0 and type=#{type}")
    int getCount(@Param("type") int type);

    @Select("select * from l_log_login where deleted=0 and user_id=#{user_id} and type=#{type} limit #{skip},#{pageSize}")
    List<LogLoginEntity> getDataByMemberId(@Param("skip") int skip, @Param("pageSize") int pageSize, @Param("type") int type, @Param("user_id") int user_id);

    @Select("select count(id) from l_log_login where deleted=0 and type=#{type} and user_id=#{user_id}")
    int getCountByUserId(@Param("type") int type, @Param("user_id") int user_id);


    @Insert("insert into l_log_login(login_at,ip,area,browser,type,user_id) VALUES(#{login_at}, #{ip},#{area},#{browser},#{type},#{user_id})")
    void insert(LogLoginEntity item);
}
