package com.zhangongjx.repository.d;

import com.zhangongjx.model.d.DrillEntity;
import com.zhangongjx.model.d.DrillEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IDrillRepository {
    @Select("select * from d_drill where deleted=0 and type=#{type} limit #{skip},#{pageSize}")
    List<DrillEntity> getDatas(@Param("type") String type, @Param("skip") int skip, @Param("pageSize") int pageSize);
    @Select("select count(id) from d_drill where deleted=0 and type=#{type}")
    int getCount(@Param("type") String type);
    @Insert("insert into d_drill(name,type,price,oper_id,oper_name) VALUES(#{name}, #{type},#{price},#{oper_id},#{oper_name})")
    void  insert(DrillEntity item);
    @Update("update d_drill set name=#{name},type=#{type},price=#{price},oper_id=#{oper_id},oper_name=#{oper_name},update_at=#{update_at} where id=#{id}")
    void  update(DrillEntity item);
}
