package com.zhangongjx.repository.i;

import com.zhangongjx.model.i.GalleryEntity;
import com.zhangongjx.model.i.ImageEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IImageRepository {
    @Select("select * from i_image where deleted=0 and gallery_id=#{gallery_id} limit #{skip},#{pageSize}")
    List<ImageEntity> getDatas(@Param("skip") int skip, @Param("pageSize") int pageSize, @Param("gallery_id") int gallery_id);

    @Select("select count(id) from i_image where deleted=0 and gallery_id=#{gallery_id}")
    int getCount(@Param("gallery_id") int gallery_id);

    @Insert("insert into i_image (path,gallery_id) values (#{path},#{gallery_id})")
    int insert(ImageEntity item);

    @Update("update i_image set gallery_id=#{gallery_id} where id=#{id}")
    int updateGallary(ImageEntity item);

    @Update("update i_image set deleted=1 where id=#{id}")
    int delete(int id);

}
