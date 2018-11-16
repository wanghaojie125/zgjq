package com.zhangongjx.repository.l;

import com.zhangongjx.model.l.LogLoginEntity;
import com.zhangongjx.model.l.LogSMSEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ILogSMSRepository {
    @Select("select * from l_log_sms where deleted=0 and phone=#{phone} and code=#{code} and status=1 order by create_at desc")
    LogSMSEntity getDataByPhoneAndCode(@Param("phone") String phone, @Param("code") String code);

    @Insert("insert into l_log_sms(phone,code,content,status,msg,expire_at) VALUES(#{phone}, #{code},#{content},#{status},#{msg},#{expire_at})")
    void insert(LogSMSEntity item);
}
