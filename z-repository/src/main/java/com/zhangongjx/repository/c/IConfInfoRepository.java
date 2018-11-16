package com.zhangongjx.repository.c;

import com.zhangongjx.model.c.ConfInfoEntity;
import com.zhangongjx.model.c.ConfVerifyCodeEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IConfInfoRepository {
    @Select("select * from c_sys_conf_info where deleted=0")
    ConfInfoEntity getData();
    @Insert("INSERT INTO `zhangongjx`.`c_sys_conf_info` (`name`, `title`, `desp`, `keyword`, `logo`, `service_tel`, `service_email`, `remark`) VALUES (#{name}, #{title}, #{desp}, #{keyword}, #{logo}, #{service_tel},#{service_email},#{remark})")
    void  insert(ConfInfoEntity item);
    @Update("update c_sys_conf_info set name=#{name},title=#{title},desp=#{desp},keyword=#{keyword},logo=#{logo},update_at=#{update_at},service_tel=#{service_tel},service_email=#{service_email},remark=#{remark}")
    void  update(ConfInfoEntity item);
    @Update("update c_sys_conf_info set deleted=1 where id=#{id}")
    void  delete(int id);
}
