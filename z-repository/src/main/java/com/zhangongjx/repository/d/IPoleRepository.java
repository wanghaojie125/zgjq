package com.zhangongjx.repository.d;

import com.zhangongjx.model.d.PoleEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IPoleRepository {
    @Select("select * from d_pole where deleted=0 and type=#{type} limit #{skip},#{pageSize}")
    List<PoleEntity> getDatas(@Param("type") String type, @Param("skip") int skip, @Param("pageSize") int pageSize);
    @Select("select count(id) from d_pole where deleted=0 and type=#{type}")
    int getCount(@Param("type") String type);
    @Insert("insert into d_pole(name,type,price,oper_id,oper_name,ladder_price,axis_while_price) VALUES(#{name}, #{type},#{price},#{oper_id},#{oper_name},#{ladder_price},#{axis_while_price})")
    void  insert(PoleEntity item);
    @Update("update d_pole set name=#{name},type=#{type},price=#{price},oper_id=#{oper_id},oper_name=#{oper_name},update_at=#{update_at},ladder_price=#{ladder_price},axis_while_price=#{axis_while_price} where id=#{id}")
    void  update(PoleEntity item);
}
