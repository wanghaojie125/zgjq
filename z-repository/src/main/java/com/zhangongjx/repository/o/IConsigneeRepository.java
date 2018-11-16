package com.zhangongjx.repository.o;

import com.zhangongjx.model.o.ConsigneeEntity;
import com.zhangongjx.model.o.ConsigneeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IConsigneeRepository {
    @Select("select * from o_consignee where deleted=0 and member_id=#{member_id} limit #{skip},#{pageSize}")
    List<ConsigneeEntity> getDatas(@Param("skip") int skip, @Param("pageSize") int pageSize, @Param("member_id") int member_id);

    @Select("select * from o_consignee where deleted=0 and member_id=#{member_id}")
    List<ConsigneeEntity> getAllDatas(@Param("member_id") int member_id);

    @Select("select count(id) from o_consignee where deleted=0 and member_id=#{member_id}")
    int getCount(@Param("member_id") int member_id);
}
