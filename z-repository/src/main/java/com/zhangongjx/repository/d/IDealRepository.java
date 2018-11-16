package com.zhangongjx.repository.d;

import com.zhangongjx.model.d.DealEntity;
import com.zhangongjx.result.DealResultEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IDealRepository {
    @Select("select d.*,m.`name` as materials_name from d_deal as d LEFT JOIN d_materials as m\n" +
            "on d.materials_id=m.id\n" +
            "where  d.type=#{type} limit #{skip},#{pageSize}")
    List<DealResultEntity> getDatas(@Param("type") String type, @Param("skip") int skip, @Param("pageSize") int pageSize);
    @Select("select count(id) from d_deal where deleted=0 and type=#{type}")
    int getCount(@Param("type") String type);
    @Insert("insert into d_deal(name,type,price,oper_id,oper_name,materials_id) VALUES(#{name}, #{type},#{price},#{oper_id},#{oper_name},#{materials_id})")
    void  insert(DealEntity item);
    @Update("update d_deal set name=#{name},type=#{type},price=#{price},oper_id=#{oper_id},oper_name=#{oper_name},update_at=#{update_at},materials_id=#{materials_id} where id=#{id}")
    void  update(DealEntity item);
}
