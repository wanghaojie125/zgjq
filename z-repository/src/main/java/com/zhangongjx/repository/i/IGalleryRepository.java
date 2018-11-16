package com.zhangongjx.repository.i;

import com.zhangongjx.model.i.GalleryEntity;
import com.zhangongjx.model.m.GoodsEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IGalleryRepository {
    @Select("select * from i_gallery where deleted=0 limit #{skip},#{pageSize}")
    List<GalleryEntity> getDatas(@Param("skip") int skip, @Param("pageSize") int pageSize);

    @Select("select * from i_gallery where deleted=0")
    List<GalleryEntity> getAllDatas();

    @Select("select count(id) from i_gallery where deleted=0")
    int getCount();

    @Insert("insert into i_gallery(name,cover,path,sort,desp) VALUES(#{name}, #{cover},#{path},#{sort},#{desp})")
    void insert(GalleryEntity item);

    @Update("update i_gallery set name=#{name},cover=#{cover},path=#{path},sort=#{sort},desp=#{desp},update_at=#{update_at} where id=#{id}")
    void update(GalleryEntity item);

    @Update("update i_gallery set deleted=1 where id=#{id}")
    void delete(int id);

    @Update("update i_gallery set count=#{count} where id=#{id}")
    void updateCount(@Param("id") int id, @Param("count") int count);
}
