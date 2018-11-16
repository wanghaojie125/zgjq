package com.zhangongjx.repository.s;

import com.zhangongjx.model.s.DepEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IDepRepository {
    @Select("select * from s_dep where deleted=0 limit #{skip},#{pageSize}")
    List<DepEntity> getDatas(@Param("skip") int skip,@Param("pageSize") int pageSize);
    @Select("select * from s_dep where deleted=0")
    List<DepEntity> getAllDatas();
    @Select("select * from s_dep where deleted=0 and id=#{id}")
    DepEntity getData(int id);
    @Select("select count(id) from s_dep where deleted=0")
    int getCount();
    @Insert("insert into s_dep(name,desp) VALUES(#{name}, #{desp})")
    void insert(DepEntity item);
    @Update("update s_dep set name=#{name},desp=#{desp},update_at=#{update_at} where id=#{id}")
    void update(DepEntity item);
    @Update("update s_dep set deleted=1 where id=#{id}")
    void delete(int id);
    @Update("update s_dep set enabled=${enable} where id=#{id}")
    void enable(@Param("id") int id,@Param("enable") int enable);
}
