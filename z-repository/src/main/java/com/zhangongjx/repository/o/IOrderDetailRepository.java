package com.zhangongjx.repository.o;

import com.zhangongjx.model.o.OrderDetailEntity;
import com.zhangongjx.model.o.OrderDetailEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IOrderDetailRepository {
    @Insert("INSERT INTO `zhangongjx`.`o_order_detail` (`order_id`, `no`, `goods_name`, `goods_model`, `count`, `unit`, `price`, `total_price`, `date`, `remark`) VALUES (#{order_id}, #{no}, #{goods_name}, #{goods_model}, #{count}, #{unit}, #{price}, #{total_price}, #{date}, #{remark})")
    int insert(OrderDetailEntity item);
    @Select("select * from o_order_detail where deleted=0 and order_id=#{order_id}")
    List<OrderDetailEntity> getDatas(@Param("order_id") String  order_id);
    @Update("update o_order_detail set deleted=1 where order_id=#{order_id}")
    void delete(@Param("order_id") String  order_id);
}
