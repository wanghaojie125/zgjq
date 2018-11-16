package com.zhangongjx.repository.l;

import com.zhangongjx.model.l.LogEmailEntity;
import com.zhangongjx.model.l.LogLoginEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ILogEmailRepository {
    @Insert("insert into l_log_email(to,from,subject,content,status,msg,expire_at) VALUES(#{to}, #{from},#{subject},#{content},#{status},#{msg},#{expire_at})")
    void insert(LogEmailEntity item);
}
