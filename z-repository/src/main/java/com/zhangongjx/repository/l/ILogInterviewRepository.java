package com.zhangongjx.repository.l;

import com.zhangongjx.model.l.LogGoodsEntity;
import com.zhangongjx.repository.mapper.LogMapper;
import com.zhangongjx.repository.mapper.OrderMapper;
import com.zhangongjx.result.StatisticsOrderGrowthEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ILogInterviewRepository {
    @SelectProvider(type = LogMapper.class, method = "getInterviewCount")
    int getInterviewCount(String query);
}
