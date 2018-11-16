package com.zhangongjx.repository.c;

import com.zhangongjx.model.c.ConfAdEntity;
import com.zhangongjx.model.c.ConfAdEntity;
import com.zhangongjx.repository.mapper.ConfAdMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IConfAdRepository {
    @SelectProvider(type = ConfAdMapper.class, method = "getDatas")
    //@Select("select * from c_sys_conf_ad where #{query} and deleted=0 limit #{skip},#{pageSize}")
    List<ConfAdEntity> getDatas(int skip, int pageSize, String query);
    @SelectProvider(type = ConfAdMapper.class, method = "getCount")
    int getCount(String query);
    @Insert("INSERT INTO c_sys_conf_ad (name, position, start_at, end_at, status, images, url, remark) VALUES (#{name}, #{position}, #{start_at}, #{end_at}, #{status}, #{images},#{url},#{remark})")
    void  insert(ConfAdEntity item);
    @Update("update c_sys_conf_ad set name=#{name},position=#{position},start_at=#{start_at},end_at=#{end_at},status=#{status},update_at=#{update_at},images=#{images},url=#{url},remark=#{remark}  where id=#{id}")
    void  update(ConfAdEntity item);
    @Update("update c_sys_conf_ad set status=#{status}  where id=#{id}")
    void  updateStatus(ConfAdEntity item);
    @Update("update c_sys_conf_ad set deleted=1 where id=#{id}")
    void  delete(int id);
}
