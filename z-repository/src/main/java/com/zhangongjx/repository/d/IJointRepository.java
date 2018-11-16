package com.zhangongjx.repository.d;

import com.zhangongjx.model.d.JointEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IJointRepository {
    @Select("select * from d_joint where deleted=0 and type=#{type} limit #{skip},#{pageSize}")
    List<JointEntity> getDatas(@Param("type") String type,@Param("skip") int skip, @Param("pageSize") int pageSize);
    @Select("select count(id) from d_joint where deleted=0 and type=#{type}")
    int getCount(@Param("type") String type);
    @Insert("insert into d_joint(name,type,out_price,oper_id,oper_name,in_price,mm_price,base_length) VALUES(#{name}, #{type},#{out_price},#{oper_id},#{oper_name},#{in_price},#{mm_price},#{base_length})")
    void  insert(JointEntity item);
    @Update("update d_joint set name=#{name},type=#{type},out_price=#{out_price},oper_id=#{oper_id},oper_name=#{oper_name},update_at=#{update_at},in_price=#{in_price},mm_price=#{mm_price},base_length=#{base_length} where id=#{id}")
    void  update(JointEntity item);
}
