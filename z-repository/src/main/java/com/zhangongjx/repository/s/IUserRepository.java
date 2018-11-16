package com.zhangongjx.repository.s;

import com.zhangongjx.model.s.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IUserRepository {
    @Select("select * from s_user where deleted=0 and user_name=#{userName}")
    UserEntity getUserBy(@Param("userName") String userName);
    @Select("select * from s_user where deleted=0 limit #{skip},#{pageSize}")
    List<UserEntity> getDatas(@Param("skip") int skip, @Param("pageSize") int pageSize);
    @Select("select * from s_user where deleted=0 and id=#{id}")
    UserEntity getData(int id);
    @Select("select count(id) from s_user where deleted=0")
    int getCount();
    @Insert("insert into s_user(user_name,pass_word,email,remark,dep_name,dep_id,real_name,phone) VALUES(#{user_name}, #{pass_word},#{email},#{remark},#{dep_name},#{dep_id},#{real_name},#{phone})")
    void insert(UserEntity item);
    @Update("update s_user set user_name=#{user_name},pass_word=#{pass_word},email=#{email},remark=#{remark},dep_name=#{dep_name},dep_id=#{dep_id},real_name=#{real_name},update_at=#{update_at} where id=#{id}")
    void update(UserEntity item);

    @Update("update s_user set pass_word=#{pass_word},head_portraits=#{head_portraits} where id=#{id}")
    void updatePwdAndHead(UserEntity item);
    @Update("update s_user set deleted=1 where id=#{id}")
    void delete(int id);
    @Update("update s_user set enabled=${enable} where id=#{id}")
    void enable(@Param("id") int id,@Param("enable") int enable);
    @Select("select * from s_user where deleted=0 and phone=#{phone}")
    UserEntity getUserByPhone(String phone);
}
