package com.zhangongjx.repository.o;

import com.zhangongjx.model.o.OrderEntity;
import com.zhangongjx.repository.mapper.OrderMapper;
import com.zhangongjx.result.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IOrderRepository {
    @SelectProvider(type = OrderMapper.class, method = "getDatas")
    List<OrderEntity> getDatas(int skip, int pageSize, String query);

    @SelectProvider(type = OrderMapper.class, method = "getCount")
    int getCount(String query);

    @SelectProvider(type = OrderMapper.class, method = "getPayCount")
    int getPayCount(String query);

    @SelectProvider(type = OrderMapper.class, method = "getPayAmount")
    double getPayAmount(String query);

    @SelectProvider(type = OrderMapper.class, method = "statisticsOrderCountForTime")
    List<StatisticsOrderCountAndAmountForTimeEntity> statisticsOrderCountForTime(String query);

    @SelectProvider(type = OrderMapper.class, method = "statisticsOrderAmountForTime")
    List<StatisticsOrderCountAndAmountForTimeEntity> statisticsOrderAmountForTime(String query);

    @SelectProvider(type = OrderMapper.class, method = "statisticsOrderCountAndAmountForTime")
    List<StatisticsOrderCountAndAmountForTimeEntity> statisticsOrderCountAndAmountForTime(String query);

    @SelectProvider(type = OrderMapper.class, method = "statisticsOrderStatus")
    List<StatisticsOrderStatusEntity> statisticsOrderStatus(String query);

    @SelectProvider(type = OrderMapper.class, method = "statisticsMemberOrder")
    List<StatisticsMemberForOrderEntity> statisticsMemberOrder(String query);


    @SelectProvider(type = OrderMapper.class, method = "statisticsTranForMem")
    List<StatisticsTranForMemEntity> statisticsTranForMemByTime(String query);

    @SelectProvider(type = OrderMapper.class, method = "statisticsTranForAmountRange")
    StatisticsTranForAmountRangeEntity statisticsTranForAmountRangeByTime(String query);

    @SelectProvider(type = OrderMapper.class, method = "statisticsTranMenmForAmountRange")
    StatisticsTranForAmountRangeEntity statisticsTranMenmForAmountRange(String query);


    @SelectProvider(type = OrderMapper.class, method = "statisticsOrderGrowth")
    List<StatisticsOrderGrowthEntity> statisticsOrderGrowth(String query);

    @Select("select count(id) from o_order where deleted=0 and member_id=#{member_id}")
    int getOrderCountByMemberId(@Param("member_id") int member_id);

    @Select("select count(id) from o_order where deleted=0 and status in(1,2,3,4) and  member_id=#{member_id}")
    double getOrderAmountByMemberId(@Param("member_id") int member_id);


    @Insert("INSERT INTO `zhangongjx`.`o_order` ( `contract_no`, `member_id`,`start_at`,`end_at`, `amount`, `consignee_id`, `name`, `phone`, `consignee`, `operator_id`, `operator_name`, `company`, `remark`,`count`) VALUES (#{contract_no}, #{member_id},#{start_at}, #{end_at}, #{amount}, #{consignee_id}, #{name}, #{phone}, #{consignee}, #{operator_id}, #{operator_name},#{company},#{remark},#{count})")
    void insert(OrderEntity item);

    @Update("UPDATE `zhangongjx`.`o_order` SET `update_at`=#{update_at},`member_id`=#{member_id}, `start_at`=#{start_at}, `end_at`=#{end_at}, `amount`=#{amount}, `consignee_id`=#{consignee_id}, `name`=#{name}, `phone`=#{phone}, `consignee`=#{consignee}, `operator_id`=#{operator_id}, `operator_name`=#{operator_name}, `company`=#{company}, `remark`=#{remark}, `count`=#{count} WHERE `id`=#{id}")
    void update(OrderEntity item);

    @Update("UPDATE `zhangongjx`.`o_order` SET `update_at`=#{update_at},`operator_id`=#{operator_id}, `operator_name`=#{operator_name}, `status`=#{status}, `waybill_no`=#{waybill_no} WHERE `id`=#{id}")
    void updateStatus(OrderEntity item);

    @Select("select * from o_order where deleted=0 and id=#{id}")
    OrderEntity getData(int id);
}
