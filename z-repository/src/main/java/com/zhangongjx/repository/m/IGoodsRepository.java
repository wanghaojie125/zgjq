package com.zhangongjx.repository.m;

import com.zhangongjx.model.m.GoodsEntity;
import com.zhangongjx.model.o.OrderEntity;
import com.zhangongjx.repository.mapper.GoodsMapper;
import com.zhangongjx.repository.mapper.OrderMapper;
import com.zhangongjx.result.StatisticsGoodsStatusEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IGoodsRepository {

    @SelectProvider(type = GoodsMapper.class, method = "getDatas")
    List<GoodsEntity> getDatas(int skip, int pageSize, String query);

    @SelectProvider(type = GoodsMapper.class, method = "getCount")
    int getCount(String query);

    @SelectProvider(type = GoodsMapper.class, method = "statisticsGoodsStatus")
    List<StatisticsGoodsStatusEntity> statisticsGoodsStatus(String query);

    @Select("select * from m_goods where deleted=0 and id=#{id}")
    GoodsEntity getData(int id);

    @Insert("INSERT INTO `zhangongjx`.`m_goods` (`name`,`type_id`,`type_name`,`sub_title`,`brand_id`,`brand_name`,`introduce`,`goods_no`,`selling_price`,`market_price`,`store`,`store_alarm_value`,`unit`,`weight`,`status`,`detail_title`,`detail_introduce`,`keyword`,`remark`,`images`) VALUES (#{name},#{type_id},#{type_name},#{sub_title},#{brand_id},#{brand_name},#{introduce},#{goods_no},#{selling_price},#{market_price},#{store},#{store_alarm_value},#{unit},#{weight},#{status},#{detail_title},#{detail_introduce},#{keyword},#{remark},#{images});select last_insert_id();")
    int insert(GoodsEntity item);

    @Update("UPDATE `zhangongjx`.`m_goods` SET `update_at`=#{update_at}, `name`=#{name}, `type_id`=#{type_id}, `type_name`=#{type_name}, `sub_title`=#{sub_title}, `brand_id`=#{brand_id}, `brand_name`=#{brand_name}, `introduce`=#{introduce}, `goods_no`=#{goods_no}, `selling_price`=#{selling_price}, `market_price`=#{market_price}, `store`=#{store}, `store_alarm_value`=#{store_alarm_value}, `unit`=#{unit}, `weight`=#{weight}, `status`=#{status}, `detail_title`=#{detail_title}, `detail_introduce`=#{detail_introduce}, `keyword`=#{keyword}, `remark`=#{remark},`images`=#{images} WHERE `id`=#{id}")
    void update(GoodsEntity item);

    @Update("update m_goods set status=#{status} where id=#{id}")
    void updateStatus(GoodsEntity item);

    @Update("update m_goods set store=#{store} where id=#{id}")
    void updateStore(GoodsEntity item);

    @Update("update m_goods set deleted=1 where id=#{id}")
    void delete(int id);
}
