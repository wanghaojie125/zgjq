package com.zhangongjx.repository.l;

import com.zhangongjx.model.l.LogGoodsEntity;
import com.zhangongjx.model.l.LogLoginEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ILogGoodsRepository {
    @Insert("insert into l_log_goods(oper_type,goods_id,goods_type,count,oper_id,oper_name,oper,remain) VALUES(#{oper_type}, #{goods_id},#{goods_type},#{count},#{oper_id},#{oper_name},#{oper},#{remain})")
    void insert(LogGoodsEntity item);
}
