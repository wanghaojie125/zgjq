package com.zhangongjx.repository.c;

import com.zhangongjx.model.c.ConfEmailTemplateEntity;
import com.zhangongjx.model.c.ConfInfoEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IConfEmailTemplateRepository {
    @Select("select * from c_sys_conf_email_template where deleted=0 and type=#{type}")
    ConfEmailTemplateEntity getData(@Param("type") String type);
    @Insert("INSERT INTO `zhangongjx`.`c_sys_conf_email_template` (`type`, `title`, `template`) VALUES (#{type}, #{title}, #{template})")
    void  insert(ConfEmailTemplateEntity item);
    @Update("update c_sys_conf_email_template set type=#{type},title=#{title},template=#{template},update_at=#{update_at} where type=#{type}")
    void  update(ConfEmailTemplateEntity item);
    @Update("update c_sys_conf_email_template set deleted=1 where id=#{id}")
    void  delete(int id);
}
