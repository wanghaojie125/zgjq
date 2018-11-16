package com.zhangongjx.repository.d;

import com.zhangongjx.model.d.MaterialsEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IMaterialsRepository {
    @Select("select * from d_materials where deleted=0 limit #{skip},#{pageSize}")
    List<MaterialsEntity> getDatas(@Param("skip") int skip, @Param("pageSize") int pageSize);
    @Select("select * from d_materials where deleted=0")
    List<MaterialsEntity> getAllDatas();
    @Select("select count(id) from d_materials where deleted=0")
    int getCount();
    @Insert("insert into d_materials(name,sg,price,oper_id,oper_name) VALUES(#{name}, #{sg},#{price},#{oper_id},#{oper_name})")
    void  insert(MaterialsEntity item);
    @Update("update d_materials set name=#{name},sg=#{sg},price=#{price},oper_id=#{oper_id},oper_name=#{oper_name},update_at=#{update_at} where id=#{id}")
    void  update(MaterialsEntity item);
}
