package com.zhangongjx.repository.c;

import com.zhangongjx.model.c.ConfAdEntity;
import com.zhangongjx.model.c.ConfVerifyCodeEntity;
import com.zhangongjx.repository.mapper.ConfAdMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IConfVerifyCodeRepository {
    @Select("select * from c_sys_conf_verify_code where deleted=0")
    ConfVerifyCodeEntity getData();
    @Insert("INSERT INTO `zhangongjx`.`c_sys_conf_verify_code` (`register`, `login`, `sys_login`, `login_fail`, `way`, `width`, `size`, `digit`,`height`) VALUES (#{register}, #{login}, #{sys_login}, #{login_fail}, #{way}, #{width},#{size},#{digit},#{height})")
    void  insert(ConfVerifyCodeEntity item);
    @Update("update c_sys_conf_verify_code set register=#{register},login=#{login},sys_login=#{sys_login},login_fail=#{login_fail},way=#{way},update_at=#{update_at},width=#{width},size=#{size},digit=#{digit},height=#{height}")
    void  update(ConfVerifyCodeEntity item);
    @Update("update c_sys_conf_verify_code set deleted=1 where id=#{id}")
    void  delete(int id);
}
