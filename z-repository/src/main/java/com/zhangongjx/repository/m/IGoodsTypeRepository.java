package com.zhangongjx.repository.m;

import com.zhangongjx.model.m.GoodsTypeEntity;
import com.zhangongjx.result.SourceResultEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IGoodsTypeRepository {
    @Select({"select * from m_goods_type where deleted=0"})
    List<GoodsTypeEntity> getGoodsTypes();
    @Insert("insert into m_goods_type(name,par_id) VALUES (#{name},#{par_id})")
    void insert(GoodsTypeEntity item);
    @Update("update m_goods_type set name=#{name},par_id=#{par_id} where id=#{id}")
    void update(GoodsTypeEntity item);
}
